package be.swsb.fiazard.ordering.topping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.swsb.fiazard.common.error.ErrorR;

import be.swsb.fiazard.common.eventsourcing.EventStore;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = ToppingResource.TOPPINGS_BASE_URI, description = "Operations about Toppings")
@Path(ToppingResource.TOPPINGS_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToppingResource {
    
	public static final String TOPPINGS_BASE_URI = "/ordering/topping";

    private ToppingDAO dao;
    private EventStore eventStore;

    public ToppingResource(ToppingDAO dao, EventStore eventStore) {
        this.dao = dao;
        this.eventStore = eventStore;
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Topping[].class, message = ""),
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
    public Response lock(Topping topping) {
        eventStore.store(new ToppingLockedEvent(topping));
        return Response.ok().build();
    }

    @POST
    @Path("/unlock")
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response unlock(Topping topping) {
        eventStore.store(new ToppingUnlockedEvent(topping));
        return Response.ok().build();
    }

    @POST
    @Path("/exclude")
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response exclude(Topping topping) {
        eventStore.store(new ToppingExcludeEvent(topping));
        return Response.ok().build();
    }
}
