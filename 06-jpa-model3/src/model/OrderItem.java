package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem {
	@Id @GeneratedValue
	@Column(name="order_item_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item; 		// 주문 상품 
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders order; 	// 주문 
	
	private int orderprice; // 주문 가격 
	private int count; 		// 주문 수량 
	
	// getter, setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public int getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
