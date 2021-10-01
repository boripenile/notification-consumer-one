package com.pangaea.notification.consumer;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.pangaea.notification.consumer.controller.ConsumerController;
import com.pangaea.notification.consumer.events.ConsumerEventManager;


@WebMvcTest({ ConsumerController.class })
public class ConsumerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ConsumerEventManager consumerEventManager;
	
	final String TOPIC = "topic1";
	final String SUBSCRIBER_ONE_PATH = "/test1";
	final String SUBSCRIBER_ONE_URL = "http://localhost:9000/test1";
	
	@Test
	public void testfetchMessageReturnTopicAndDataIfSubscribed() throws Exception{
		JSONObject data = new JSONObject();
		data.put("name", "Murtadha Ali");
		data.put("greeting", "How are you guys?");
		
		JSONObject fetchedMessage = new JSONObject();
		fetchedMessage.put("topic", TOPIC);
		fetchedMessage.put("data", data);
		
		Mockito.when(consumerEventManager.readCurrentMessage(SUBSCRIBER_ONE_URL))
				.thenReturn(fetchedMessage);
		
		MockHttpServletRequestBuilder mockRequest 
			= MockMvcRequestBuilders.get(SUBSCRIBER_ONE_PATH)
			  .contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.message", is("No message available")));
	}
	
}
