package org.iut.cloud.services;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iut.cloud.model.Approval;

@Path("/approvals")
public class AppManager {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApprovals() {
		
		List<Approval> approvals = ofy().load().type(Approval.class).list();
		
		if(approvals == null || approvals.isEmpty())
			return Response.status(404).build();
		
		return Response.ok().entity(approvals).build();
	}
	
	@Path("/account/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApprovalsForAccount(@PathParam("id") Long account) {
		
		List<Approval> approvals = ofy().load().type(Approval.class).filter("account", account).list();
		
		if(approvals == null || approvals.isEmpty())
			return Response.status(404).build();
		
		return Response.ok().entity(approvals).build();
		
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApproval(@PathParam("id") Long id) {
		
		Approval approval = ofy().load().type(Approval.class).id(id).now();
		
		if(approval == null)
			return Response.status(404).build();
		
		return Response.ok().entity(approval).build();
	}
	
	@Path("/new")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApproval(Approval approval) {
		
		ofy().save().entity(approval);
		
		return Response.status(201).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApproval(@PathParam("id") Long id) {
		
		ofy().delete().type(Approval.class).id(id);
		
		return Response.ok().build();
	}
	
}
