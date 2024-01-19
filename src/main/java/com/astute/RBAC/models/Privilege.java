//package com.astute.RBAC.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.Collection;
//
//@Entity
//@Table(name="privilege")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Privilege {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "privileges")
//    private Collection<Role> roles;
//}