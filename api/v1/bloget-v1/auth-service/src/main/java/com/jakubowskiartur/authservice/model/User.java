package com.jakubowskiartur.authservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    String username;

    String password;

    String email;

    boolean enabled;

    @Column(name = "account_non_expired")
    boolean accountNonExpired;

    @Column(name = "credentials_non_expired")
    boolean credentialsNonExpired;

    @Column(name = "account_non_locked")
    boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    })
    List<Role> roles;

    public User(User user) {
        //this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roles = user.getRoles();
    }
}
