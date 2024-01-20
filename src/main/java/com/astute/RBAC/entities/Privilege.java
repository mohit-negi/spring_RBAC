package com.astute.RBAC.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "privilege")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Privilege {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
//    private String groupIdToken;
//    private boolean isVerified;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

}