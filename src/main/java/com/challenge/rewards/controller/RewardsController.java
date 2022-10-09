package com.challenge.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rewards.exception.NoTransactionsFoundException;
import com.challenge.rewards.exception.ServiceException;
import com.challenge.rewards.model.RewardsSummary;
import com.challenge.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

	@Autowired
	RewardsService rewardsService;

	@GetMapping("/rewardsSummaryById/{customerId}")
	public ResponseEntity<?> getRewardPointsByUserId(@PathVariable String customerId, @RequestParam int pastMonths) {
		try {
			List<RewardsSummary> summary = rewardsService.calculateRewardsForACustomer(pastMonths,customerId);
			return new ResponseEntity<>(summary, HttpStatus.OK);
		} catch (NoTransactionsFoundException | ServiceException e) {
			if (e instanceof NoTransactionsFoundException) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
