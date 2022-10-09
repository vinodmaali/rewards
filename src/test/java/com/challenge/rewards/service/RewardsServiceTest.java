package com.challenge.rewards.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.rewards.exception.NoTransactionsFoundException;
import com.challenge.rewards.model.RewardsSummary;
import com.challenge.rewards.service.impl.RewardsServiceImpl;

@SpringBootTest
public class RewardsServiceTest {
	@InjectMocks
	RewardsServiceImpl rewardsService;
	
	@Test
	public void testGetRewardPointsByUserIdWithValidInput() throws Exception {	
		List<RewardsSummary> response= rewardsService.calculateRewardsForACustomer(3,"123");
		assertTrue(!response.isEmpty());
		assertNotNull(response.size()==2);
	}
	
	@Test
	public void testGetRewardPointsByUserIdWithInValidInput() {
		assertThrows(NoTransactionsFoundException.class, ()-> rewardsService.calculateRewardsForACustomer(3,"456"));			
	}

}
