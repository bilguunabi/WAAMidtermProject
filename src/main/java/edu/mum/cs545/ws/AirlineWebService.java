package edu.mum.cs545.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("airline")
public class AirlineWebService {
	@Inject
	private AirlineService airlineService;

	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> findAll() {
		return airlineService.findAll();
	}

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@DefaultValue("") @QueryParam("name") String name) throws ParseException {
		if (name.isEmpty()) {
			return "null";
		}
		Airline airline = new Airline(name);
		airlineService.create(airline);
		return "created";
	}

	@GET
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@DefaultValue("") @QueryParam("name") String name) throws ParseException {
		System.out.println("inside AirlineWebservice param:" + name);
		if (name.isEmpty()) {
			return "null";
		}
		Airline airline = airlineService.findByName(name);
		if (!airline.equals(null)) {
			airlineService.delete(airline);
			return "success";
		} else {
			return "fail";
		}
	}

	@GET
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(@DefaultValue("") @QueryParam("oldName") String oldName,
			@DefaultValue("") @QueryParam("newName") String newName) throws ParseException {
		System.out.println("indide airlinewebService "+oldName+" "+newName);
		if (oldName.isEmpty() || newName.isEmpty()) {
			return "null";
		}
		Airline airline = airlineService.findByName(oldName);
		if (airline.equals(null)) {
			System.out.println("not found");
			return "fail";
		}
		System.out.println("going to setName");
		airline.setName(newName);
		System.out.println(""+airline.getName());
		airlineService.update(airline);
		return "success";
	}

}
