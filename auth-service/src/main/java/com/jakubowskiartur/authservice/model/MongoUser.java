package com.jakubowskiartur.authservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MongoUser {

    @Id
    String id;
    String username;
    String password;
    String email;
    List<MongoRole> roles;
    boolean enabled;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean accountNonLocked;

    public MongoUser(MongoUser user) {
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
