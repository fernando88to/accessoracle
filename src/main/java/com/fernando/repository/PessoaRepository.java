package com.fernando.repository;

import com.fernando.domain.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
    @PersistenceContext
    EntityManager em;
    public List<Pessoa> consultar() {
        return listAll();
    }

    /*public List<Pessoa> consultar2() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);


        return em.createQuery(criteriaQuery).getResultList();



    }*/
    public List<Pessoa> consultar2() {
        CriteriaBuilder  criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> studentRoot = criteriaQuery.from(Pessoa.class);
        criteriaQuery.select(studentRoot);

        //Predicate predicate = criteriaBuilder.equal(studentRoot.get("id"), 1l);
        //Predicate predicate2 = criteriaBuilder.equal(studentRoot.get("nome"), "Fernando");
        ///Predicate predicate = criteriaBuilder.like(studentRoot.get("nome"), "%E%");

        Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(studentRoot.get("nome")),"%E%");
        //criteriaQuery.where(predicate, predicate2);
        criteriaQuery.where(predicate);
        TypedQuery<Pessoa> typedQuery = em.createQuery(criteriaQuery);
        List<Pessoa> studentList = typedQuery.getResultList();
        return studentList;

    }



}
