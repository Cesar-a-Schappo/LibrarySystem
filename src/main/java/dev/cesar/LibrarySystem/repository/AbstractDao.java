package dev.cesar.LibrarySystem.Dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // --------------------
    // Basic CRUD
    // --------------------

    public void persist(T entity) {
        em.persist(entity);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(entity);
    }

    public void removeById(Serializable id) {
        T entity = select(id);
        if (entity != null) {
            remove(entity);
        }
    }

    @Transactional(readOnly = true)
    public T select(Serializable id) {
        return em.find(clazz, id);
    }

    // --------------------
    // Select All
    // --------------------

    @Transactional(readOnly = true)
    public List<T> selectAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        cq.select(cq.from(clazz));
        return em.createQuery(cq).getResultList();
    }

    // --------------------
    // Select by column
    // --------------------

    @Transactional(readOnly = true)
    public T selectOne(String column, Object value) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            ParameterExpression<Object> param = cb.parameter(Object.class);
            cq.select(root).where(cb.equal(root.get(column), param));

            TypedQuery<T> q = em.createQuery(cq);
            q.setParameter(param, value);

            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<T> selectMany(String column, Object value) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            ParameterExpression<Object> param = cb.parameter(Object.class);
            cq.where(cb.equal(root.get(column), param));

            TypedQuery<T> q = em.createQuery(cq);
            q.setParameter(param, value);

            return q.getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }

    // --------------------
    // Count
    // --------------------

    @Transactional(readOnly = true)
    public long countAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(clazz)));
        return em.createQuery(cq).getSingleResult();
    }
}
