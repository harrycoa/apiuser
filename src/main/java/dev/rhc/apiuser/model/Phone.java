package dev.rhc.apiuser.model;

import javax.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String citycode;
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public Phone setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Phone setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getCitycode() {
        return citycode;
    }

    public Phone setCitycode(String citycode) {
        this.citycode = citycode;
        return this;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Phone setCountrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Phone setUser(User user) {
        this.user = user;
        return this;
    }

    public Phone(Long id, String number, String citycode, String countrycode, User user) {
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.user = user;
    }

    public Phone() {
    }
}
