package be.swsb.fiazard.ordering.bun;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.common.eventsourcing.EventStore;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = BunResource.BUN_PATH, description = "Operations about Buns")
@Path(BunResource.BUN_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BunResource {
    
	public static final String BUN_PATH = "/ordering/bun";

    private BunDAO dao;
    private EventStore eventStore;

    public BunResource(BunDAO dao, EventStore eventStore) {
        this.dao = dao;
		this.eventStore = eventStore;
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Bun[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response getAll() {
        return Response.ok(dao.findAll(), MediaType.APPLICATION_JSON_TYPE).build();
    }
    
    @POST
    @Path("/lock")
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response lock(Bun bun) {
    	eventStore.store(new BunLockedEvent(bun));
    	return Response.ok().build();
    }
    
    @POST
    @Path("/unlock")
    @Timed
    @ApiResponses(value = {
    		@ApiResponse(code = 200, response = Void.class, message = ""),
    		@ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response unlock(Bun bun) {
    	eventStore.store(new BunUnlockedEvent(bun));
    	return Response.ok().build();
    }

    @POST
    @Path("/exclude")
    @Timed
    @ApiResponses(value = {
    		@ApiResponse(code = 200, response = Void.class, message = ""),
    		@ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response exclude(Bun bun) {
    	eventStore.store(new BunExcludeEvent(bun));
    	return Response.ok().build();
    }
}
