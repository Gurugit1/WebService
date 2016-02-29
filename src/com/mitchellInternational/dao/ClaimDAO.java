package com.mitchellInternational.dao;

import java.util.List;

import com.mitchellInternational.vo.Claim;

public interface ClaimDAO {
	 public void createClaim(Claim claim);
	    public List<Claim> listClaim(String claimNumber);
	    public List<Claim> listClaimByDate(String startDate, String endDate);
		

}
