package br.com.luciano.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceSingleton {
	
    instance;

    private EntityManagerFactory entityManagerFactory;

    private EntityManagerFactory setupEntityManagerFactory() {
        return Persistence.createEntityManagerFactory( "crud.cdi.jersey.pu" );
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null){
            entityManagerFactory = setupEntityManagerFactory();
        }
        return entityManagerFactory;
    }

    public void destroy() {
        entityManagerFactory.close();
    }
}
