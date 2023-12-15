package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
public class RegistrationController {

    @Autowired
    DatabaseService databaseService;
    
    // TODO: Task 6
    @GetMapping("/events/register/{eventId}")
    public String loadRegister(@PathVariable String eventId, Model model) {
        System.out.println("registering for event id " + eventId);
        Integer eventIdInt = Integer.parseInt(eventId);
        // find the event
        Event foundEvent = databaseService.getRegisteredId(eventIdInt);
        model.addAttribute("event", foundEvent);
        return "eventregister";
    }

    // TODO: Task 7
}
