package be.swsb.fiazard.management.resource;

import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.management.domain.topping.Topping;
import be.swsb.fiazard.management.domain.topping.ToppingDAO;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = ToppingResourceV1.TOPPINGS_BASE_URI, description = "Operations about Toppings")
@Path(ToppingResourceV1.TOPPINGS_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToppingResourceV1 {
    public static final String TOPPINGS_BASE_URI = "/v1/toppings";

    private ToppingDAO dao;

    public ToppingResourceV1(ToppingDAO dao) {
        this.dao = dao;
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
}
