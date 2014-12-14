package be.craftsmen.fiazard.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IllegalIdExceptionTest {

    @Test
    public void message_ReturnsCorrectMessage(){
        String actual = new IllegalIdException("my id").getMessage();
        assertThat(actual).isEqualTo("id invalid: my id");
    }

    @Test
    public void errorCode_ReturnsIllegalId() throws Exception {
        AppErrorCode actual = new IllegalIdException("my id").getErrorCode();
        assertThat(actual).isEqualTo(AppErrorCode.ILLEGAL_ID);
    }
}