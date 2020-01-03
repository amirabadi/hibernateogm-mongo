package com.sample.ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.sample.model.Property;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class PropertyManager {

    @PersistenceContext(unitName = "mongo-ogm")
    private EntityManager em;

    public void save(Property p) {
        em.persist(p);
    }
    public Property find(Property p){
        //  return   em.find(Property.class,p.getId());
           return (Property) em.createNativeQuery("db.Property.find({'key':'4'})",Property.class).getSingleResult();
    }
    public List<Property> queryCache() {
        Query query = em.createQuery("from Property p");

        List<Property> list = query.getResultList();
        return list;
    }

    public void update(Property pUpdate) {
        em.merge(pUpdate);
        em.flush();
    }

    public void delete(Property pUpdate) {
        em.remove(em.contains(pUpdate) ? pUpdate : em.merge(pUpdate));
        em.flush();
    }
}
