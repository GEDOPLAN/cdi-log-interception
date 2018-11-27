package de.gedoplan.CDILogDemo.rest;

import de.gedoplan.CDILogDemo.services.HelloWorldService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

    
        @Inject
        private HelloWorldService helloWorldService;
    
	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok(helloWorldService.getFormattedDate("dd.MM.yyyy")).build();
	}
        
	@GET
        @Path("world")
	@Produces("text/plain")
	public Response doGetFail() {
		return Response.ok(helloWorldService.getSomeError("Hello")).build();
	}
}
