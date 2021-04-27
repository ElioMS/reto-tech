package com.reto.tech.models.response;
import java.io.Serializable;

public class ClientResponse implements Serializable {
    private String name;
    private String lastname;
    private Integer age;
    private String birthDate;
    private String deceaseDate;

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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeceaseDate() {
        return deceaseDate;
    }

    public void setDeceaseDate(String deceaseDate) {
        this.deceaseDate = deceaseDate;
    }
}
