package vttp.ssf.assessment.eventmanagement.repositories;

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
		template.opsForHash().put("events", event.getEventId(), event);
	}

	// TODO: Task 3
	public Long getNumberOfEvents(){
		Long mapSize = template.opsForHash().size("events");
		return mapSize;
	}

	// TODO: Task 4
	public Event getEvent(Integer index){
		Optional opt = Optional.ofNullable(template.opsForHash().get("events", index));
		if (opt.isPresent()){
			Event foundEvent = (Event) opt.get();
			return foundEvent;
		} else {
			return null;
		}
	}
}
