package io.github.k_gregory.registry.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "individual")
public class Individual {
    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "middlename", nullable = false)
    private String middleName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "passport", nullable = false)
    private String passport;

    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
