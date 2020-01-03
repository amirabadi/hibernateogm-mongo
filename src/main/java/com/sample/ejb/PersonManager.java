package com.sample.ejb;

import com.sample.model.Address;
import com.sample.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class PersonManager {
    @PersistenceContext(unitName = "mongo-ogm")
    private EntityManager em;
    public void save(Person p) {
        em.persist(p);
    }
    public void saveAddress(Address a) {
        em.persist(a);
    }
    public Person find(Person p){
          return   em.find(Person.class,p.getId());
     //   return (Person) em.createNativeQuery("db.Person.find({'key':'4'})",Person.class).getSingleResult();
    }
    public List<Person> queryCache() {
        Query query = em.createQuery("from Person p");

        List<Person> list = query.getResultList();
        return list;
    }

    public void update(Person pUpdate) {
        em.merge(pUpdate);
        em.flush();
    }

    public void delete(Person pUpdate) {
        em.remove(em.contains(pUpdate) ? pUpdate : em.merge(pUpdate));
        em.flush();
    }
}
