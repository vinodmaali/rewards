package com.challenge.rewards.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.challenge.rewards.exception.NoTransactionsFoundException;
import com.challenge.rewards.exception.ServiceException;
import com.challenge.rewards.model.RewardsSummary;
import com.challenge.rewards.model.Transaction;
import com.challenge.rewards.service.RewardsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RewardsServiceImpl implements RewardsService{

	@Override
	public List<RewardsSummary> calculateRewardsForACustomer(int noOfMonths , String customerId)
			throws NoTransactionsFoundException, ServiceException {
		List<Transaction> transactionsInThePastNMonths= getLastNMonthTransactions(noOfMonths, customerId);
		if(transactionsInThePastNMonths.isEmpty()) {
			throw new NoTransactionsFoundException("No transactions have been found for the customer "+customerId + " in the past "+noOfMonths+" months");
		}
		
		Map<LocalDate, List<Transaction>> transactionsByMonth = transactionsInThePastNMonths.stream()
		        .collect(Collectors.groupingBy(d -> LocalDate.parse(d.getTransactionDate(),  DateTimeFormatter.ofPattern("yyyy-MM-dd")).withDayOfMonth(1)));
	
		List<RewardsSummary> rewards= new ArrayList<RewardsSummary>();
		for(LocalDate key: transactionsByMonth.keySet()) {
			List<Transaction> transactions= transactionsByMonth.get(key);
			int totalRewardPoints= 0;
			for(Transaction t: transactions) {
				totalRewardPoints+= calculateRewardPointsFromDollarsSpent(t.getDollarsSpent());
			}
			RewardsSummary rewardsSummary = new RewardsSummary();
			rewardsSummary.setMonth(key.getMonth().name());
			
			rewardsSummary.setRewardPoints(totalRewardPoints);
			rewards.add(rewardsSummary);
		}
		
		return rewards;
	}
	
	
	private int calculateRewardPointsFromDollarsSpent(int dollarsSpent) {
		int rewardPoints=0;
		int over100 = dollarsSpent - 100;
	    
	    if (over100 > 0) {
	    	rewardPoints += (over100 * 2);
	    }    
	    if (dollarsSpent > 50) {
	    	rewardPoints += 50;      
	    }
	    return rewardPoints;
	}


	private List<Transaction> getLastNMonthTransactions(int noOfMonths, String customerId) throws ServiceException {
		List<Transaction> allTransactions=  getAllTransactions();
		
		return allTransactions.stream().filter(transaction-> transaction.getCustomerId().equalsIgnoreCase(customerId)
				&& isTransactionIsInLastNMonths(noOfMonths, transaction.getTransactionDate())).collect(Collectors.toList());
	}


	private boolean isTransactionIsInLastNMonths(int noOfMonths, String transactionDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate transactionDate= LocalDate.parse(transactionDateString, formatter);
		LocalDate currentDate= LocalDate.now();
		int diff= currentDate.getMonth().getValue()- transactionDate.getMonth().getValue();
		return diff <=noOfMonths;
	}


	private List<Transaction> getAllTransactions() throws ServiceException {
		List<Transaction> transactions = new ArrayList<>();		

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Resource resource = new ClassPathResource("transactions.json");
			InputStream inputStream = resource.getInputStream();
			byte[] fileData = FileCopyUtils.copyToByteArray(inputStream);
			String outputString = new String(fileData);
			transactions = objectMapper.readValue(outputString, new TypeReference<List<Transaction>>(){});
		} catch (IOException e) {
			throw new ServiceException("Failed to get transactions, please try later");
		}
		return transactions;
	}

}
