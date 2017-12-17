package pl.sda.javawwa.dto;

import pl.sda.javawwa.entity.Customer;

import java.time.LocalDate;

public class CustomerDto {

    private Integer id;
    private String email;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    public CustomerDto(Customer entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.dateOfBirth = entity.getDateOfBirth();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
