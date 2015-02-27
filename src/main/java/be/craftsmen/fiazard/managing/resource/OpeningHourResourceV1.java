package be.craftsmen.fiazard.managing.resource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.craftsmen.fiazard.common.error.ErrorR;
import be.craftsmen.fiazard.managing.representation.openinghour.OpenPeriodR;
import be.craftsmen.fiazard.managing.representation.openinghour.OpeningHourR;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = OpeningHourResourceV1.OPENING_HOUR_BASE_URI, description = "Operations about opening hours")
@Path(OpeningHourResourceV1.OPENING_HOUR_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OpeningHourResourceV1 {
	public static final String OPENING_HOUR_BASE_URI = "/v1/openinghours";
	private static final List<OpeningHourR> openingHours;

	private static final OpeningHourR mondayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.MONDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR tuesdayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.TUESDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR wednesdayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.WEDNESDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR thursdayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.THURSDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR fridayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.FRIDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR saturdayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.SATURDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();
	private static final OpeningHourR sundayOpeningHour = new OpeningHourR.Builder()
			.withDayOfWeek(DayOfWeek.SUNDAY)
			.withHour(new OpenPeriodR.Builder()
					.withFrom(LocalTime.of(9, 30))
					.withUntil(LocalTime.of(17, 30))
					.build())
			.build();

	static {
		openingHours = Lists.newArrayList(
				mondayOpeningHour,
				tuesdayOpeningHour,
				wednesdayOpeningHour,
				thursdayOpeningHour,
				fridayOpeningHour,
				saturdayOpeningHour,
				sundayOpeningHour);
	}

	@GET
	@Timed
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = OpeningHourR[].class, message = ""),
			@ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
	})
	public Response getAll() {
		return Response.ok(openingHours, MediaType.APPLICATION_JSON_TYPE).build();
	}
}
