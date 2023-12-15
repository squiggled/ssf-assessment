package vttp.ssf.assessment.eventmanagement.controllers;

import java.time.Year;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Registration;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
public class RegistrationController {

    @Autowired
    DatabaseService databaseService;

    // TODO: Task 6
    @GetMapping("/events/register/{eventId}")
    public String loadRegister(@PathVariable String eventId, Model model, HttpSession session) {
        //save event in session

        System.out.println("registering for event id " + eventId);

        
        // find the event
        Integer eventIdInt = Integer.parseInt(eventId);
        Event foundEvent = databaseService.getRegisteredId(eventIdInt);

        System.out.println("found event: " + foundEvent);

        model.addAttribute("registration",new Registration());
        model.addAttribute("event", foundEvent);
        session.setAttribute("event", foundEvent);
        return "eventregister";
    }

    // TODO: Task 7
    @PostMapping("/registration/register")
    public String processRegistration(@Valid @ModelAttribute Registration registration, BindingResult result, HttpSession session, Model model){
        Event savedEvent = (Event) session.getAttribute("event");
        System.out.println("event found "+ savedEvent);

        //check for form error
        if (result.hasErrors()){
            session.setAttribute("event", savedEvent);
            model.addAttribute("registration", registration);
            model.addAttribute("event", savedEvent);
            return "eventregister";
        }

        //server side rendering - AGE CHECK
        Date userBirthday = registration.getBirthday();
        String year = userBirthday.toString().substring(24);
        int yearNow = Year.now().getValue();

        if ((yearNow - Integer.parseInt(year) <21)) {
            String message = "You are below 21 years old";
            session.setAttribute("event", savedEvent);
            model.addAttribute("errMessage", message);
            return "errorregistration";
        }

         //server side rendering - QUOTA CHECK
        Boolean canRegister = databaseService.checkQuota(savedEvent.getEventId(), registration.getTickets());
        if (!canRegister){
            String message = "Your request for tickets exceeded the event size";
            session.setAttribute("event", savedEvent);
            model.addAttribute("errMessage", message);
            return "errorregistration";
        }
        savedEvent = (Event) session.getAttribute("event");
        session.setAttribute("event", savedEvent);
        model.addAttribute("event", savedEvent);
        return "successregistration";
    }
}
