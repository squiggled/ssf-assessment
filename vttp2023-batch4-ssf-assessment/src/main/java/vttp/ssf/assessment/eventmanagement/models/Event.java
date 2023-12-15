package vttp.ssf.assessment.eventmanagement.models;

import java.text.DateFormat;
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

    public Date getEventDate() throws ParseException {
        
        String dateString = eventDate.toString();
        long dateLong = Long.parseLong(dateString);

        Date date = new Date(dateLong);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       
        Date dt = sdf.parse(date.toString()); 
        //Unparseable date: "Fri Dec 12 19:03:03 SGT 1969" (through reference chain: vttp.ssf.assessment.eventmanagement.models.Event["eventDate"])
        System.out.println(dt);
        return dt;
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
