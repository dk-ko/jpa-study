package controller;

import java.util.List;

import javax.persistence.EntityManager;

import model.Item;
import model.Category;

public class CategoryController {
	
	public List<Item> find(EntityManager em,Long search){
		Category category = em.find(Category.class, search);
		List<Item> items = category.getItems();
		
		return items;
	}
	
}
