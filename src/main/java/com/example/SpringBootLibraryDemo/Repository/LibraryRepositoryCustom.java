package com.example.SpringBootLibraryDemo.Repository;

import com.example.SpringBootLibraryDemo.Controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<Library> findAllByAuthor(String authorName);
}
