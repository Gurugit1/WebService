package com.mitchellInternational.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.mitchellInternational.vo.Claim;

public class JDBCClaimDAO implements ClaimDAO{
	 
    private static Connection connection = null;
 
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection == null)
                connection = DriverManager.getConnection("jdbc:mysql://localhost/MitchellInternation?user=root&password=123");
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
        return connection;
    }

	@Override
	public void createClaim(Claim claim) {
		
		String insertMitchellClaim = " insert into mitchellclaim (ClaimNumber, ClaimantFirstName, ClaimantLastName, Status, LossDate, AssignedAdjusterID)"
                + " values (?, ?, ?, ?, ?, ?)";

        String insertLossInfo = " insert into lossinfo (ClaimNumber, CauseOfLoss, ReportedDate, LossDescription) values (?, ?, ?, ?)";

        String insertVehicleDetails = " insert into vehicledetails (ClaimNumber, ModelYear, MakeDescription, ModelDescription, "
                + "EngineDescription, ExteriorColor, LicPlate, LicPlateState, LicPlateExpDate, DamageDescription, Mileage, Vin)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(insertMitchellClaim);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setString(2, claim.getClaimantFirstName());
            preparedStmt.setString(3, claim.getClaimantLastName());
            preparedStmt.setString(4, claim.getStatus());
            preparedStmt.setTimestamp(5, claim.getLossDate());
            preparedStmt.setLong(6, claim.getAssignedAdjusterID());
            preparedStmt.execute();
            
            preparedStmt = connection.prepareStatement(insertLossInfo);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setString(2, claim.getLossinfo().getCauseOfLoss());
            preparedStmt.setTimestamp(3, claim.getLossinfo().getReportedDate());
            preparedStmt.setString(4, claim.getLossinfo().getLossDescription());
            preparedStmt.execute(); 
            
            
            preparedStmt = connection.prepareStatement(insertVehicleDetails);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setInt(2, claim.getVehicles().getVehicleDetails().getModelYear());
            preparedStmt.setString(3, claim.getVehicles().getVehicleDetails().getMakeDescription());
            preparedStmt.setString(4, claim.getVehicles().getVehicleDetails().getModelDescription());
            preparedStmt.setString(5, claim.getVehicles().getVehicleDetails().getEngineDescription());
            preparedStmt.setString(6, claim.getVehicles().getVehicleDetails().getExteriorColor());
            preparedStmt.setString(7, claim.getVehicles().getVehicleDetails().getLicPlate());
            preparedStmt.setString(8, claim.getVehicles().getVehicleDetails().getLicPlateExpDate());
            preparedStmt.setString(9, claim.getVehicles().getVehicleDetails().getLicPlateState());
            preparedStmt.setString(10, claim.getVehicles().getVehicleDetails().getDamageDescription());
            preparedStmt.setInt(11, claim.getVehicles().getVehicleDetails().getMileage());
            preparedStmt.setString(12, claim.getVehicles().getVehicleDetails().getVin());

            preparedStmt.execute();
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	@Override
	public List<Claim> listClaim(String claimNumber) {
		   List<Claim> claims = new LinkedList<Claim>();
	        String getMitchellClaim = "Select * from mitchellclaim where ClaimNumber = '" + claimNumber + "'";

	         try {
	        	 
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery(getMitchellClaim);
	                 
	                Claim claim = null;
	                while(resultSet.next()){
	                String firstName =resultSet.getString("ClaimantFirstName");
	                String lastName = resultSet.getString("ClaimantLastName");
	                String status = resultSet.getString("Status");
	                Timestamp lossDate = resultSet.getTimestamp("LossDate");
	                Long assingnedAdjusterID = resultSet.getLong("AssignedAdjusterID");
	                    claim = new Claim(firstName,lastName,status,lossDate,assingnedAdjusterID);
	                    claims.add(claim);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(claims);
	            return claims;
	}

	@Override
	public List<Claim> listClaimByDate(String startDate, String endDate) {
		  List<Claim> claims = new LinkedList<Claim>();
	        String getMitchellClaim = "Select * from mitchellclaim where LossDate BETWEEN '" + startDate + "' AND '" + endDate + "'";

	         try {
	        	 
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery(getMitchellClaim);
	                 
	                Claim claim = null;
	                while(resultSet.next()){
	                String firstName =resultSet.getString("ClaimantFirstName");
	                String lastName = resultSet.getString("ClaimantLastName");
	                String status = resultSet.getString("Status");
	                Timestamp lossDate = resultSet.getTimestamp("LossDate");
	                Long assingnedAdjusterID = resultSet.getLong("AssignedAdjusterID");
	                    claim = new Claim(firstName,lastName,status,lossDate,assingnedAdjusterID);
	                    claims.add(claim);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(claims);
	            return claims;	}
  }