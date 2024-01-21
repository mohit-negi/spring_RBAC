package com.astute.RBAC.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public UserEntity(String email, String phoneNumber, String userName, String firstName, String lastName, String password, String address, String description, String idToken, String groupIdToken, boolean isVerified, OffsetDateTime createDateTime, OffsetDateTime updateDatetime, List<Role> roles) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.description = description;
        this.idToken = idToken;
        this.groupIdToken = groupIdToken;
        this.isVerified = isVerified;
        this.createDateTime = createDateTime;
        this.updateDatetime = updateDatetime;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return id == userEntity.id &&
                isVerified == userEntity.isVerified &&
                Objects.equals(email, userEntity.email) &&
                Objects.equals(phoneNumber, userEntity.phoneNumber) &&
                Objects.equals(userName, userEntity.userName) &&
                Objects.equals(firstName, userEntity.firstName) &&
                Objects.equals(lastName, userEntity.lastName) &&
                Objects.equals(password, userEntity.password) &&
                Objects.equals(address, userEntity.address) &&
                Objects.equals(description, userEntity.description) &&
                Objects.equals(idToken, userEntity.idToken) &&
                Objects.equals(groupIdToken, userEntity.groupIdToken) &&
                Objects.equals(createDateTime, userEntity.createDateTime) &&
                Objects.equals(updateDatetime, userEntity.updateDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber, userName, firstName, lastName, password, address, description,
                idToken, groupIdToken, isVerified, createDateTime, updateDatetime);
    }
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>(10);


}
