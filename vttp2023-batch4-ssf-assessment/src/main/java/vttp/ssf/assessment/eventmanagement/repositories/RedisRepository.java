package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Object eventObject = template.opsForList().index("events", index);
		Event foundEvent = (Event) eventObject;
		return foundEvent;
	}

	// public void getRegisteredId(Integer indexInRedis) {

	// }
}
