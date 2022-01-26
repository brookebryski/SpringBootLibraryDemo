package com.example.SpringBootLibraryDemo.Service;

import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service is for business logic
// Do not write all of your code in controller
@Service
public class LibraryService {

    @Autowired
    LibraryRepository repository;

    public String buildId(String isbn, int aisle)
    {
        if(isbn.startsWith("Z"))
        {
            return "OLD"+isbn+aisle;
        }
        return isbn+aisle;
    }

    public boolean checkBookAlreadyExists(String id)
    {
       Optional<Library> lib=repository.findById(id);
       if(lib.isPresent())
           return true;
       else
           return false;

    }

    public Library getBookById(String id)
    {
        return repository.findById(id).get();
    }
}
