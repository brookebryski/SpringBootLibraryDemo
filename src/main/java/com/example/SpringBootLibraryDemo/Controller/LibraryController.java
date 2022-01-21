package com.example.SpringBootLibraryDemo.Controller;

import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import com.example.SpringBootLibraryDemo.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LibraryController {

    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
    {
        String id = libraryService.buildId(library.getIsbn(), library.getAisle());
        AddResponse ad = new AddResponse();
        if(!libraryService.checkBookAlreadyExists(id))
        {

            library.setId(id);
            repository.save(library);
            HttpHeaders headers = new HttpHeaders();
            headers.add("unique", id);
            ad.setMsg("Success book is added");
            ad.setId(id);
            // return ad;
            return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
        }
        // add book details into database
        else
        {
            ad.setMsg("Book already exists.");
            ad.setId(id);
            return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
        }
           // write the code to tell book already exists
        }
    }

