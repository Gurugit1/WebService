package com.mitchellInternational.claimclient;

import java.io.File;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class ClaimClient {

  public static void main(String[] args) {
    ClientConfig config = new ClientConfig();

    Client client = ClientBuilder.newClient(config);

    WebTarget target = client.target(getBaseURI());
	
    String response = 
        target.path("rest").path("CreateClaim").request().accept(MediaType.TEXT_XML).entity(new File("create-claim.xml")).post(String.class);
	System.out.println(response);
	
    String response1 = 
        target.path("rest").path("read").request().accept(MediaType.TEXT_PLAIN).get(String.class);
    System.out.println(response1);
 
    String response2= 
        target.path("rest").path("/read/bylossdate").request().accept(MediaType.TEXT_PLAIN).get(String.class);
    System.out.println(response2);
  }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/com.mitchellInternational.claimwebservice").build();
  }
} 