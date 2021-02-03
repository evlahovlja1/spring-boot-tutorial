package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Student elvir = new Student("Elvir", "evlahovlja1@etf.unsa.ba", LocalDate.of(2000, 1, 24));
                Student camil = new Student("camil", "camilcamke@etf.unsa.ba", LocalDate.of(2020, 1, 1));
                repository.saveAll(List.of(elvir, camil));
            }
        };
//        args -> {
//            Student elvir = new Student("Elvir", "evlahovlja1@etf.unsa.ba", LocalDate.of(2000, 1, 24));
//            Student camil = new Student("camil", "camilcamke@etf.unsa.ba", LocalDate.of(2020, 1, 1));
//            repository.saveAll(List.of(elvir, camil));
//        };
    }
}
