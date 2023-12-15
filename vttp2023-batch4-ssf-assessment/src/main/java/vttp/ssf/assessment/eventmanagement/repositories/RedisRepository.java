package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired
    @Qualifier("myredis")
    private RedisTemplate<String, Object> template;

	// TODO: Task 2
	public void saveToRedis(Event event) {
		Long size = template.opsForList().size("events");
		if (size<4){
			template.opsForList().leftPush("events", event);
		}
	}

	// TODO: Task 3
	public Long getNumberOfEvents(){
		Long eventNo = template.opsForList().size("events");
		return eventNo;
	}

	// TODO: Task 4
	public Event getEvent(Integer index){
		// System.out.println("we got before finding"); //debug
		Object eventObject = template.opsForList().index("events", index);
		Event foundEvent = (Event) eventObject;
		// System.out.println("we got after casting"); //debug
		return foundEvent;
		
	}

	public void updateParticipantCount(Event event){
		Integer indexInRedis = null;
		Integer eventId = event.getEventId();
        if (eventId == 4){
            indexInRedis = 0;
        } else if (eventId == 3){
            indexInRedis = 1;
        } else if (eventId == 2){
            indexInRedis = 2;
        } else if (eventId == 1){
            indexInRedis = 3;
        } 
		template.opsForList().set("events", indexInRedis, event);

	}

	public List<Event> getAllEvents(){
		List<Object> objList = template.opsForList().range("events", 0, 3);
		List<Event> eventList = new ArrayList<>();
		for (Object object : objList){
			Event event = (Event) object;
			eventList.add(event);
		}
		return eventList;
	}


}
