package com.astute.RBAC.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
//    private String groupIdToken;
//    private boolean isVerified;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users = new ArrayList<>(1000);

//    @ManyToMany
//    @JoinTable(
//            name = "roles_privileges",
//            joinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "privilege_id", referencedColumnName = "id"))
//    private Collection<Privilege> privileges;
}