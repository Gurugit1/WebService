package com.mitchellInternational.claimwebservice;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.mitchellInternational.dao.JDBCClaimDAO;
import com.mitchellInternational.vo.Claim;

import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;



@Path("/CreateClaim")
public class ClaimService {
	
	ServeRequest request = new ServeRequest();
	JDBCClaimDAO dao = new JDBCClaimDAO();
	 
	    @POST
	    @Path("/create")
	    @Consumes("application/xml")
	    public Response createClaim(InputStream incomingData) {
	        StringBuilder stringBuilder = new StringBuilder();
	        try {
	            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
	            String line = null;
	            while ((line = in.readLine()) != null) {
	                stringBuilder.append(line);
	            }
	            request.createClaim(stringBuilder.toString());
	        } catch (Exception e) {
	            System.out.println(e.getMessage()+"Error Parsing: - ");
	        }
	        System.out.println("Data Received: " + stringBuilder.toString());

	        return Response.status(200).entity(stringBuilder.toString()).build();
	    
	    }
	    
	    @GET
	    @Path("/read")
	    @Produces("TEXT_PLAIN")
	    public String getClaim(@QueryParam("ClaimNumber") String claimNumber) {
	    	StringBuilder result =new StringBuilder();
	        List<Claim> tempResult =dao.listClaim(claimNumber);
	        for(int i=0;i<tempResult.size();i++){
	        	result.append(tempResult.get(i).getClaimantFirstName() +","+ tempResult.get(i).getClaimantLastName()
	        		+","+tempResult.get(i).getLossDate()+","+tempResult.get(i).getAssignedAdjusterID()+","+tempResult.get(i).getStatus());
	        	result.append("/n");
	        }
	        return result.toString();	        		
	    }

	    @GET
	    @Path("/read/bylossdate")
	    @Produces("TEXT_PLAIN")
	    public String getClaimByLossDate(@QueryParam("LossDateStart") String startDate, @QueryParam("LossDateEnd") String endDate) {
	    	StringBuilder result =new StringBuilder();
	        List<Claim> tempResult =dao.listClaimByDate(startDate, endDate);
	        for(int i=0;i<tempResult.size();i++){
	        	result.append(tempResult.get(i).getClaimantFirstName() +","+ tempResult.get(i).getClaimantLastName()
	        		+","+tempResult.get(i).getLossDate()+","+tempResult.get(i).getAssignedAdjusterID()+","+tempResult.get(i).getStatus());
	        	result.append("/n");
	        }
	        return result.toString();
	    }
	  
}
