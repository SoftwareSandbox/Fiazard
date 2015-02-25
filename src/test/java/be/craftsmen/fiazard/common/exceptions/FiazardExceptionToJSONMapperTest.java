package be.craftsmen.fiazard.common.exceptions;

import static be.craftsmen.fiazard.common.exceptions.FiazardExceptionBuilder.someFiazardExceptionBuilder;

import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import be.craftsmen.fiazard.common.error.ErrorR;
import be.craftsmen.fiazard.common.error.ErrorRBuilderForTests;

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
        ErrorR expectedErrorR = new ErrorRBuilderForTests()
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