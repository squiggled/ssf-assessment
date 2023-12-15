package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
public class EventController {

	@Autowired
	DatabaseService databaseService;
	
	//TODO: Task 5
	@GetMapping(value={"", "/", "events/listing"})
	public String getAllEvents(Model model){
		List<Event> eventListing = databaseService.getAllEvents();
		model.addAttribute("events", eventListing);
		return "view0";
	}


}
