package jpa.start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 엔티티 매니터 팩토리 생성
		// persistence.xml의 설정 정보를 사용해 생성.
		// JPA 기반 객체를 만들고 JPA 구현체에 따라 데이터베이스 커넥션 풀도 생성. 
		// 생성 비용이 크기 때문에 Application 전체에 딱 한번만 생성해 공유해 사용하는 것이 좋다. 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-start");
		// 엔티티 매니저 생성 
		// JPA 대부분의 기능을 엔티티 매니저가 제공.
		// 엔티티 매니저를 사용해 DB에 CRUD를 할 수 있음.
		// 데이터베이스 커넥션 때문에 스레드간 공유/재사용하면 안된다. 
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 획득 
		EntityTransaction tx = em.getTransaction();
		
		// JPA는 항상 트랜잭션 안에서 데이터를 변경해야한다.
		// 트랜잭션 없이 데이터를 변경하면 예외가 발생한다.
		try {
			tx.begin(); // 트랜잭션 시작 
			logic(em); // 비즈니스 로직 실행 
			tx.commit(); // 정상 동작 시 트랜잭션 커밋 
		} catch (Exception e) {
 			tx.rollback(); // 예외 발생 시 트랜잭션 롤백 
		} finally {
			em.close(); // 엔티티 매니저 종료 
		}
		emf.close(); // 엔티티 매니저 팩토리 종료 
	}
	
	// 비즈니스 로직 
	private static void logic (EntityManager em){
		String id ="id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("koda");
		member.setAge(26);
		
		// 등록
		em.persist(member);
		
		// 수정
		// entity 값만 변경하더라도 JPA가 변경 내역을 추적해 데이터를 수정한다. (UPDATE SQL)
		member.setAge(20);
		
		// 한 건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember="+findMember.getUsername() 
			+ ", age="+findMember.getAge());
		
		// 목록 조회 
		// JPQL(Java Persistence Query Language) 
		// 엔티티 객체를 대상으로 쿼리한다. 
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		// 쿼리 객체를 생성한 뒤 쿼리 객체의 getResultList()를 사용한다. 
		System.out.println("members.size="+members.size());
		
		// 삭제
		em.remove(member);
		
	}

}
