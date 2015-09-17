package be.swsb.fiazard.ordering.orderedorders;

import be.swsb.fiazard.common.error.ErrorR;
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

@Api(value = OrdersResource.ORDERS_BASE_URI, description = "Operations about orders")
@Path(OrdersResource.ORDERS_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersResource {
    public static final String ORDERS_BASE_URI = "/orders";
    private OrdersDAO ordersDAO;

    public OrdersResource(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Order[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response getAll() {
        return Response.ok(ordersDAO.findAll(), MediaType.APPLICATION_JSON_TYPE).build();
    }
}
