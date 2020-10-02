package com.capgemini.onlinetest.service;

import java.math.BigInteger;
import java.util.List;

import com.capgemini.onlinetest.entities.Test;



public interface ITestService {
	
	Test addTest(Test test);

	Test updateTest(BigInteger testId, Test test);

	Test deleteTest(BigInteger testId);

	boolean assignTest(Long userId, BigInteger testId);

	List<Test> fetchAll();

	Test findById(BigInteger testId);
}
//
