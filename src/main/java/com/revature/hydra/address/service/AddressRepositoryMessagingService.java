package com.revature.hydra.address.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.beans.Address;

@Service
public class AddressRepositoryMessagingService {
	@Autowired
	private AddressRepositoryRequestDispatcher requestDispatcher;

	@RabbitListener(queues = "revature.hydra.repos.address")
	public Address receiveSingleAddressRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return requestDispatcher.processSingleAddressRequest(request);
	}

	@RabbitListener(queues = "revature.hydra.repos.address.list")
	public List<Address> receiveListAddressRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return requestDispatcher.processListAddressRequest(request);
	}
}
