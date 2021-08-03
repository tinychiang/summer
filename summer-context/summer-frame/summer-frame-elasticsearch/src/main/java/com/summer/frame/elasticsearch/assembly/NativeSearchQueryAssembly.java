package com.summer.frame.elasticsearch.assembly;

import com.summer.frame.elasticsearch.AbstractPageHelper;
import com.summer.frame.elasticsearch.annotation.*;
import com.summer.frame.elasticsearch.annotation.field.RangeField;
import com.summer.frame.elasticsearch.annotation.field.StringField;
import com.summer.frame.elasticsearch.annotation.field.TermField;
import com.summer.frame.elasticsearch.annotation.field.WildcardField;
import com.summer.frame.elasticsearch.enums.Link;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.*;
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

import java.lang.reflect.Field;
import java.util.*;
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
        this.boolQueryBuilder = QueryBuilders.boolQuery();
        this.nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
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

    private final BoolQueryBuilder boolQueryBuilder;

    private final NativeSearchQueryBuilder nativeSearchQueryBuilder;

    public NativeSearchQuery assembly() {
        if (this.clazz.isAnnotationPresent(SearchType.class)) {
            this.searchType();
        }
        if (this.clazz.isAnnotationPresent(FetchSource.class)) {
            this.fetchSourceFilter();
        }
        if (this.clazz.isAnnotationPresent(ScriptSorter.class)) {
            this.scriptSort();
        }
        if (this.clazz.isAnnotationPresent(Highlighters.class)) {
            this.highlighters();
        }
        if (this.clazz.isAnnotationPresent(Highlighter.class)) {
            this.highlighter();
        }
        if (this.clazz.isAnnotationPresent(Aggregations.class)) {
            this.aggregations();
        }
        if (this.clazz.isAnnotationPresent(Aggregation.class)) {
            this.aggregation();
        }
        if (this.condition instanceof AbstractPageHelper) {
            this.fieldSort();
        }
        this.queryBuilder();
        return this.nativeSearchQueryBuilder.build();
    }

    private void searchType() {
        SearchType searchType = this.clazz.getAnnotation(SearchType.class);
        this.nativeSearchQueryBuilder.withSearchType(searchType.value());
    }

    private void fetchSourceFilter() {
        FetchSource fetchSource = this.clazz.getAnnotation(FetchSource.class);
        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(fetchSource.includes(), fetchSource.excludes());
        this.nativeSearchQueryBuilder.withSourceFilter(fetchSourceFilter);
    }

    private void highlighters() {
        Highlighters highlighters = this.clazz.getAnnotation(Highlighters.class);
        HighlightBuilder.Field[] fields = Arrays.stream(highlighters.highlighters()).map(this::highlighter).toArray(HighlightBuilder.Field[]::new);
        this.nativeSearchQueryBuilder.withHighlightFields(fields);
    }

    private void highlighter() {
        Highlighter highlighter = this.clazz.getAnnotation(Highlighter.class);
        HighlightBuilder.Field field = this.highlighter(highlighter);
        this.nativeSearchQueryBuilder.withHighlightFields(field);
    }

    private HighlightBuilder.Field highlighter(Highlighter highlighter) {
        return new HighlightBuilder.Field(highlighter.field())
                .preTags(highlighter.preTags())
                .postTags(highlighter.postTags())
                .fragmentOffset(highlighter.fragmentSize())
                .numOfFragments(highlighter.numOfFragments());
    }

    private void scriptSort() {
        ScriptSorter scriptSorter = this.clazz.getAnnotation(ScriptSorter.class);
        ScriptSortBuilder scriptSortBuilder = SortBuilders.scriptSort(new Script(scriptSorter.script()), scriptSorter.scriptSortType());
        this.nativeSearchQueryBuilder.withSort(scriptSortBuilder);
    }

    private void fieldSort() {
        AbstractPageHelper abstractPageHelper = (AbstractPageHelper) this.condition;
        this.pageable(abstractPageHelper.getFrom(), abstractPageHelper.getSize());
        this.fieldSort(abstractPageHelper.getSorters());
    }

    private void pageable(int from, int size) {
        this.nativeSearchQueryBuilder.withPageable(PageRequest.of(from, size));
    }

    private void fieldSort(List<AbstractPageHelper.Sorter> sorters) {
        if (CollectionUtils.isNotEmpty(sorters)) {
            sorters.forEach(sorter -> {
                SortBuilder<FieldSortBuilder> sortBuilder = SortBuilders.fieldSort(sorter.getField()).order(sorter.getSortOrder());
                this.nativeSearchQueryBuilder.withSort(sortBuilder);
            });
        }
    }

    private void aggregations() {
        Aggregation[] aggregations = this.clazz.getAnnotation(Aggregations.class).aggregations();
        List<Aggregation> roots = Arrays.stream(aggregations).filter(Aggregation::root).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roots)) {
            roots.forEach(aggregation -> {
                AbstractAggregationBuilder<?> abstractAggregationBuilder = this.aggregationBuilder(aggregation);
                while (StringUtils.isNotEmpty(aggregation.subName())) {
                    aggregation = this.subAggregation(aggregation.subName());
                    abstractAggregationBuilder.subAggregation(this.aggregationBuilder(aggregation));
                }
                this.nativeSearchQueryBuilder.addAggregation(abstractAggregationBuilder);
            });
        } else {
            throw new IllegalArgumentException("Can not find root aggregation");
        }
    }

    private void aggregation() {
        Aggregation aggregation = this.clazz.getAnnotation(Aggregation.class);
        AbstractAggregationBuilder<?> abstractAggregationBuilder = this.aggregationBuilder(aggregation);
        this.nativeSearchQueryBuilder.addAggregation(abstractAggregationBuilder);
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
                throw new IllegalArgumentException("Can not build aggregation.");
        }
    }

    private Aggregation subAggregation(String subName) {
        Aggregation[] aggregations = this.clazz.getAnnotation(Aggregations.class).aggregations();
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

    private void queryBuilder() {
        List<String> excludes = ObjectUtils.defaultIfNull(this.cascadesQuery(), Collections.emptyList());
        Stream.of(this.clazz.getDeclaredFields()).forEach(field -> {
            if (!excludes.contains(field.getName())) {
                this.basicQueryAssembly(field, this.boolQueryBuilder);
            }
        });
        this.nativeSearchQueryBuilder.withQuery(this.boolQueryBuilder);
    }

    private List<String> cascadesQuery() {
        if (this.clazz.isAnnotationPresent(Cascades.class)) {
            Cascade[] cascades = this.clazz.getAnnotation(Cascades.class).cascades();
            return new ArrayList<String>() {{
                Stream.of(cascades).forEach(cascade -> {
                    cascadeQuery(cascade);
                    Collections.addAll(this, cascade.fields());
                });
            }};
        }
        if (this.clazz.isAnnotationPresent(Cascade.class)) {
            Cascade cascade = this.clazz.getAnnotation(Cascade.class);
            this.cascadeQuery(cascade);
            return Stream.of(cascade.fields()).collect(Collectors.toList());
        }
        return null;
    }

    private void cascadeQuery(Cascade cascade) {
        BoolQueryBuilder cascadeBoolQueryBuilder = QueryBuilders.boolQuery();
        Stream.of(cascade.fields()).forEach(fieldName -> {
            try {
                Field field = this.clazz.getField(fieldName);
                this.basicQueryAssembly(field, cascadeBoolQueryBuilder);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Can not find root aggregation.", e);
            }
        });
        this.queryLink(this.boolQueryBuilder, cascadeBoolQueryBuilder, cascade.link());
    }

    private void basicQueryAssembly(Field field, BoolQueryBuilder boolQueryBuilder) {
        if (field.isAnnotationPresent(RangeField.class)) {
            RangeField rangeField = field.getAnnotation(RangeField.class);
            RangeQueryBuilder rangeQueryBuilder = this.rangeQuery(field, rangeField);
            this.queryLink(boolQueryBuilder, rangeQueryBuilder, rangeField.link());
        }
        if (field.isAnnotationPresent(StringField.class)) {
            StringField stringField = field.getAnnotation(StringField.class);
            QueryStringQueryBuilder queryStringQueryBuilder = this.stringQuery(field, stringField);
            this.queryLink(boolQueryBuilder, queryStringQueryBuilder, stringField.link());
        }
        if (field.isAnnotationPresent(TermField.class)) {
            TermField termField = field.getAnnotation(TermField.class);
            TermQueryBuilder termQueryBuilder = this.termQuery(field, termField);
            this.queryLink(boolQueryBuilder, termQueryBuilder, termField.link());
        }
        if (field.isAnnotationPresent(WildcardField.class)) {
            WildcardField wildcardField = field.getAnnotation(WildcardField.class);
            WildcardQueryBuilder wildcardQueryBuilder = this.wildcardQuery(field, wildcardField);
            this.queryLink(boolQueryBuilder, wildcardQueryBuilder, wildcardField.link());
        }
    }

    private RangeQueryBuilder rangeQuery(Field field, RangeField rangeField) {
        KeyValue<String, Object> fieldValue = this.fieldValue(field, rangeField.field());
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldValue.getKey());
        switch (rangeField.range()) {
            case GTE:
                rangeQueryBuilder.gte(fieldValue.getValue());
                break;
            case LTE:
                rangeQueryBuilder.lte(fieldValue.getValue());
                break;
            default:
                break;
        }
        if (StringUtils.isNotEmpty(rangeField.format())) {
            rangeQueryBuilder.format(rangeField.format());
        }
        return rangeQueryBuilder;
    }

    private QueryStringQueryBuilder stringQuery(Field field, StringField stringField) {
        KeyValue<String, Object> fieldValue = this.fieldValue(field, stringField.field());
        return QueryBuilders.queryStringQuery((String) fieldValue.getValue())
                .defaultField(fieldValue.getKey())
                .defaultOperator(stringField.operator());
    }

    private TermQueryBuilder termQuery(Field field, TermField termField) {
        KeyValue<String, Object> fieldValue = this.fieldValue(field, termField.field());
        return QueryBuilders.termQuery(fieldValue.getKey(), fieldValue.getValue());
    }

    private WildcardQueryBuilder wildcardQuery(Field field, WildcardField wildcardField) {
        KeyValue<String, Object> fieldValue = this.fieldValue(field, wildcardField.field());
        return QueryBuilders.wildcardQuery(fieldValue.getKey(), (String) fieldValue.getValue());
    }

    /**
     * 条件连接
     *
     * @param source 原条件
     * @param target 目标条件
     * @param link   连接方式
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private void queryLink(BoolQueryBuilder source, QueryBuilder target, Link link) {
        switch (link) {
            case MUST:
                source.must(target);
                break;
            case MUST_NOT:
                source.mustNot(target);
                break;
            case SHOULD:
                source.should(target);
                break;
            default:
                break;
        }
    }

    /**
     * 获取字段值
     *
     * @param field       字段
     * @param customField 自定义字段名称
     * @return 无法获取或非基本类型或基本封装类型, 抛出异常
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private KeyValue<String, Object> fieldValue(Field field, String customField) {
        try {
            if (this.isPrimitive(field)) {
                field.setAccessible(Boolean.TRUE);
                String name = StringUtils.isEmpty(customField) ? field.getName() : customField;
                return new DefaultKeyValue<>(name, field.get(this.condition));
            }
            throw new IllegalArgumentException(String.format("Field: <%s>. That is not primitive.", field.getName()));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can not get field value by reflection.", e);
        }
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