package be.swsb.fiazard.ordering.condiment;

import be.swsb.fiazard.common.error.ErrorR;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = CondimentResource.CONDIMENT_PATH, description = "Operations about Condiments")
@Path(CondimentResource.CONDIMENT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CondimentResource {

    public static final String CONDIMENT_PATH = "/ordering/condiment";

    private CondimentDAO dao;

	private EventStore eventStore;

    public CondimentResource(CondimentDAO dao, EventStore eventStore) {
        this.dao = dao;
		this.eventStore = eventStore;
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Condiment[].class, message = ""),
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
    public Response lock(Condiment condiment) {
    	eventStore.store(new CondimentLockedEvent(condiment));
    	return Response.ok().build();
    }
    
    @POST
    @Path("/unlock")
    @Timed
    @ApiResponses(value = {
    		@ApiResponse(code = 200, response = Void.class, message = ""),
    		@ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response unlock(Condiment condiment) {
    	eventStore.store(new CondimentUnlockedEvent(condiment));
    	return Response.ok().build();
    }

    @POST
    @Path("/exclude")
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response exclude(Condiment condiment) {
        eventStore.store(new CondimentExcludeEvent(condiment));
        return Response.ok().build();
    }
}
