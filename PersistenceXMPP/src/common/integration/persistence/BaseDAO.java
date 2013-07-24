package common.integration.persistence;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;



/**
 *
 * @author emmanuelle
 */
public class BaseDAO<T> implements InterfaceDAO<T> {

    private Class<T> tipo;
    
    public BaseDAO(){}


    public BaseDAO(Class<T> tipo) {
        this.tipo = tipo;
        System.out.println("Se crea el bean con la clase: " + tipo.getName());
    }
  

    @Override
    public void save(T t) {
        System.out.println("Salvando en " + tipo.getSimpleName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(t);
            session.getTransaction().commit();
                   session.flush();
        } catch (Exception x) {
            x.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            
     
            session.close();
            
        }
    }

    @Override
    public void saveOrUpdate(T t) {
        System.out.println("Salvando o actualizando en " + tipo.getSimpleName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        
        session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            session.getTransaction().commit();
               session.flush();

        } catch (Exception x) {
            x.printStackTrace();
            session.getTransaction().rollback();
        } finally {
         
            session.close();
        }
    }

    @Override
    public void delete(T t) {
        System.out.println("Borrando en " + tipo.getSimpleName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception x) {
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<T> findAll() {
             System.out.println("Consultando en " + tipo.getSimpleName());
        List<T> result = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            result = session.createQuery("from " + getTipo().getCanonicalName()).list();
        } catch (Exception x) {
            x.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        return result;
    }

    @Override
    public List<T> executeQuery(String query) {
        List<T> result = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
           ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
      
        try {
              session.beginTransaction();
                result = session.createQuery(query).list();
        } catch (Exception x) {
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        return result;
    }

    @Override
    public T find(int id) {
        Object obj = null;
      //    System.out.println("El nombre" + id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String identificador = catMeta.getIdentifierPropertyName();
        
        String nombre = getTipo().getSimpleName();
    //    System.out.println("El nombre" + nombre);
        try {
            session.beginTransaction();
            obj = session.createQuery("from " + nombre
                    + " as " + nombre.toLowerCase() + " where " + nombre.toLowerCase() + "." + identificador + " = :id").setString("id", String.valueOf(id)).uniqueResult();
        } catch (Exception x) {
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        return (T) obj;
    }
    //TODO: Crear método para consultar por cuip
    
   
    
   
    
    
    
    
    
    
    public int getMaxId(){
        Integer maxId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String identificador = catMeta.getIdentifierPropertyName();
        String nombre = getTipo().getSimpleName();
        System.out.println(nombre);
        try {
            session.beginTransaction();
            maxId = (Integer) session.createQuery("Select MAX("+identificador+") as maxId from " + nombre).uniqueResult();
        } catch (Exception x) {
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        
        return (maxId != null)? maxId : 0;
    
    }

    /**
     * @return the tipo
     */
    public Class<T> getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Class<T> tipo) {
        this.tipo = tipo;
    }

   
}
