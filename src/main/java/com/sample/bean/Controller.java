package com.sample.bean;

import com.sample.ejb.PersonManager;
import com.sample.ejb.PropertyManager;
import com.sample.model.Address;
import com.sample.model.Person;
import com.sample.model.Property;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@SessionScoped
@Named(value = "controller")
public class Controller implements Serializable {

    @EJB
    PropertyManager ejb;
    @EJB
    PersonManager ejbP;

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    List<Property> propertyList;
    List<Person> personList;

    Property property;

    private String key;
    private String value;

    private String name,lName, address1,address2;

    //  @PostConstruct
    public void readDB() {
        propertyList = ejb.queryCache();
        personList = ejbP.queryCache();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void save() {
        Property p = new Property();
        p.setKey(key);
        p.setValue(value);
        ejb.save(p);
        propertyList.add(p);
        key="";
        value="";
    }

    public void update(Property p){
        Property pUpdate=ejb.find(p);
        pUpdate.setValue("xxx");
        ejb.update(pUpdate);
    }

    public void delete(Property p){
        Property pUpdate=ejb.find(p);

        ejb.delete(pUpdate);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveRelations(){
        Person p = new Person();
        Address add1 = new Address();
        Address add2 = new Address();
        p.setName(name);
        p.setlName(lName);
        ejbP.save(p);
       add1.setAddressPost(address1);
        add1.setPerson(p);
        ejbP.saveAddress(add1);
        add2.setAddressPost(address2);
        add2.setPerson(p);
        ejbP.saveAddress(add2);
        personList.add(p);
    }



    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
