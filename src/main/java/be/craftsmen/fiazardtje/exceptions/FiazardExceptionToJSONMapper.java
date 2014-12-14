package be.craftsmen.fiazardtje.exceptions;

import be.craftsmen.fiazard.exceptions.FiazardException;
import be.craftsmen.fiazard.representation.error.ErrorR;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FiazardExceptionToJSONMapper implements ExceptionMapper<FiazardException> {

    @Override
    public Response toResponse(FiazardException exception) {
        ErrorR errorR = ErrorR.from(exception);

        return Response.status(errorR.getStatus())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(errorR)
                .build();
    }
}
