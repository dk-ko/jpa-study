package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Orders {
	@Id @GeneratedValue
	@Column(name="order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member; // 주문 회원 
	
	@OneToMany(mappedBy="order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	@OneToOne
	@JoinColumn(name="delivery_id")
	private Delivery delivery; // 배송 정보 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderdate;	 // 주문 시간 
	
	@Enumerated(EnumType.STRING)
	private orderstatus status; // 주문 상태 

	// 연관 관계 메소드 
	public void setMember(Member member) {
		if(this.member != null) {
			this.member.getOrders().remove(this);
		}
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	// getter, setter

	public Long getId() {
		return id;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public orderstatus getStatus() {
		return status;
	}

	public void setStatus(orderstatus status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}
	
}

enum orderstatus {
	order, cancle
}