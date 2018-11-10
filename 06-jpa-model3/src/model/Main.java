package model;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import controller.CategoryController;
import controller.ItemController;
import model.Category;
import model.Item;
import model.OrderItem;

/*
 * 요구 사항 추가 
 * - 상품을 주문할 때 배송 정보를 입력할 수 있다. 주문과 배송은 일대일 관계
 * - 상품을 카테고리로 구분할 수 있다. 
 */

public class Main {
	static CategoryController categoryController = new CategoryController();
	static ItemController itemController = new ItemController();
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("06-jpa-model3");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			// 비즈니스 로직 
			logic(em);
			tx.commit();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback(); // 에러 발생 시 트랜잭션 롤백 
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	private static void logic (EntityManager em){
		// Item 저장
		Item item1 = new Item();
		item1.setName("molskin");
		item1.setPrice(25000);
		item1.setStockquantity(10);
		em.persist(item1);
		
		// Category 저장 
		Category category = new Category();
		category.setParent(null);
		category.setName("문구");
		category.addItem(item1);
		em.persist(category);
		
		
		
		// Member 저장
		Member member = new Member();
		
		
		// Order 저장
		Orders order = new Orders();
		
		// Delivery 저장
		Delivery delivery = new Delivery();
		
		// 카테고리에 해당하는 물품들 출력
		List<Item> items= categoryController.find(em, 1L);
		System.out.println(items.toString());
		
		// 해당 물품의 카테고리 출력
		
		
		// 회원의 배송 정보(상태) 출력 
	}
}
