package org.iut.cloud.services;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iut.cloud.model.Account;
import org.jose4j.json.internal.json_simple.JSONObject;

@Path("/check/{account}")
public class CheckAccount {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@PathParam("account") Long id) {
		
		Account account = ofy().load().type(Account.class).id(id).now();
		
		if(account == null)
			return Response.status(404).build();
		
		JSONObject risk = new JSONObject();
		risk.put("risk", account.getRisk());
		
		return Response.ok().entity(risk).build();
	}
	
}
