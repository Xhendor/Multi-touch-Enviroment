/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.integration.persistence;

import java.util.List;

/**
 *
 * @author laboratorio
 */
public interface InterfaceDAO<T> {

    public void save(T t);

    public void delete(T t);

    public T find(int id);

    public List<T> findAll();

    public void saveOrUpdate(T instance);

    public List<T> executeQuery(String query);
    
    //TODO: Es posible agregar los métodos para el Criteria
     
}