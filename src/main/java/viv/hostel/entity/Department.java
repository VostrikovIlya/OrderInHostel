package viv.hostel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String slug;

    private String phone;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
