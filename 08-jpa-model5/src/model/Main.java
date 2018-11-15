package model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.Delivery;
import model.entity.Member;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.item.Album;
import model.entity.item.Item;

public class Main {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("08-jpa-model5");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			logic(em);
			tx.commit();
		} catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void logic(EntityManager em) {
		Delivery delivery = new Delivery();
//		em.persist(delivery); // persist
		
		OrderItem orderItem1 = new OrderItem();
		OrderItem orderItem2 = new OrderItem();
//		em.persist(orderItem1); // persist
//		em.persist(orderItem2); // persist
		
		Order order = new Order();
		order.setDelivery(delivery);
		order.addOrderItem(orderItem1);
		order.addOrderItem(orderItem2);

		em.persist(order); // persist
	}
}
