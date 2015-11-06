package be.swsb.fiazard.ordering.preparation;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static be.swsb.fiazard.util.representation.LocalDateTimeUtil.toLocalDateTime;

@Api(value = OrderItemToBePreparedViewModelResource.PATH, description = "Resource representing items to be prepared")
@Path(OrderItemToBePreparedViewModelResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderItemToBePreparedViewModelResource {

    public static final String PATH = "/ordering/itemsToBePrepared";

    private OrderItemToBePreparedViewModelDAO dao;

    public OrderItemToBePreparedViewModelResource(OrderItemToBePreparedViewModelDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @ApiResponses(value = {@ApiResponse(code = 200, response = OrderItemToBePreparedViewModel[].class, message = "")})
    public Response find(@QueryParam("dateFrom") String dateFrom, @QueryParam("dateUntil") String dateUntil) {
        return Response.ok(dao.find(toLocalDateTime(dateFrom), toLocalDateTime(dateUntil)), MediaType.APPLICATION_JSON_TYPE).build();
    }

}
