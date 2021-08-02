package com.summer.frame.elasticsearch.assembly;

import com.summer.frame.elasticsearch.AbstractPageHelper;
import com.summer.frame.elasticsearch.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 条件整理
 * 懒汉单例 - 线程安全
 * </p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-30
 */
@Slf4j
public class NativeSearchQueryAssembly<T> {

    private static NativeSearchQueryAssembly<?> nativeSearchQueryAssembly = null;

    private NativeSearchQueryAssembly(T condition) {
        this.condition = condition;
        this.clazz = condition.getClass();
    }

    @SuppressWarnings("unchecked")
    public static <T> NativeSearchQueryAssembly<T> instance(T condition) {
        if (nativeSearchQueryAssembly == null) {
            synchronized (NativeSearchQueryAssembly.class) {
                if (nativeSearchQueryAssembly == null) {
                    nativeSearchQueryAssembly = new NativeSearchQueryAssembly<>(condition);
                }
            }
        }
        return (NativeSearchQueryAssembly<T>) nativeSearchQueryAssembly;
    }

    private final Class<?> clazz;

    private final T condition;

    private final NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

    public NativeSearchQuery assembly() {
        if (clazz.isAnnotationPresent(SearchType.class)) {
            this.searchType();
        }
        if (clazz.isAnnotationPresent(FetchSource.class)) {
            this.fetchSourceFilter();
        }
        if (clazz.isAnnotationPresent(ScriptSorter.class)) {
            this.scriptSort();
        }
        if (clazz.isAnnotationPresent(Highlighters.class)) {
            this.highlighters();
        } else if (clazz.isAnnotationPresent(Highlighter.class)) {
            this.highlighter();
        }
        if (condition instanceof AbstractPageHelper) {
            this.fieldSort();
        }
        if (clazz.isAnnotationPresent(Aggregations.class)) {
            this.aggregations();
        }
        return nativeSearchQueryBuilder.build();
    }

    private void searchType() {
        SearchType searchType = clazz.getAnnotation(SearchType.class);
        nativeSearchQueryBuilder.withSearchType(searchType.searchType());
    }

    private void fetchSourceFilter() {
        FetchSource fetchSource = clazz.getAnnotation(FetchSource.class);
        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(fetchSource.includes(), fetchSource.excludes());
        nativeSearchQueryBuilder.withSourceFilter(fetchSourceFilter);
    }

    private void highlighters() {
        Highlighters highlighters = clazz.getAnnotation(Highlighters.class);
        HighlightBuilder.Field[] fields = Arrays.stream(highlighters.highlighters()).map(this::highlighter).toArray(HighlightBuilder.Field[]::new);
        nativeSearchQueryBuilder.withHighlightFields(fields);
    }

    private void highlighter() {
        Highlighter highlighter = clazz.getAnnotation(Highlighter.class);
        HighlightBuilder.Field field = this.highlighter(highlighter);
        nativeSearchQueryBuilder.withHighlightFields(field);
    }

    private HighlightBuilder.Field highlighter(Highlighter highlighter) {
        return new HighlightBuilder.Field(highlighter.field())
                .preTags(highlighter.preTags())
                .postTags(highlighter.postTags())
                .fragmentOffset(highlighter.fragmentSize())
                .numOfFragments(highlighter.numOfFragments());
    }

    private void scriptSort() {
        ScriptSorter scriptSorter = clazz.getAnnotation(ScriptSorter.class);
        ScriptSortBuilder scriptSortBuilder = SortBuilders.scriptSort(new Script(scriptSorter.script()), scriptSorter.scriptSortType());
        nativeSearchQueryBuilder.withSort(scriptSortBuilder);
    }

    private void fieldSort() {
        AbstractPageHelper abstractPageHelper = (AbstractPageHelper) condition;
        this.pageable(abstractPageHelper.getFrom(), abstractPageHelper.getSize());
        this.fieldSort(abstractPageHelper.getSorters());
    }

    private void pageable(int from, int size) {
        nativeSearchQueryBuilder.withPageable(PageRequest.of(from, size));
    }

    private void fieldSort(List<AbstractPageHelper.Sorter> sorters) {
        if (CollectionUtils.isNotEmpty(sorters)) {
            sorters.forEach(sorter -> {
                SortBuilder<FieldSortBuilder> sortBuilder = SortBuilders.fieldSort(sorter.getField()).order(sorter.getSortOrder());
                nativeSearchQueryBuilder.withSort(sortBuilder);
            });
        }
    }

    private void aggregations() {
        Aggregation[] aggregations = clazz.getAnnotation(Aggregations.class).aggregations();
        List<Aggregation> roots = Arrays.stream(aggregations).filter(Aggregation::root).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roots)) {
            roots.forEach(aggregation -> {
                AbstractAggregationBuilder<?> abstractAggregationBuilder = this.aggregationBuilder(aggregation);
                Assert.notNull(abstractAggregationBuilder, "Can not build aggregation.");
                while (StringUtils.isNotEmpty(aggregation.subName())) {
                    aggregation = this.subAggregation(aggregation.subName());
                    abstractAggregationBuilder.subAggregation(this.aggregationBuilder(aggregation));
                }
                nativeSearchQueryBuilder.addAggregation(abstractAggregationBuilder);
            });
        } else {
            throw new IllegalArgumentException("Can not find root aggregation");
        }
    }

    private AbstractAggregationBuilder<?> aggregationBuilder(Aggregation aggregation) {
        switch (aggregation.type()) {
            case AVG:
                return this.avg(aggregation);
            case COUNT:
                return this.count(aggregation);
            case DATE_HISTOGRAM:
                return this.dateHistogram(aggregation);
            case MAX:
                return this.max(aggregation);
            case MIN:
                return this.min(aggregation);
            case SUM:
                return this.sum(aggregation);
            default:
                return null;
        }
    }

    private Aggregation subAggregation(String subName) {
        Aggregation[] aggregations = clazz.getAnnotation(Aggregations.class).aggregations();
        Optional<Aggregation> optional = Stream.of(aggregations).filter(aggregation -> aggregation.name().equals(subName)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("Sub aggregation is not found.");
    }

    private AvgAggregationBuilder avg(Aggregation aggregation) {
        return AggregationBuilders.avg(aggregation.name()).field(aggregation.field());
    }

    private ValueCountAggregationBuilder count(Aggregation aggregation) {
        return AggregationBuilders.count(aggregation.name()).field(aggregation.field());
    }

    private DateHistogramAggregationBuilder dateHistogram(Aggregation aggregation) {
        return AggregationBuilders.dateHistogram(aggregation.name()).field(aggregation.field());
    }

    private MaxAggregationBuilder max(Aggregation aggregation) {
        return AggregationBuilders.max(aggregation.name()).field(aggregation.field());
    }

    private MinAggregationBuilder min(Aggregation aggregation) {
        return AggregationBuilders.min(aggregation.name()).field(aggregation.field());
    }

    private SumAggregationBuilder sum(Aggregation aggregation) {
        return AggregationBuilders.sum(aggregation.name()).field(aggregation.field());
    }

    /**
     * 判断对象为基本类型或基本封装类型
     *
     * @param object 对象
     * @return true: 符合; false: 不符合
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private boolean isPrimitive(Object object) {
        if (object != null) {
            return object.getClass().isPrimitive() ||
                    object instanceof String ||
                    object instanceof Integer ||
                    object instanceof Long ||
                    object instanceof Double ||
                    object instanceof Boolean ||
                    object instanceof Float ||
                    object instanceof Byte ||
                    object instanceof Character ||
                    object instanceof Short;
        }
        return false;
    }

}