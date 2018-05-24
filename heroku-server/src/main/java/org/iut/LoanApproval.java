package org.iut;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iut.model.Account;
import org.iut.model.Approval;
import org.iut.model.Loan;

@Path("/loan")
public class LoanApproval {

	private static final String URI = "https://newproject-161616.appspot.com/services/";
	private static final Logger LOG = Logger.getLogger(LoanApproval.class.getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loaning(Loan loan, @Context HttpServletRequest request) {
		
		String response;
		
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String header = headers.nextElement();
			LOG.info(header + " " + request.getHeader(header));
		}
		
		Client client = ClientBuilder.newClient();
		Response res = client.target(URI + "accounts/"+loan.getAccount()).request().get(Response.class);
		
		if(res.getStatus() != 200) {
			LOG.info("Account not found");
			return Response.status(res.getStatus()).build();
		}
		
		Account account = res.readEntity(Account.class);
		
		LOG.info(account.getNom() + " retrieved");
		
		switch(account.getRisk()) {
			case "high":
				response = "refused";
				break;
			case "low":
				if(loan.getAmount() + account.getAmount() > 10000) {
					account.setRisk("high");
					response = "refused";
				}
				else {
					account.setAmount(loan.getAmount() + account.getAmount());
					response = "accepted";
				}
				client.target(URI + "accounts/update")
					.request(MediaType.APPLICATION_JSON)
					.put(Entity.entity(account, MediaType.APPLICATION_JSON));
				break;
			default:
				return Response.status(500).build();
		}
		
		Approval approval = new Approval();
		approval.setAccount(account.getAccount());
		approval.setNom(loan.getNom());
		approval.setResponse(response);
		
		client.target(URI + "approvals/new")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.entity(approval, MediaType.APPLICATION_JSON));
		
		return Response.ok().entity(approval).build();
	}
}
