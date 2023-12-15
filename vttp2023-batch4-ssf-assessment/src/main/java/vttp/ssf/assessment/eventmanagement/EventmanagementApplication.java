package vttp.ssf.assessment.eventmanagement;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner{

	@Autowired
	DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	// TODO: Task 1
	@Override
	public void run(String... args) throws Exception {
		File file = new File("vttp2023-batch4-ssf-assessment/src/resources/");
		String fileName = "static/events.json";
		List<Event> eventListing = databaseService.readFile(file, fileName);

		for (Event event : eventListing){
			databaseService.saveRecord(event);
		}

		//check for task 3
		

	}
	
	
	

}
