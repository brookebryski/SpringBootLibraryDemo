package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootLibraryDemoApplication {
	// implements CommandLineRunner

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLibraryDemoApplication.class, args);
	}
}
//	@Override
//	public void run(String[] args)
//	{
//		Library lib = repository.findById("fdsefr343").get();
//		System.out.println(lib.getAuthor());
//		Library en = new Library();
//		en.setAisle(123);
//		en.setAuthor("Rahul");
//		en.setBook_name("Devops");
//		en.setIsbn("lkhs");
//		en.setId("lkhs123");
		// repository.save(en);
//		List<Library> allrecords =  repository.findAll();
		//
//		for(Library item : allrecords)
//		{
//			System.out.println(item.getBook_name());
//		}
//		repository.delete(en);
//	}


// HTTP Request - POST
// End Point - http://localhost:8080
// Resource - /addBook
// Status Code - 201

// Body / Payload:
// {
//	  "isbn": "studio",
//	  "aisle": 201622,
//    "author":"shetty",
//	  "book_name":"selenium"
//	}

// If book was added to table successfully:
// Response:
// {
//	  "msg": "Success Book is Added",
//    "id": "studio201622"
// }

// If book is already present in table:
// Response:
// {
//	  "msg": "Book already exists",
//     "id": ""
// }
// Status Code - 202

// Get Book by ID:
// Resource: getBooks/{ID}
// HTTP Method: GET
// Response:
// {
//   "id":"mnbc234",
//   "isbn": "mnbc",
//   "aisle":234,
//   "author": "Rahul ShettyTrainer",
//   "bookName":"Python"
// }

// Get Book by Author:
// Resource: getBooks/author?authorname=rahul
// HTTP Method: GET
// Response:
// {
//	  "id":"mnbc234",
//    "isbn": "mnbc",
//    "aisle": 234,
//    "author": "Rahul ShettyTrainer",
//    "bookName":"Python"
// }

// Update Book
// HTTP Request - POST (or PUT)
// End Point - /updateBook/{id}
// Status Code - 200
// Body/Payload:
// {
//	   "aisle":201622,
//     "author": "shetty",
//     "book_name": "selenium"
// }

// Delete Book
// HTTP Request - POST
// End Point - http://localhost:8080
// Resource - deleteBook
// Status Code - 201
// Body/Payload
// {
//   "id":"dsfrw"
// }
// Response: "Book is deleted"

// Get All Books:
// Resource: /getBooks
// Http Method: GET
// Response:
// {
//   "id":"mnbc234",
//   "isbn": "mnbc",
//   "aisle":234,
//   "author": "Rahul ShettyTrainer",
//   "bookName":"Python"
// }

// Security:
// Add security dependency to Pom
// Run server
// Copy and paste password from terminal into Postman authorization basic auth