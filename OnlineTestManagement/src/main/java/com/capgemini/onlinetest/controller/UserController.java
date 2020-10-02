package com.capgemini.onlinetest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinetest.entities.User;
import com.capgemini.onlinetest.service.IUserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger Log= LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService service;
	@PostMapping("/add")
	public ResponseEntity<User>addUser(@RequestBody @Valid User user )
	{
		User myUser=convertFromUser(user);
		User user1=service.addUser(myUser);
		  Log.info("User Added ");
		  System.out.println(user1);
		ResponseEntity<User>response=new ResponseEntity<User>(user1, HttpStatus.OK);
		return response;
	}
	public User convertFromUser(User user) {
		User user1=new User();
		user1.setUserId(user.getUserId());
		user1.setUserName(user.getUserName());
		user1.setUserPassword(user.getUserPassword());
		user1.setAdmin(user.isAdmin());
		return user1;
	}
	//
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid User user) {
		User myUser = convertFromUser(user);
		myUser.setUserId(userId);
		myUser = service.updateUser(userId, myUser);
		  Log.info("User updated ");
		ResponseEntity<User> response = new ResponseEntity<>(myUser, HttpStatus.OK);
		return response;
	}
	
	 @GetMapping("/remove/{id}")
		public ResponseEntity<Boolean> deleteTest(@PathVariable("id") Long userId) {
			User result = service.deleteUser(userId);
			  Log.info("User Removed ");
			ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);
			return response;
		}

	 
	 @GetMapping("/get/{id}")
	 public ResponseEntity<User>getTest(@PathVariable("id")Long userId){
			User user = service.findById(userId);
			ResponseEntity<User>response=new ResponseEntity<>(user, HttpStatus.OK);
			return response;
			
		  }
	 @GetMapping
	 @ResponseStatus(HttpStatus.OK)
	 	public List<User>fetchAll(){
		 List<User> user=service.fetchAll();
		 Log.info("Users fetched");
		 System.out.println(user);
		 return user;
	 }
	 	
	
	 
}
