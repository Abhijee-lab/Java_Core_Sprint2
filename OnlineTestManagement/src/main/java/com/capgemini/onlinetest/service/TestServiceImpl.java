package com.capgemini.onlinetest.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onlinetest.dao.ITestDao;
import com.capgemini.onlinetest.dao.IUserDao;
import com.capgemini.onlinetest.entities.Test;
import com.capgemini.onlinetest.entities.User;
import com.capgemini.onlinetest.exception.TestNotFoundException;
@Service
@Transactional
public class TestServiceImpl implements ITestService {
   
   @Autowired
   private IUserDao userDao;
	
   @Autowired
	private ITestDao testDao;
	
	
	/*
	 ***************************************************
	 *This method is used to add new test
	 *************************************************** 
	 */
	
	@Override
	public Test addTest(Test test) 
	{	
			test = testDao.save(test);
			return test;
	}
	
	/*
	 ***************************************************
	 *This method is used to update existing test
	 *************************************************** 
	 */
	

	@Override
	public Test updateTest(BigInteger testId, Test test) {
		boolean exists = testDao.existsById(testId);
		if (exists) {
			test = testDao.save(test);
			return test;
		}
		throw new TestNotFoundException("Test not found for id="+testId);
	}
	
	/*
	 ***************************************************
	 *This method is used to delete existing test
	 *************************************************** 
	 */

	@Override
	public Test deleteTest(BigInteger testId) {
		Test test = findById(testId);
		testDao.delete(test);
		return test;
	}
	
	/*
	 ***************************************************
	 *This method is used to assign test to a user
	 *************************************************** 
	 */

	@Override
	public boolean assignTest(Long userId, BigInteger testId) {
		boolean testExists = testDao.existsById(testId);
		if (testExists) 
		{
				Test test = findById(testId);
				Optional<User> optional = userDao.findById(userId);
				if(optional.isPresent())
				{
					User user = optional.get();
					user.setUserTest(test);
					userDao.save(user);
				}
				
				
				return true;
		}
		else
		{
			throw new TestNotFoundException("Test not found for id="+testId);
		}
	}



	@Override
	public Test findById(BigInteger testId) {
		 Optional<Test>optional=testDao.findById(testId);
	     if(optional.isPresent()){
	         Test test=optional.get();
	         return test;
	     }
	     throw new TestNotFoundException("Test not found for id="+testId);
	    }
	@Override
	public List<Test> fetchAll() {
		List<Test> tests = testDao.findAll();
		return tests;
	}
	}


//