package be.craftsmen.fiazard.managing.representation.openinghour;

import java.time.DayOfWeek;
import java.util.List;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.common.Representation;

import com.google.common.collect.Lists;

public class OpeningHourR implements Representation {

	private Id id;

	private DayOfWeek day;

	private List<OpenPeriod> openPeriods;

	private OpeningHourR() {
	}

	public String getId() {
		return Id.asString(id);
	}
	
	public DayOfWeek getDay() {
		return day;
	}
	
	public List<OpenPeriod> getOpenPeriods() {
		return openPeriods;
	}

	public static class Builder {
		private Id id;
		private DayOfWeek dayOfWeek;
		private List<OpenPeriod> openPeriods = Lists.newArrayList();

		public Builder() {
			id = Id.random();
		}

		public OpeningHourR build() {
			OpeningHourR openingHourR = new OpeningHourR();

			openingHourR.id = id;
			openingHourR.day = dayOfWeek;
			openingHourR.openPeriods = openPeriods;

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

		public Builder withOpenPeriods(List<OpenPeriod> openPeriods) {
			this.openPeriods.addAll(openPeriods);
			return this;
		}

		public Builder withOpenPeriod(OpenPeriod openPeriod) {
			this.openPeriods.add(openPeriod);
			return this;
		}

	}
}
