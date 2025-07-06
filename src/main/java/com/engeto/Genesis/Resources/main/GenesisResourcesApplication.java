package com.engeto.Genesis.Resources.main;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.engeto.Genesis.Resources")
@SpringBootApplication
public class GenesisResourcesApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenesisResourcesApplication.class, args);
    }
}
