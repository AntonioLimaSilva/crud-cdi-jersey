package br.com.luciano.util.api;


import java.util.List;

public interface BasicDAO {
	
    Object inserir(Object entity);

    Object buscar(Class<?> entityClass, long entityId);

    Object alterar(Object entity);

    List<?> lista(Class<?> entityClass);

    long contador(Class<?> entityClass);

    void deletar(Class<?> entityClass);
}
