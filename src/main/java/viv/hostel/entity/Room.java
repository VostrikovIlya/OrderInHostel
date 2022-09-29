package viv.hostel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Size(min = 1)
    private int numberRooms;

    @Size(min = 1, max = 6)
    private int numberUsers;

    @Size(min = 1)
    private int numberFloor;

    @ManyToMany
    @JoinTable(name = "option_in_room",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "options_room_id"))
    private Set<OptionsRoom> options;

}
