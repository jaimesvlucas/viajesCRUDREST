/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class ViajesCRUD {
    public static int destroyDestino(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_viajesCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from Destinos d WHERE d.id =" + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;  
    }
      public static List<Destinos> getDestinos() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_viajesCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM destinos";
        Query q = manager.createNativeQuery(sql, Destinos.class); //método para consultas en SQL
        List<Destinos> destinosBD = q.getResultList();

        return destinosBD;
    }
      
     public static Destinos getDestino(int id) {  //devuelve un objeto de clase Productos
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_viajesCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT d FROM Destinos d WHERE d.id=" + id;
        Query q = manager.createQuery(sql,Destinos.class); //método para consultas en SQL
        Destinos miDestino =  ( Destinos ) q.getSingleResult(); //para un único registro
        manager.close();
        return  miDestino;
    } 
    
    public static int actualizaDestino(Destinos miDestino) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_viajesCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Destinos d SET d.ciudad = :ciudad, d.imagen = :imagen, d.precio = :precio WHERE d.id = :id";
        Query q = manager.createQuery(sql,Destinos.class);
        q.setParameter("id", miDestino.getId());
        q.setParameter("ciudad", miDestino.getCiudad());
        q.setParameter("imagen", miDestino.getImagen());
        q.setParameter("precio", miDestino.getPrecio());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
        return filasAfectadas;      
    }
    
    public static void insertaDestino(Destinos destino) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_viajesCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(destino);
        manager.getTransaction().commit();
        }
}
