package be.craftsmen.fiazard.common.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IllegalIdFiazardExceptionTest {

    @Test
    public void message_ReturnsCorrectMessage(){
        String actual = new IllegalIdFiazardException("my id").getMessage();
        assertThat(actual).isEqualTo("id invalid: my id");
    }

    @Test
    public void errorCode_ReturnsIllegalId() throws Exception {
        AppErrorCode actual = new IllegalIdFiazardException("my id").getErrorCode();
        assertThat(actual).isEqualTo(AppErrorCode.ILLEGAL_ID);
    }
}