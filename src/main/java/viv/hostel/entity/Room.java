package viv.hostel.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1)
    private int numberRooms;

    @Size(min = 1, max = 6)
    private int numberUsers;

    @Size(min = 1)
    private int numberFloor;

    @ManyToMany
    @JoinTable(name = "options_in_room")
    private Set<OptionsRoom> options;

}
