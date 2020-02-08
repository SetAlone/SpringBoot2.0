package com.itlaoqi.springbootspringbootapplication.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author caiww
 * @date 2019/12/18 -12:08
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run..." + Arrays.asList());
    }
}
