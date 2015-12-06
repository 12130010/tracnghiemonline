package dao;

import java.util.List;

import org.hibernate.HibernateException;

public interface GenericDao<E,K> {
    public K save(E entity) throws HibernateException;
    public void saveOrUpdate(E entity) throws HibernateException;
    public void update(E entity) throws HibernateException;
    public void remove(E entity)throws HibernateException;
    public E find(K key)throws HibernateException;
    public List<E> getAll() throws HibernateException;
}