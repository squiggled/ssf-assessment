package vttp.ssf.assessment.eventmanagement.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

import org.springframework.core.io.Resource;


@Service
public class DatabaseService {

    @Autowired
    RedisRepository redisRepo;
    
    List<Event> eventListing;

     // TODO: Task 1
    public List<Event> readFile(File file, String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
		InputStream is = resource.getInputStream();
		JsonReader jsonReader = Json.createReader(is);
        JsonArray jsonArray = jsonReader.readArray();
        eventListing = new ArrayList<>();
        for (JsonObject eventObj: jsonArray.getValuesAs(JsonObject.class)){
            Event event = new Event();
            event.setEventId(eventObj.getInt("eventId"));
            event.setEventName(eventObj.getString("eventName"));
            event.setEventSize(eventObj.getInt("eventSize"));
            // event.setEventDate(eventObj.getInt("eventDate"));
            JsonNumber number = eventObj.getJsonNumber("eventDate");
            Long eventDate = number.longValue();
            event.setEventDate(eventDate);
            event.setParticipants(eventObj.getInt("participants"));
            System.out.println(event);
            eventListing.add(event);
        }
        System.out.println(eventListing);
        return eventListing;
        
    }


    public void saveRecord(Event event) {
        redisRepo.saveToRedis(event);
    }
    
    public List<Event> getAllEvents(){
        return eventListing;
    }


    public Event getRegisteredId(Integer eventId) {
        Integer indexInRedis = null;
        if (eventId == 4){
            indexInRedis = 0;
        } else if (eventId == 3){
            indexInRedis = 1;
        } else if (eventId == 2){
            indexInRedis = 2;
        } else if (eventId == 1){
            indexInRedis = 3;
        } 
        Event eventFound = redisRepo.getEvent(indexInRedis);
        return eventFound;
    }
   

}
