/*
package com.ios.test;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YourApiTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private YourService yourService; // Replace with the actual service class used in your application

  @Test
  public void testYourApi() {
    // Mock the response from the service
    when(yourService.someMethod()).thenReturn("MockedResponse");

    // Make a request to your API endpoint using RestTemplate
    ResponseEntity response = restTemplate.exchange(
        "/your-api-endpoint", // Replace with your actual API endpoint
        HttpMethod.GET,
        null,
        String.class);

    // Verify the response
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("MockedResponse", response.getBody());
  }
}*/
