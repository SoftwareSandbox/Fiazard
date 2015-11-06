package be.swsb.fiazard.util.representation;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeUtilTest {

    @Test
    public void stringCanBeConvertedToLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTimeUtil.toLocalDateTime("2012-04-23T18:25:43.511Z");

        assertThat(localDateTime.getYear()).isEqualTo(2012);
        assertThat(localDateTime.getMonth()).isEqualTo(Month.APRIL);
        assertThat(localDateTime.getDayOfMonth()).isEqualTo(23);
        assertThat(localDateTime.getHour()).isEqualTo(18);
        assertThat(localDateTime.getMinute()).isEqualTo(25);
        assertThat(localDateTime.getSecond()).isEqualTo(43);
        assertThat(localDateTime.getNano()).isEqualTo(511000000);
    }

    @Test
    public void nullStringCanBeConvertedToLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTimeUtil.toLocalDateTime(null);

        assertThat(localDateTime).isNull();
    }

    @Test
    public void localDateTimeCanBeConvertedToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2012, Month.JANUARY, 2, 3, 4, 5, 6000000);

        String string = LocalDateTimeUtil.toString(localDateTime);
        assertThat(string).isEqualTo("2012-01-02T03:04:05.006Z");
    }

    @Test
    public void nullLocalDateTimeCanBeConvertedToString() {
        LocalDateTime localDateTime = null;

        String string = LocalDateTimeUtil.toString(localDateTime);
        assertThat(string).isNull();
    }

}