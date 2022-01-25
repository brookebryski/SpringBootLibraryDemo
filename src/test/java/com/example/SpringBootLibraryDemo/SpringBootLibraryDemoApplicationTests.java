package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Controller.LibraryController;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import com.example.SpringBootLibraryDemo.Service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
// use mockito for external dependencies

@SpringBootTest
class SpringBootLibraryDemoApplicationTests {

	@Autowired
	LibraryController con;
	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryService;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkBuildIDLogic()
	{
		LibraryService lib =new LibraryService();
		String id = lib.buildId("ZMAN", 24);
		assertEquals(id, "OLDZMAN24");
	}

	@Test
	public void addBookTest()

	{
		// mock
		Library lib = buildLibrary();
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyExists(lib.getId())).thenReturn(true);

		ResponseEntity response =con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
	}

	public Library buildLibrary()
	{
		Library lib = new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("sfe");
		lib.setAuthor("Rahul Shetty");
		lib.setId("sfe322");
		return lib;
	}
}
