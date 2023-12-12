package com.ios.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ios.dto.UserDTO;
import com.ios.model.User;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  String url;
  @Mock
  private RestTemplate restTemplate;
  @InjectMocks
  private UserService userService;
  private User user;

  @BeforeEach
  public void setUp() {

    //MockitoAnnotations.openMocks(this);
    user = User.builder().id(10).name("Ajay").email("ajay@gmail.com").gender("Male")
        .status("active").build();
    url = "https://gorest.co.in/public/v2/users";
  }

  @Test
  @DisplayName("This test is for Rest call..........")
  //@Disabled
  public void testGetUserData() {

    //String url = "https://gorest.co.in/public/v2/users";
    User user1 = new User(1, "Ajay", "ajay@gmail.com", "Male", "active");
    User user2 = new User(2, "Khwaja", "khwaja@gmail.com", "Male", "inactive");
    User user3 = new User(3, "Manu", "manu@gmail.com", "Female", "active");

    List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

    //stubbing OR setting the behaviour for restTemplate getForObject();
    when(restTemplate.getForObject(url, List.class)).thenReturn(userList);

    List<User> actualList = userService.getUserData();

    verify(restTemplate, times(1)).getForObject(url, List.class);
    assertThat(actualList.size()).isGreaterThan(0);
    assertEquals(userList.size(), actualList.size());


  }

  @Test
  //@Disabled
  @DisplayName("This test is for getAll active users....")
  public void testGetUsersByStatus() {

    //String url = "https://gorest.co.in/public/v2/users";
    User user1 = new User(1, "Ajay", "ajay@gmail.com", "Male", "active");
    User user2 = new User(2, "Khwaja", "khwaja@gmail.com", "Male", "inactive");
    User user3 = new User(3, "Manu", "manu@gmail.com", "Female", "active");

    List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

    String status = "inactive";

    //setting the behaviour...
    when(restTemplate.getForObject(url, List.class)).thenReturn(userList);

    List<UserDTO> userDtos = userService.getUsersByStatus(status);

    assertThat(userDtos.size()).isGreaterThan(0);
    assertThat(userDtos.get(0).getStatus()).isNotNull();
    assertThat(userDtos.get(0).getStatus()).isEqualTo(status);

  }


  @Test
  @DisplayName("This test is for get all users in group.....")
    //@Disabled
  void test_GetGroupOfUsers() {

    User user1 = new User(1, "Ajay", "ajay@gmail.com", "Male", "active");
    User user2 = new User(2, "Khwaja", "khwaja@gmail.com", "Male", "inactive");
    User user3 = new User(3, "Manu", "manu@gmail.com", "Female", "active");

    List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

    //setting the behaviour...
    when(restTemplate.getForObject(url, List.class)).thenReturn(userList);

    Map<String, List<User>> groupOfUsers = userService.getGroupOfUsers();

    assertThat(groupOfUsers.size()).isGreaterThan(0);
    assertThat(groupOfUsers.get("active").get(0)).isNotNull();
    assertThat(groupOfUsers.get("inactive").get(0)).isNotNull();

  }

  @Test
  @DisplayName("Ths test case for getting the list of users names by their status")
    //@Disabled
  void test_GetGroupOfUsersName() {

    User user1 = new User(1, "Ajay", "ajay@gmail.com", "Male", "active");
    User user2 = new User(2, "Khwaja", "khwaja@gmail.com", "Male", "inactive");
    User user3 = new User(3, "Manu", "manu@gmail.com", "Female", "active");

    List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

    //setting the behaviour...
    when(restTemplate.getForObject(url, List.class)).thenReturn(userList);

    //calling actual service method....
    Map<String, List<String>> groupOfUsersName = userService.getGroupOfUsersName();

    //comparing the result....
    assertThat(groupOfUsersName.size()).isGreaterThan(0);
    assertThat(groupOfUsersName.get("active").get(0)).isNotNull();
    assertThat(groupOfUsersName.get("inactive").get(0)).isNotNull();
  }

  @Test
  @DisplayName("This test case is for getting list of all users.....")
  //@Disabled
  void getUsers() {

    ArrayList<User> expected = new ArrayList<>(
        Arrays.asList(new User(501200, "Ak", "ak@gmail.com", "male", "active"),
            new User(501243, "PK", "pk@gmail.com", "male", "active"),
            new User(500254, "Jack", "jack@gmail.com", "male", "inactive"),
            new User(500250, "Manu", "manu@gmail.com", "female", "active"),
            new User(501239, "Anu", "anu@gmail.com", "female", "active")));

    //here no need to mock anything because service method not interacting with any other method.....

    //calling actual service method....
    List<User> actual = userService.getUsers();

    //comparing the results....

    assertThat(actual).isNotNull();
    assertThat(expected.size()).isEqualTo(actual.size());
    assertThat(expected.get(0).getName()).isEqualTo(actual.get(0).getName());




  }
}
