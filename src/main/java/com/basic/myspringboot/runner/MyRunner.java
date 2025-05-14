package com.basic.myspringboot.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("${myboot.name} = " + name);
        System.out.println("${myboot.age} = " + age);

        System.out.println("VM argument-foo : " + args.containsOption("foo"));
        System.out.println("Program argument-bar : " + args.containsOption("bar"));

        //Iterable forEach(Comsumer) : 함수형 인터페이스 void accept(T t)
        //Comsumer 의 추상 메서드 오버라이딩 -> 람다식으로

        //Program Argument 목록 출력
        args.getOptionNames()
                .forEach( name -> System.out.println(name));

    }//run

}
