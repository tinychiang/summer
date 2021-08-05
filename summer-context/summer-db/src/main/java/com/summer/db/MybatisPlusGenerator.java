package com.summer.db;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis Plus 代码生成器
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-04
 */
@SuppressWarnings("all")
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 文件位置
        String filepath = "/Users/chiang/Desktop/summer";
        // java文件路径
        String javaPath = "/src/main/java";
        // mapper文件路径
        String mapperPath = "/src/main/resources/mapper/";
        // 作者
        String author = "Tiny Chiang";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/summer?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String username = "root";
        String password = "tinychiang";
        // 表名称
        String[] tableNames = {"product"};
        // 包名称
        String packageName = "com.summer.db";
        // 模块名称
        String moduleName = "";
        // 模板引擎: freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 模板引擎: velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(filepath.concat(javaPath));
        globalConfig.setAuthor(author);
        globalConfig.setOpen(false);
        // 属性添加Swagger2注解
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driver);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent(packageName);
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义输出配置
        List<FileOutConfig> fileOutConfigs = new ArrayList<FileOutConfig>() {{
            // 自定义配置会被优先输出
            this.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return filepath.concat(mapperPath)
                            .concat(packageConfig.getModuleName())
                            .concat("/")
                            .concat(tableInfo.getEntityName())
                            .concat("Mapper")
                            .concat(StringPool.DOT_XML);
                }
            });
        }};

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        injectionConfig.setFileOutConfigList(fileOutConfigs);
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 设置父类
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("字段");
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }

}