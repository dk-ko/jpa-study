import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
 * 요구 사항 추가 
 * - 상품을 주문할 때 배송 정보를 입력할 수 있다. 주문과 배송은 일대일 관계
 * - 상품을 카테고리로 구분할 수 있다. 
 */

public class Main {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("06-jpa-model3");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			// 비즈니스 로직 
			tx.commit();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback(); // 에러 발생 시 트랜잭션 롤백 
		} finally {
			em.close();
		}
		
		emf.close();
	}
}
