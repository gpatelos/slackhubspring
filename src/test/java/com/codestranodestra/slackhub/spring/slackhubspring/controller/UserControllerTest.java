package com.codestranodestra.slackhub.spring.slackhubspring.controller;

import com.codestranodestra.slackhub.spring.slackhubspring.model.User;
import com.codestranodestra.slackhub.spring.slackhubspring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User mockUser = new User("WorstMovieEver");
    private User anotherMockUser = new User("TheFuckIsSnoke");
    private Collection<User> mockAllUsers = Arrays.asList(mockUser, anotherMockUser);

    private String exampleUserJSON = "{\"userId\":1,\"name\":\"Joseph\",\"userName\":\"j123\",\"password\":\"masboi\"}";

    @Test
    public void getAllUsersTest() throws Exception {

        Mockito.when(
                userService.getAllUsers()).thenReturn(mockAllUsers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


        String expected = "[{\"userId\":1,\"name\":\"WorstMovieEver\",\"userName\":null,\"password\":null}," +
                "{\"userId\":2,\"name\":\"TheFuckIsSnoke\",\"userName\":null,\"password\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getUserByIdTest() throws Exception {

        Mockito.when(
                userService.getUserById(Mockito.anyInt())).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users/1").accept(
                MediaType.APPLICATION_JSON).content(exampleUserJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"userId\":1,\"name\":\"WorstMovieEver\",\"userName\":null,\"password\":null}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createUserTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON).content(exampleUserJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/users/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void updateUserTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/users/1")
                .accept(MediaType.APPLICATION_JSON).content(exampleUserJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        System.out.println(result.getResponse());

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/users/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void deleteUserTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/users/2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.GONE.value(), response.getStatus());
//        assertEquals("http://localhost/users/2",
//                response.getHeader(HttpHeaders.LOCATION));
    }

}
