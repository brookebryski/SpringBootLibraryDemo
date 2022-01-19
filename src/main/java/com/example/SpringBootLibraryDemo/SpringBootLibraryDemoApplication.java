package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLibraryDemoApplication implements CommandLineRunner {

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLibraryDemoApplication.class, args);
	}

	@Override
	public void run(String[] args)
	{
		Library lib = repository.findById("fdsefr343").get();
		System.out.printf(lib.getAuthor());
	}

}
