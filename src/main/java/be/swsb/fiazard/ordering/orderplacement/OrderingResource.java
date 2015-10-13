package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.common.Identifiable;
import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.eventstore.AggregateRepository;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = OrderingResource.ORDERING_BASE_URI, description = "Operations about orders")
@Path(OrderingResource.ORDERING_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderingResource {

    public static final String ORDERING_BASE_URI = "/ordering";
    private OrderFactory orderFactory;
    private AggregateRepository aggregateRepo;

    public OrderingResource(OrderFactory orderFactory, AggregateRepository aggregateRepo) {
        this.orderFactory = orderFactory;
        this.aggregateRepo = aggregateRepo;
    }

    @POST
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 202, response = Identifiable.class, message = "Order placed"),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Error while placing order")
    })
    @Path("/placeorder")
    public Response placeOrder(PlaceOrder placeOrder) {
        Identifiable identifiable = Identifiable.randomId();
        new PlaceOrderCommandHandler(orderFactory, aggregateRepo).handleCommand(new PlaceOrderCommand(placeOrder.getSandwich()));
        return Response.ok(identifiable).build();
    }

}
