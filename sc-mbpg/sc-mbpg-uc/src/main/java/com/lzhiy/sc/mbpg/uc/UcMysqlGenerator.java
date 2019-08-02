package com.lzhiy.sc.mbpg.uc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/***********************************************************************
 * @website ：
 * @creator : zhiyingli
 * @create-time : 2019-08-02
 * @email : lizhiying61f@gmail.com
 * @description :
 ***********************************************************************/
public class UcMysqlGenerator {
    public static void main(String[] args) {
        //读取配置文件
        Properties properties = new Properties();
        InputStream inputStream = UcMysqlGenerator.class.getClassLoader().getResourceAsStream("uc.properties");
        try {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            properties.load(inputStream);

            //数据源配置
            String driverName = properties.getProperty("jdbc.driverClass");
            String url = properties.getProperty("jdbc.connectionURL");
            String username = properties.getProperty("jdbc.userName");
            String password = properties.getProperty("jdbc.password");

            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(DbType.MYSQL)
                    .setUrl(url)
                    .setDriverName(driverName)
                    .setUsername(username)
                    .setPassword(password);
            mpg.setDataSource(dsc);

            //全局配置
            String outputDir = properties.getProperty("global.outputDir");

            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + outputDir)
                    .setAuthor("lzhiy")
                    .setFileOverride(true)// 是否覆盖文件
                    .setActiveRecord(true)// 开启 activeRecord 模式
                    .setEnableCache(false)// XML 二级缓存
                    .setBaseResultMap(true)// XML ResultMap
                    .setBaseColumnList(true)// XML columList
                    .setOpen(false)//生成后打开文件夹
                    .setIdType(IdType.UUID)
                    // 自定义文件命名，注意 %s 会自动填充表实体属性！
                    .setMapperName("%sMapper")
                    .setXmlName("%sMapper")
                    .setServiceName("%sService")
                    .setServiceImplName("%sServiceImpl")
                    .setControllerName("%sController");
            mpg.setGlobalConfig(gc);

            //包配置
            String moduleName = properties.getProperty("package.moduleName");
            String parent = properties.getProperty("package.parent");

            PackageConfig pc = new PackageConfig();
            pc.setModuleName(moduleName)
                    .setParent(parent)
                    .setController("controller")// 这里是控制器包名，默认 web
                    .setEntity("entity")
                    .setMapper("dao")
                    .setService("service")
                    .setServiceImpl("service.impl")
                    .setXml("mapper");
            mpg.setPackageInfo(pc);

            //策略配置
            String include = properties.getProperty("strategy.include");

            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel)
                    .setColumnNaming(NamingStrategy.underline_to_camel)
//                .setTablePrefix(new String[]{"kun_"})// 此处可以修改为您的表前缀
//                .setSuperEntityClass("com.kun.cloud.common.SuperEntity")
//                .setSuperControllerClass("com.kun.SuperController")
//                .setSuperMapperClass("com.kun.SuperMapper")
                    .setEntityLombokModel(false)
                    .setInclude(include)
                    .setRestControllerStyle(true)
//                .setSuperEntityColumns("id")
                    .setControllerMappingHyphenStyle(true);
            mpg.setStrategy(strategy);

            //自定义配置
            String fileOutput = properties.getProperty("injection.fileOutput");
            String mapper = properties.getProperty("injection.mapper");

            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig(fileOutput) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + mapper + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//                return projectPath + "/kun-cloud-uc/kun-cloud-uc-biz/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null));



            // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
