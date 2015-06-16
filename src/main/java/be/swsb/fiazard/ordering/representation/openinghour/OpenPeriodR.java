package be.swsb.fiazard.ordering.representation.openinghour;

import java.time.LocalTime;

public class OpenPeriodR {
    private LocalTime from;
    private LocalTime until;

    private OpenPeriodR() {
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getUntil() {
        return until;
    }

    public static class Builder {
        private LocalTime from;
        private LocalTime until;

        public Builder withFrom(LocalTime from) {
            this.from = from;
            return this;
        }

        public Builder withUntil(LocalTime until) {
            this.until = until;
            return this;
        }

        public OpenPeriodR build() {
            OpenPeriodR openPeriod = new OpenPeriodR();
            openPeriod.from = from;
            openPeriod.until = until;
            return openPeriod;
        }
    }
}
