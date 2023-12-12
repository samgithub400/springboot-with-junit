package com.ios.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//This annotation is used to active the JPA components....
@DataJpaTest
//This annotation is used to enable in-memory database instead of application connected to the actual database....
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

  @Autowired
  private  EmployeeRepository employeeRepository;

  @BeforeEach
  void setUp() {

  }

  @AfterEach
  void tearDown() {

  }
}
