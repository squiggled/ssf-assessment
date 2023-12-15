package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class Registration {
    @NotEmpty(message="Please enter a name")
    @Size(min=5, max=25)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message="Birth date must be a past date")
    private Date birthday;

    @NotEmpty(message="Please enter an email")
    @Email(message="Please enter a valid email")
    @Size(max=50)
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}",message="Invalid phone number")
    private String phone;

    @Min(value=1, message="Minimum 1 ticket")
    @Max(value=3, message="Maximum 3 tickets")
    private Integer tickets;

    
    public Registration() {
    }
 

    public Registration(@NotEmpty(message = "Please enter a name") @Size(min = 5, max = 25) String name,
            @Past(message = "Birth date must be a past date") Date birthday,
            @NotEmpty(message = "Please enter an email") @Email(message = "Please enter a valid email") @Size(max = 50) String email,
            @Pattern(regexp = "[8-9]{8,}", message = "Invalid phone number") String phone,
            @Min(1) @Max(3) Integer tickets) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.tickets = tickets;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    
    
}
