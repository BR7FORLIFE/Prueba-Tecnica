package com.archives.backend.security.command;

import org.springframework.boot.CommandLineRunner;

public class UserCommandSeeder implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Desde command line runner!");
    }
}
