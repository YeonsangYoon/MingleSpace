package com.minglespace;

import com.minglespace.config.ServerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MinglespaceApplication implements CommandLineRunner {
    private final ServerConfig serverConfig;

    public static void main(String[] args) {
        SpringApplication.run(MinglespaceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================================================");
        System.out.println("Yaml FIle : " + serverConfig.getName());
        System.out.println("environment : " + serverConfig.getEnvironment());
        System.out.println("host server : " + serverConfig.getServer());
        System.out.println("==============================================================================");
    }
}
