package viv.hostel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import viv.hostel.serializer.OrderSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "orders")
@JsonSerialize(using = OrderSerializer.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
