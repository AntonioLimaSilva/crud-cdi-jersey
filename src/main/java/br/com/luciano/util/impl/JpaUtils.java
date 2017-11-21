package br.com.luciano.util.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import br.com.luciano.util.PersistenceSingleton;
import br.com.luciano.util.impl.JPAOperation;

class JpaUtils {
	
	static Object transactional(final JPAOperation operation, String errorMessage) {
        EntityManager em = PersistenceSingleton.instance.getEntityManagerFactory().createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            final Object returnedData = operation.execute(em);
            tx.commit();
            return returnedData;
        } catch (RollbackException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new IllegalStateException(errorMessage, e);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

}
