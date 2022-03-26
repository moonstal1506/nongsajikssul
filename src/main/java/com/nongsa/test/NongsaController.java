package com.nongsa.test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NongsaController {
	
	//http://localhost:9000/test/hello
	@GetMapping("test/hello")
	public  String hello() {
		return "<h1>hello springboot</h1>";
	}
}
