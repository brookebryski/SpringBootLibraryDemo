package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
		System.out.println(lib.getAuthor());
		Library en = new Library();
		en.setAisle(123);
		en.setAuthor("Rahul");
		en.setBook_name("Devops");
		en.getIsbn("lkhs");
		en.setId("lkhs123");
		// repository.save(en);
		List<Library> allrecords =  repository.findAll();
		//
		for(Library item : allrecords)
		{
			System.out.println(item.getBook_name());
		}
		repository.delete(en);
	}
}
