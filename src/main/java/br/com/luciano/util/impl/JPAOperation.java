package br.com.luciano.util.impl;

import javax.persistence.EntityManager;

@FunctionalInterface
interface JPAOperation {
    Object execute(EntityManager entityManager);
}
