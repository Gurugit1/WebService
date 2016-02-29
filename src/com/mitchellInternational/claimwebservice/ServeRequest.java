package com.mitchellInternational.claimwebservice;


import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.mitchellInternational.dao.JDBCClaimDAO;
import com.mitchellInternational.vo.Claim;

public class ServeRequest {

	  JDBCClaimDAO dao = new JDBCClaimDAO();
	public void createClaim(String s){
    try {
        JAXBContext jc = JAXBContext.newInstance(Claim.class);
        Unmarshaller ums = jc.createUnmarshaller();
        InputSource inputSource = new InputSource( new StringReader(s) );
        Claim claim = (Claim) ums.unmarshal(inputSource);
        dao.createClaim(claim);
        
        
    } catch (Exception e) {
        
        System.out.print(e.getMessage());
        
    }
   }

}