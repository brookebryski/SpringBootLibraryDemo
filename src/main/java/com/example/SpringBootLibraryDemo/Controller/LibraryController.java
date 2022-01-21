package com.example.SpringBootLibraryDemo.Controller;

import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
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

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library)
    {
        AddResponse ad = new AddResponse();
        String id = library.getIsbn()+library.getAisle();
        library.setId(id);
        repository.save(library);
        HttpHeaders headers = new HttpHeaders();
        headers.add("unique", id);
        ad.setMsg("Success book is added");
        ad.setId(id);
       // return ad;
        return new ResponseEntity<AddResponse>(ad,headers,HttpStatus.CREATED);
        // add book details into database
        // jpa repository -save
    }
}
