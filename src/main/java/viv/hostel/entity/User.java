package viv.hostel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String numberContract;

    @NotEmpty
    @Size(min = 3)
    private String username;

    @NotEmpty
    @Size(min = 128)
    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room numberRoom;

    private String phone;

    @ColumnDefault("true")
    @JsonIgnore
    private boolean enabled;

    @ColumnDefault("false")
    @JsonIgnore
    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> authorities;

    public void addAuthorities(Role role) {
        authorities.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
