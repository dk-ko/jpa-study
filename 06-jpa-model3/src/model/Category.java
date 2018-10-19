package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="category")
public class Category {
	@Id @GeneratedValue
	@Column(name="category_id")
	private long id;
	
	private String name;
	
	// Category > item 방향 
	// M:N 관계 연관관계의 주인에서 @JoinTable 설정
	// category_item 테이블을 직접 사용하지 않는다. 
	// 연결테이블에 필드가 추가되면 이 방식을 사용할 수 없다.
	// > 연결 엔티티를 만들어서 일대다/다대일 관계로 분리하는 것이 좋다.
	@ManyToMany
	@JoinTable(name="category_item",
			joinColumns=@JoinColumn(name="category_id"),
			inverseJoinColumns=@JoinColumn(name="item_id"))
	private List<Item> items = new ArrayList<Item>();
	
	// 카테고리의 계층 구조를 위한 필드들 
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private List<Category> child = new ArrayList<Category>();
	
	// 연관관계 메소드 
	public void addChildCategory(Category child){
		this.child.add(child);
		child.setParent(this);
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	// getter, setter
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChild() {
		return child;
	}
	public void setChild(List<Category> child) {
		this.child = child;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
