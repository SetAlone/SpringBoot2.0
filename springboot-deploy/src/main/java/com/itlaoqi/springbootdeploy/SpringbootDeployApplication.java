package com.itlaoqi.springbootdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * debug启动
 * 热部署的配置过程
 * 1、关闭页面缓存
 *      spring.thymeleaf.cache=false
 * 2、在xml中定义devtolls
 *      <dependency>
 *          <groupId>org.springframework.boot</groupId>
 *          <artifactId>spring-boot-devtools</artifactId>
 *      </dependency>
 *    在/maven-plugin中增加
 *       <plugin>
 *           <groupId>org.springframework.boot</groupId>
 *           <artifactId>spring-boot-maven-plugin</artifactId>
 *           <configuration>
 *               <!--增加fork才允许热部署，fork交叉 -->
 *               <fork>true</fork>
 *           </configuration>
 *       </plugin>
 * 3、修改idea的设置
 *    ctrl+alt+s 快速打开settings，选项卡build-->complier-->build project automaticlly勾选
 *    ctrl+shift+a 快速打开，搜索registry --> 确保complier.automake...被勾选
 */
@SpringBootApplication
public class SpringbootDeployApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDeployApplication.class, args);
    }

}
