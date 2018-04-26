package org.iut.cloud.services;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iut.cloud.model.Account;

@Path("/accounts")
public class AccManager {
	
	private static final Logger LOG = Logger.getLogger(AccManager.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts() {
		
		List<Account> accounts = ofy().load().type(Account.class).list();
		
		if(accounts == null || accounts.isEmpty()) {
			LOG.info("Accounts not found");
			return Response.status(404).build();
		}
		
		return Response.ok().entity(accounts).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id") Long id) {
		
		Account account = ofy().load().type(Account.class).id(id).now();
		
		if(account == null) {
			LOG.info("Account " + id + " not found");
			return Response.status(404).build();
		}
		
		return Response.ok().entity(account).build();
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
	
	@Path("/update")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Account account) {
		
		try{
			ofy().transact(() -> {
				Account gaeAccount = ofy().load().type(Account.class).id(account.getAccount()).now();
				if(gaeAccount != null) {
					ofy().save().entity(account);
				} else throw new NotFoundException();
			});
		}catch(NotFoundException e) {
			LOG.info(e.getMessage());
			return Response.status(404).build();
		}
		
		return Response.status(200).build();
	}
}
