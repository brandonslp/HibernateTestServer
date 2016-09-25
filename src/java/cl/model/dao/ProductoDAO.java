/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import cl.model.pojos.Productos;
import java.util.List;
/**
 *
 * @author brand
 */
public class ProductoDAO {
    
    public void ingresarProducto(Productos p){
        SessionFactory sf = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sf=HibernateUtil.getSessionFactory();
            session = sf.openSession();
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            throw  new RuntimeException("No se pudo guardar el producto");
        }
    }
    
    public String consultarProducto(int cod){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Productos p = (Productos) session.get(Productos.class, cod);
        session.close();
        if (p!=null) {
            return "El producto de codigo "+ p.getId() +" cuyo nombre es "+p.getName()+" Cuesta "+p.getPrice()+" tiene stock "+ p.getQuantity()+" Descripcion "+p.getDescription();
        }else{
            return "El producto de codigo "+ cod+ "No existe";
        }
    }
    
    public List<Productos> getAll(){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery("from Productos");
        List<Productos> listP = query.list();
        session.close();
        return listP;
    }
    
    public boolean deleteProduct(int cod){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(session.get(Productos.class, cod));
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
        
    }
}
