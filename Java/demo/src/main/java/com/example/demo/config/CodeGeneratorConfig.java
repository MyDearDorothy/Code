package com.example.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author ZhangCheng
 * @version 1.0
 * @date 2022/1/6 19:34
 */
public class CodeGeneratorConfig {

    private static final String URL="jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String DRIVERNAME="com.mysql.cj.jdbc.Driver";
    private static final String USERNAME="root";
    private static final String PASSWORD="12345";
    private static final String PATH="E:\\Program\\Code\\Java\\demo\\src\\main\\java";
    private static final String AUTHOR="ZhangCheng";
    private static final String PACKAGE="com.example.demo";
    private static String[] table_name={"user_Infor"};

    public static void main(String[] args){
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(PATH)                      //生成文件的输出目录
                .setAuthor(AUTHOR)                           //设置作者
                .setFileOverride(true)                       //是否覆盖文件
                .setOpen(false);                             //生成后打开文件

        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                        .setTypeConvert(new MySqlTypeConvert())
                        .setUrl(URL)
                        .setDriverName(DRIVERNAME)
                        .setUsername(USERNAME)
                        .setPassword(PASSWORD);

        // 3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)                                  //全局大小写命名
                      .setNaming(NamingStrategy.underline_to_camel)          //下划线转驼峰
                      .setColumnNaming(NamingStrategy.underline_to_camel)
                      .setTablePrefix("tbl_")
                      .setEntityLombokModel(true)                            //使用lombok
                      .setInclude(table_name);                              //生成使用的表

        // 4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE)
            .setMapper("mapper")
            .setService("service")
            .setController("controller")
            .setEntity("entity");


        // 5.执行
        mpg.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pc);
        mpg.execute();
    }
}
