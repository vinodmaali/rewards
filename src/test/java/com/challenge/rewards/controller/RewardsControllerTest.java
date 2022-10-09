package com.challenge.rewards.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.challenge.rewards.service.impl.RewardsServiceImpl;

@SpringBootTest
public class RewardsControllerTest {
	
	@InjectMocks
	RewardsController rewardsController;
	
	@BeforeEach
	public void setup() {
		rewardsController.rewardsService= new RewardsServiceImpl();
	}
	
	@Test
	public void testGetRewardPointsByUserIdWithValidInput() throws Exception {	
		ResponseEntity<?> response= rewardsController.getRewardPointsByUserId("123", 3);
		assertTrue(response.getStatusCode().value()==200);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetRewardPointsByUserIdWithInValidInput() throws Exception {
		ResponseEntity<?> response= rewardsController.getRewardPointsByUserId("789", 3);
		assertTrue(response.getStatusCode().value()==400);		
	}

}
