package vttp.ssf.assessment.eventmanagement.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private Integer eventId;
    private String eventName;
    private Integer eventSize;
    private Long eventDate;
    private Integer participants;

    public Event() {
    }

    public Event(Integer eventId, String eventName, Integer eventSize, Long eventDate, Integer participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public String getEventDate() throws ParseException {
        Date date = new Date(eventDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
        //org.springframework.data.redis.serializer.SerializationException: Could not read JSON:Cannot deserialize value of type `java.lang.Long` from String "18-12-2023": not a valid `java.lang.Long` value
//  at [Source: (byte[])"{"@class":"vttp.ssf.assessment.eventmanagement.models.Event","eventId":2,"eventName":"Round Singapore Cycling","eventSize":5,"eventDate":"18-12-2023","participants":0}"; line: 1, column: 138] (through reference chain: vttp.ssf.assessment.eventmanagement.models.Event["eventDate"]) 
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

}
