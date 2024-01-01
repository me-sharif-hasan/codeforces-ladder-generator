package com.iishanto.codeforcesladdergenerator;

import com.iishanto.codeforcesladdergenerator.service.CodeforcesApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
public class CodeforcesLadderGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeforcesLadderGeneratorApplication.class, args);
    }

}
