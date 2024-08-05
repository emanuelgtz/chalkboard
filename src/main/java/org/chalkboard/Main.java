package org.chalkboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class Main {
  public static void main(String[] args) {

    System.out.println("Application running");
    SpringApplication.run(Main.class,args);
  }



}
