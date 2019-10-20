package user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//controller
@RestController
public class HelloWorldController {


	
	
	//GET
	//URI - /hello world
	// method - "Hello World"
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean HelloWorldBean()	{
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path =  "/hello-world-path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
}
