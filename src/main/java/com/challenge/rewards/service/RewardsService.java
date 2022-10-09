package com.challenge.rewards.service;

import java.util.List;

import com.challenge.rewards.exception.NoTransactionsFoundException;
import com.challenge.rewards.exception.ServiceException;
import com.challenge.rewards.model.RewardsSummary;

public interface RewardsService {
	
	public List<RewardsSummary> calculateRewardsForACustomer(int noOfMonths ,String customerId) throws NoTransactionsFoundException, ServiceException;

}
