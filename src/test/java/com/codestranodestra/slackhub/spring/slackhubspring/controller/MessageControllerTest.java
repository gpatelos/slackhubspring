package com.codestranodestra.slackhub.spring.slackhubspring.controller;

import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;
import com.codestranodestra.slackhub.spring.slackhubspring.service.MessageService;
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
@WebMvcTest(value = MessageController.class, secure = false)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;

    private Message mockMessage = new Message("Worst Movie Eva", 1);
    private Message anotherMockMessage = new Message("WHO the fuck is Snoke",2);
    private Collection<Message> mockAllMessages = Arrays.asList(mockMessage, anotherMockMessage);

    private String exampleMessageJSON = "{\"messageId\":1,\"messageBody\":\"Hello buddy\",\"timeStamp\":null,\"userId\":null}";

    @Test
    public void getAllMessagesTest() throws Exception {

        Mockito.when(
                messageService.getAllMessages()).thenReturn(mockAllMessages);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


        String expected = "[{\"messageId\":1,\"messageBody\":\"Worst Movie Eva\"}," +
                "{\"messageId\":2,\"messageBody\":\"WHO the fuck is Snoke\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getMessageByIdTest() throws Exception {

        Mockito.when(
                messageService.getMessageById(Mockito.anyInt())).thenReturn(mockMessage);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/1").accept(
                MediaType.APPLICATION_JSON).content(exampleMessageJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"messageId\":1,\"messageBody\":\"Worst Movie Eva\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createMessageTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/messages")
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/messages/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void updateMessageTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/messages/1")
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        System.out.println(result.getResponse());

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/messages/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void deleteMessageTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/messages/2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/messages/2",
                response.getHeader(HttpHeaders.LOCATION));
    }

}
