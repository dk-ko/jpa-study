package model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.Member;
import model.entity.item.Album;
import model.entity.item.Item;

public class Main {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("07-jpa-model4");
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
		Album album = new Album();
		album.setId(1l);
		album.setName("Sky");
		album.setPrice(10000);
		album.setStockQuantity(100);
		album.setArtist("Jaurim");
		album.setEtc("memo");
		
		// ERROR : detached entity passed to persist
		// cascade 공부 후 다시 수정 
		em.persist(album);
		
		Album findAlbum = em.find(Album.class, 1l);
		System.out.println(findAlbum);
		
		Item findItem = em.find(Item.class, 1l);
		System.out.println(findItem);
		
		Member member = new Member();
		member.setId(1l);
		member.setName("고다경");
		member.setCreatedDate(new Date());
		
		em.persist(member);
		Member findMember = em.find(Member.class, 1l);
		System.out.println(findMember);
		
		member.setName("고다경1");
		member.setLastModifiedDate(new Date());
		findMember = em.find(Member.class, 1l);
		System.out.println(findMember);
		
	}
}
