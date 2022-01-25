package com.example.SpringBootLibraryDemo;

import com.example.SpringBootLibraryDemo.Service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootLibraryDemoApplicationTests {

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
}
