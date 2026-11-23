package com.arohau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("app sout");
        System.out.println("http://localhost:8080/actuator/beans");
        System.out.println("Now Ctrl + F in browser: com.arohau");
        System.out.println("Notice 3 beans: app; higherLevelService_1; backupService_1");
    }

    /**
     * This bean will only be created if the class "com.arohau.App" is present in the application's classpath.
     * SO THE BEAN WILL BE CREATED IN THIS EXAMPLE
     */
    @Bean
    @ConditionalOnClass(name = "com.arohau.App")
    public String higherLevelService_1() {
        return "HigherLevelService_1 bean created because 'com.arohau.App' is present.";
    }

    /**
     * This bean will only be created if the class "com.arohau.App" is present in the application's classpath.
     * SO THE BEAN IS WILL NOT BE CREATED IN THIS EXAMPLE
     */
    @Bean
    @ConditionalOnClass(name = "com.arohau.SomeMissingClass")
    public String higherLevelService_2() {
        return "HigherLevelService_2 bean created because 'com.arohau.App' is present.";
    }

    /**
     * This bean will only be created if the class "com.arohau.SomeMissingClass"
     * is NOT present in the application's classpath.
     * SO THE BEAN WILL BE CREATED IN THIS EXAMPLE
     */
    @Bean
    @ConditionalOnMissingClass("com.arohau.SomeMissingClass")
    public String backupService_1() {
        return "BackupService_1 bean created because 'com.arohau.SomeMissingClass' is missing.";
    }

    /**
     * This bean will only be created if the class "com.arohau.SomeMissingClass"
     * is NOT present in the application's classpath.
     * SO THE BEAN IS WILL NOT BE CREATED IN THIS EXAMPLE
     */
    @Bean
    @ConditionalOnMissingClass("com.arohau.App")
    public String backupService_2() {
        return "BackupService_2 bean created because 'com.arohau.SomeMissingClass' is missing.";
    }
}
