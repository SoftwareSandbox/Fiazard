package be.craftsmen.fiazard.managing.representation.openinghour;

import java.time.LocalTime;

import be.craftsmen.fiazard.managing.representation.util.LocalTimeDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OpenPeriodR {
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime from;
	@JsonDeserialize(using = LocalTimeDeserializer.class)
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
