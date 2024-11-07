package Daos;

import Entity.employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.XJPA;

public class employeeDaoImpl implements employeeDAO{
    @Override
    public void create(employee item) {

        EntityManager em = XJPA.getEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(item);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public static void main(String[] args) {

        employee st = new employee("hv", 1000.0);
        employeeDaoImpl edao = new employeeDaoImpl();
        edao.create(st);
    }
}
