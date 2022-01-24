package com.example.SpringBootLibraryDemo.Repository;

import com.example.SpringBootLibraryDemo.Controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
}
