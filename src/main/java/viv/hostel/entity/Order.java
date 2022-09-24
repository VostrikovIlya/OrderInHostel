package viv.hostel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    private Date dateOfApplication;

    private Date dateOfExecution;

    @ColumnDefault("false")
    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
