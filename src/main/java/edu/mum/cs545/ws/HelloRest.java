package edu.mum.cs545.ws;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("hello")
public class HelloRest {

	private static final String SUCCESS_RESULT = "Sussess";

	private static final String FAILURE_RESULT = "Fail";

	@Inject
	private AirlineService airlineService;

	@Inject
	private FlightService flightService;

	@Inject
	private AirportService airportService;

	@Inject
	private AirplaneService airplaneService;

	@Inject
	private FlightDao flightDao;

	@GET
	public String helloWorld(@DefaultValue("Bindy") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

	@Path("airline")
	@GET
	public String getAirlineTest() {

		String result = "{";

		List<Flight> flights = flightService.findAll();

		if (!flights.isEmpty()) {
			for (Flight f : flights) {
				result += f.getId() + " " + f.getAirline().getId();
			}
		}
		return result;

	}

	

}
