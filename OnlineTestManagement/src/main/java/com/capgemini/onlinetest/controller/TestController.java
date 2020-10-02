package com.capgemini.onlinetest.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;


import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinetest.entities.Test;
import com.capgemini.onlinetest.service.ITestService;




@RestController
@RequestMapping("/tests")
public class TestController {
	
	  private static final Logger Log= LoggerFactory.getLogger(TestController.class);
	 @Autowired
	 private ITestService service;
	 @GetMapping("/get/{id}")
	  public ResponseEntity<Test>getTest(@PathVariable("id") BigInteger id){
		Test test = service.findById(id);
		Log.info("Test Fetched");
		ResponseEntity<Test>response=new ResponseEntity<>(test, HttpStatus.OK);
		System.out.println("This is detail"+test);
		return response;
		
	  }
	 
//	
	

	 @GetMapping
	 @ResponseStatus(HttpStatus.OK)
	   public List<Test>fetchAll(){
	       List<Test> tests=service.fetchAll();
	   
	       	Log.info("All Tests Fetched");
	       	System.out.println(tests);
	       
	       return tests;
	   }
	 
	 
	
	 @PostMapping("/add")
	    public ResponseEntity<Test>createTest(@RequestBody @Valid Test test){
	       Test myTest = convertFromTest(test);
	       test=service.addTest(myTest);
	       Log.info("Test created ");
	        ResponseEntity<Test>response=new ResponseEntity<>(test, HttpStatus.OK);
	        System.out.println(test);
	        return response;
	      
	   }
	 
	 @GetMapping("/delete/{id}")
		public ResponseEntity<Boolean> deleteTest(@PathVariable("id") BigInteger testId) {
			Test result = service.deleteTest(testId);
			  Log.info("Test removed");
			ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);
			return response;
		}

	 @PutMapping("/update/{id}")
		public ResponseEntity<Test> updateTest(@PathVariable("id") BigInteger testId, @RequestBody @Valid Test test) {
			Test myTest = convertFromTest(test);
			myTest.setTestId(testId);
			myTest = service.updateTest(testId, myTest);
			  Log.info("Test updated ");
			ResponseEntity<Test> response = new ResponseEntity<>(myTest, HttpStatus.OK);
			return response;
		}
	 
	 @GetMapping("/assign/{testId}/{userId}")
		public ResponseEntity<Boolean> assignTest(@PathVariable("testId") BigInteger testId,
				@PathVariable("userId") Long userId) {
			Boolean isAssign = service.assignTest(userId, testId);
			  Log.info("Test assigned ");
			ResponseEntity<Boolean> response = new ResponseEntity<>(isAssign, HttpStatus.OK);
			return response;
		}
	 public static Test convertFromTest(Test test) {
			Test myTest=new Test();
			myTest.setTestId(test.getTestId());
			myTest.setTestTitle(test.getTestTitle());
			myTest.setTestDuration(test.getTestDuration());
			myTest.setTestTotalMarks(test.getTestTotalMarks());
			//dto.setStartTime(test.getStartTime());
			//dto.setEndTime(test.getEndTime());
			return myTest;
		}

		
		
		
	 
}
