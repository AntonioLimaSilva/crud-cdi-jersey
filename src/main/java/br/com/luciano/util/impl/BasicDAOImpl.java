package br.com.luciano.util.impl;

import br.com.luciano.util.api.BasicDAO;

import static br.com.luciano.util.impl.JpaUtils.transactional;

import java.util.List;

public class BasicDAOImpl implements BasicDAO {
	
    @Override
    public Object inserir(Object entity) {
        return transactional(session -> {
            session.persist(entity);
            return entity;
        }, "Não pode inserir a entidade!");
    }

    @Override
    public Object buscar(Class<?> entityClass, long id) {
        return transactional(em -> em.find(entityClass, id),
                String.format("Não pode encontrar a entiade com %s", id));
    }

    @Override
    public Object alterar(Object entity) {
        return transactional(em -> {
            em.merge(entity);
            return entity;
        }, String.format("Não pode alterar a entidade %s", entity));
    }

    @Override
    public List<?> lista(Class<?> entityClass) {
        return (List<?>) transactional(em ->
                        em.createQuery("from " + entityClass.getSimpleName()).getResultList(),
                String.format("Não pode obter a entidade %s", entityClass.getSimpleName()));
    }

    @Override
    public long contador(Class<?> entityClass) {
        return (long) transactional(
                em -> em.createQuery("select count(*) from " + entityClass.getSimpleName()).getSingleResult(),
                "Não pode obter o total de entidade");
    }

    @Override
    public void deletar(Class<?> entityClass) {
        transactional(em -> {
            em.createQuery("delete from " + entityClass.getSimpleName()).executeUpdate();
            return null;
        }, String.format("Não pode deletar a entidade %s", entityClass.getSimpleName()));
    }
}
