package com.challenge.rewards.model;

import lombok.Data;

@Data
public class RewardsSummary {
	private int rewardPoints;
	private String month;
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
}
