package com.astute.RBAC.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name="application_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "userId")
    private Integer id;
    @Column(name = "email",length=100, unique = true)
    private String email;
    @Column(name = "phoneNumber",length=100, unique = true)
    private String phoneNumber;
    @Column(name = "userName",length=100, unique = true)
    private String userName;
    @Column(name = "firstName",length = 100)
    private String firstName;
    @Column(name = "lastName",length = 100)
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name="address", length=100)
    private String address;
    private String description;
    private String idToken;
    @Column(name = "groupIdToken",length = 1000)
    private String groupIdToken;
    private boolean isVerified;

    @CreationTimestamp
    @Column(name = "createDateTime")
    private OffsetDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private OffsetDateTime updateDatetime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isVerified == user.isVerified &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(address, user.address) &&
                Objects.equals(description, user.description) &&
                Objects.equals(idToken, user.idToken) &&
                Objects.equals(groupIdToken, user.groupIdToken) &&
                Objects.equals(createDateTime, user.createDateTime) &&
                Objects.equals(updateDatetime, user.updateDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber, userName, firstName, lastName, password, address, description,
                idToken, groupIdToken, isVerified, createDateTime, updateDatetime);
    }
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

}
