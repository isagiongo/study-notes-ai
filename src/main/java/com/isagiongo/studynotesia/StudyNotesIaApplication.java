package com.isagiongo.studynotesia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.isagiongo.studynotesia"})
public class StudyNotesIaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyNotesIaApplication.class, args);
    }

}
