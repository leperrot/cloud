package org.iut.cloud.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.iut.cloud.model.Account;

@Path("/accounts")
public class AccManager {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts() {
		
		List<Account> accounts = ofy().load().type(Account.class).list();
		
		if(accounts == null || accounts.isEmpty())
			return Response.status(404).build();
		
		return Response.ok().entity(accounts).build();
	}
	
	@Path("/new")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(Account account) {
		
		ofy().save().entity(account);
		
		return Response.status(201).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("id") Long account) {
		
		ofy().delete().type(Account.class).id(account);
		
		return Response.ok().build();
	}
}
