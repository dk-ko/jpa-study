package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-model2");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			logic(em);
			tx.commit();
		}catch (Exception e){
			tx.rollback();
		}finally{
			em.close();
		}
		emf.close();
	}
	
	private static void logic(EntityManager em){
		// 주문한 회원 객체 그래프로 탐색 - Order > Member 
		Order order = em.find(Order.class, 1);
		Member member = order.getMember();
		System.out.println(member.toString());
		System.out.println(member.getName());
		
		// 주문한 상품 하나 객체 그래프로 탐색 - Order > Order Item 
		OrderItem orderItem = order.getOrderitems().get(0);
		Item item = orderItem.getItem();
		System.out.println(item.toString());
	}
}
