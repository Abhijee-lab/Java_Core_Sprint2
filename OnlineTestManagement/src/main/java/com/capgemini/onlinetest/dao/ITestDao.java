package com.capgemini.onlinetest.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlinetest.entities.Test;



public interface ITestDao extends JpaRepository<Test,BigInteger>{

}
//