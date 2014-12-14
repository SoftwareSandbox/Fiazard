package be.craftsmen.fiazardtje.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import be.craftsmen.fiazard.representation.error.ErrorR;
import be.craftsmen.fiazard.representation.error.ErrorRTestBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.dropwizard.jackson.Jackson;
import org.assertj.core.api.Assertions;
import org.json4s.jackson.Json;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static be.craftsmen.fiazardtje.exceptions.FiazardExceptionBuilder.someFiazardExceptionBuilder;

public class FiazardExceptionToJSONMapperTest {

    private FiazardExceptionToJSONMapper mapper;

    @Before
    public void before(){
        mapper = new FiazardExceptionToJSONMapper();
    }
    
    @Test
    public void mapsToErrorR() throws Exception {
        AppErrorCode expectedAppError = AppErrorCode.ILLEGAL_ID;
        String expectedMessage = "exception message";
        int expectedStatus = 400;
        ErrorR expectedErrorR = new ErrorRTestBuilder()
                .withStatus(expectedStatus)
                .withErrorCode(expectedAppError.getErrorCode())
                .withMessage(expectedMessage)
                .withFields("aField", "bField")
                .build();

        FiazardExceptionBuilder.DummyFiazardException dummyFiazardException = someFiazardExceptionBuilder()
                .withStatus(expectedStatus)
                .withAppError(expectedAppError)
                .withMessage(expectedMessage)
                .withFields("aField", "bField")
                .build();

        Response actual = mapper.toResponse(dummyFiazardException);

        Assertions.assertThat(actual.getStatus()).isEqualTo(expectedStatus);
        Assertions.assertThat(actual.getEntity()).isEqualTo(expectedErrorR);
    }
}