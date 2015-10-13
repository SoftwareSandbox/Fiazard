package be.swsb.fiazard.ordering.openinghours;

import be.swsb.fiazard.common.Id;
import be.swsb.fiazard.common.Representation;
import com.google.common.collect.Lists;

import java.time.DayOfWeek;
import java.util.List;

public class OpeningHourR implements Representation {

    private Id id;

    private DayOfWeek day;

    private List<OpenPeriodR> hours;

    private OpeningHourR() {
    }

    public String getId() {
        return Id.asString(id);
    }

    public DayOfWeek getDay() {
        return day;
    }

    public List<OpenPeriodR> getHours() {
        return hours;
    }

    public static class Builder {
        private Id id;
        private DayOfWeek dayOfWeek;
        private List<OpenPeriodR> hours = Lists.newArrayList();

        public Builder() {
            id = Id.random();
        }

        public OpeningHourR build() {
            OpeningHourR openingHourR = new OpeningHourR();

            openingHourR.id = id;
            openingHourR.day = dayOfWeek;
            openingHourR.hours = hours;

            return openingHourR;
        }

        public Builder withId(Id id) {
            this.id = id;
            return this;
        }

        public Builder withDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Builder withHours(List<OpenPeriodR> hours) {
            this.hours.addAll(hours);
            return this;
        }

        public Builder withHour(OpenPeriodR openPeriod) {
            this.hours.add(openPeriod);
            return this;
        }

    }
}
