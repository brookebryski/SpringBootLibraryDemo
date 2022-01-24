package com.example.SpringBootLibraryDemo.Controller;

import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import com.example.SpringBootLibraryDemo.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

        @GetMapping("/getBooks/{id}")
        public Library getBookById(@PathVariable(value="id")String id)
        {
            try {
                Library lib = repository.findById(id).get();
                return lib;
            }
            catch(Exception e)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("getBooks/author")
        public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
        {
            return repository.findAllByAuthor(authorname);
        }

        @PutMapping("/updateBook/{id}")
        public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library)
        {
            Library existingBook = repository.findById(id).get();

            existingBook.setAisle(library.getAisle());
            existingBook.setAuthor(library.getAuthor());
            existingBook.setBook_name(library.getBook_name());
            repository.save(existingBook);
            return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
        }

        @DeleteMapping("/deleteBook")
        public ResponseEntity<String> deleteBookById(@RequestBody Library library)
        {
           Library libdelete =repository.findById(library.getId()).get();
           repository.delete(libdelete);
           return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);
        }
    }

