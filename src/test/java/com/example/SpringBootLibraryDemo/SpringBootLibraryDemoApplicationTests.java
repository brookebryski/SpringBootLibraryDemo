package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Controller.AddResponse;
import com.example.SpringBootLibraryDemo.Controller.Library;
import com.example.SpringBootLibraryDemo.Controller.LibraryController;
import com.example.SpringBootLibraryDemo.Repository.LibraryRepository;
import com.example.SpringBootLibraryDemo.Service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


// use mockito for external dependencies
// mockito is used to mock your methods, but mockmvc is used to mock your service calls
@SpringBootTest
@AutoConfigureMockMvc

class SpringBootLibraryDemoApplicationTests {

	@Autowired
	LibraryController con;
	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryService;
	@Autowired
	private MockMvc mockMvc;

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
		when(libraryService.checkBookAlreadyExists(lib.getId())).thenReturn(false);
		when(repository.save(any())).thenReturn(lib);

		ResponseEntity response =con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		AddResponse ad= (AddResponse) response.getBody();
		ad.getId();
		assertEquals(lib.getId(), ad.getId());
		assertEquals("Success Book is Added",ad.getMsg());

		// call Mock service from code
	}

	@Test
	public void addBookControllerTest()
	{
		Library lib = buildLibrary();
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(lib);
;
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.checkBookAlreadyExists(lib.getId())).thenReturn(false);
		when(repository.save(any())).thenReturn(lib);
		this.mockMvc.perform(post("/addBook")).contentType(MediaType.APPLICATION_JSON);
		.content(jsonString)).andExpect(status().isCreated());

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
