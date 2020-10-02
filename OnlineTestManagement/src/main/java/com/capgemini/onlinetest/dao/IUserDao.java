package com.capgemini.onlinetest.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlinetest.entities.User;

public interface IUserDao extends JpaRepository<User, Long> {

}
//