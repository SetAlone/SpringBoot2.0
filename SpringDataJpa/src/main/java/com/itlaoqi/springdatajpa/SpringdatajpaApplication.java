package com.itlaoqi.springdatajpa;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class SpringdatajpaApplication {
    @Bean //手动初始化DruidDataSource 对象
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    //注册后台界面Servlet entity , 用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建StatViewServlet，绑定到/druid/路径下
        //开启后，访问localhost/druid就可以看到druid管理后台
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet() , "/druid/*");
        Map<String ,String > param = new HashMap<String,String>();
        param.put("loginUsername" , "admin");
        param.put("loginPassword" , "123456");
        param.put("allow" , "");//哪些IP允许访问后台“”代表所有地址
        param.put("deny" , "33.31.51.88");//不允许这个IP访问
        bean.setInitParameters(param);
        return bean;
    }

    //用于监听获取应用的数据 ， Filter用于收集数据, Servlet用于展现数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter()); //设置过滤器
        bean.addUrlPatterns("/*");
        Map<String,String> param = new HashMap<String,String>();
        //排除静态资源
        param.put("exclusions" , "*.png,*.woff,*.js,*.css,/druid/*");
        bean.setInitParameters(param);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }
}