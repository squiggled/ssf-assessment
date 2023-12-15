package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
public class EventController {

	@Autowired
	DatabaseService databaseService;
	
	//TODO: Task 5
	@GetMapping("/events/listing")
	public String getAllEvents(Model model){
		List<Event> eventListing = databaseService.getAllEvents();
		model.addAttribute("events", eventListing);
		return "view0";
	}

	@GetMapping("/events/register/{eventId}")
	public String loadRegister(@PathVariable Integer eventId, Model model){
		System.out.println("registering for event id " + eventId);

		//find the event
		Event foundEvent = databaseService.getRegisteredId(eventId);
		model.addAttribute("event", foundEvent);
		return "eventregister";
	}
	// @GetMapping("/update")
    // public String loadUpdatePage(@RequestParam("id") Integer itemId, HttpSession session, Model model){
    //     System.out.println("item to update in loadupdatepage " + itemId);
    //     //find the item
    //     Item foundItem = cartService.findItemById(itemId, session);

    //     System.out.println("found item's id in loadupdatepage: " +foundItem.getId());
      
    //     session.setAttribute("reloadFromRedis", false);
    //     model.addAttribute("itemToUpdate", foundItem);
    //     return "updatecart";
    // }


}
