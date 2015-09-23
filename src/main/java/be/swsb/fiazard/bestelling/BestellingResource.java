package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.common.Identifiable;
import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.eventstore.AggregateRepository;
import be.swsb.fiazard.ordering.orderplacement.OrderPlaced;
import be.swsb.fiazard.ordering.orderplacement.PlaceOrder;
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

@Api(value = BestellingResource.ORDERING_BASE_URI, description = "Operations about orders")
@Path(BestellingResource.ORDERING_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellingResource {

    public static final String ORDERING_BASE_URI = "/bestelleuh";
    private BestellingFactory bestellingFactory;
    private AggregateRepository aggregateRepo;

    public BestellingResource(BestellingFactory bestellingFactory, AggregateRepository aggregateRepo) {
        this.bestellingFactory = bestellingFactory;
        this.aggregateRepo = aggregateRepo;
    }

    @POST
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 202, response = Identifiable.class, message = "Order placed"),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Error while placing order")
    })
    @Path("/placeorder")
    public Response plaatsBestelling(PlaceOrder placeOrder) {
        Identifiable identifiable = Identifiable.randomId();
        new PlaatsBestellingCommandHandler(bestellingFactory, aggregateRepo).handleCommand(new PlaatsBestellingCommand(placeOrder.getSandwiches().get(0).getLabel()));
        return Response.ok(identifiable).build();
    }

}
