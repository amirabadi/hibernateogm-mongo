package com.sample.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String addressPost;
    @ManyToOne
    @JoinColumn(name="person",referencedColumnName = "id",nullable = true)
    private Person person;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
