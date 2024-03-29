package user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResourceController {
	
	@Autowired
	private UserDaoService service;
	
//Get /users
//retrieveAllUsers
	
@GetMapping(path = "/users/")
public List<User> retrieveAllUsers(){
	return service.findAll();
}

//GET /users/{id}
//retrieveUser(int id)
@GetMapping(path = "/user/{id}")
public User retrieveUser(@PathVariable int id) {
	User user = service.findOne(id);
	
	if(user == null)
		throw new UserNotFoundException("id-"+id);

	
//"all-users", SERVER_PATH + "/users"
	//retrieveAllUsers
	Resource<User> resource = new Resource<User>(user);
	
	
	
			
	
	return user;
}

// POST - 
// input - details of user
// output - CREATED & Return the created URI

//HATEOAS



@PostMapping("/users")
public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
	User savedUser = service.save(user);
	// CREATED
	// /user/{id} savedUser.getId
URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}")
	.buildAndExpand(savedUser.getId()).toUri();
	
	return ResponseEntity.created(location).build();
	
}
@DeleteMapping(path = "/user/delete/{id}")
public void deleteUser(@PathVariable int id) {
	User user = service.deleteById(id);
	
	if(user == null)
		throw new UserNotFoundException("id-"+id);
}

}
