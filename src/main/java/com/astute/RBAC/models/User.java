//package com.astute.RBAC.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.Collection;
//
//@Entity
//@Table(name="user")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private boolean enabled;
//    private boolean tokenExpired;
//
//    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;
//}