package com.reto.tech.models.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ClientRequest implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String birthDate;
    private Integer age = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
