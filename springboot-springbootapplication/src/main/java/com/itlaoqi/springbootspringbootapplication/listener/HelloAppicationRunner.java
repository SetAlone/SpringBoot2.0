package com.itlaoqi.springbootspringbootapplication.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author caiww
 * @date 2019/12/18 -12:06
 */
@Component
public class HelloAppicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner...run..");
    }
}
