package org.iut.cloud.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iut.cloud.model.Account;
import org.json.JSONObject;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Path("/check/{account}")
public class CheckAccount {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@PathParam("account") Long id) {
		
		Account account = ofy().load().type(Account.class).id(id).now();
		
		if(account == null)
			return Response.status(404).build();

		JSONObject json = new JSONObject();
		json.put("risk", account.getRisk());
		
		return Response.ok().entity(json).build();
	}
	
}