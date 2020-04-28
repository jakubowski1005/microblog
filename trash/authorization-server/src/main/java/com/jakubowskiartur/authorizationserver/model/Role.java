package com.jakubowskiartur.authorizationserver.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "name")
    String name;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "permission_role", joinColumns = {
//            @JoinColumn(name = "role_id", referencedColumnName = "id")
//    }, inverseJoinColumns = {
//            @JoinColumn(name = "permission_id", referencedColumnName = "id")
//    })
//    List<Permission> permissions;
}
