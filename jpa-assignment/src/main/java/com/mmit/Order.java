package com.mmit;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = { PERSIST, REMOVE })
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column(name = "shipping_address")
	private String shippingAddress;
	@Column(name = "shipping_phone", nullable = false)
	private String shippingPhone;
	@Column(name = "total_amount")
	private double totalAmount;
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@OneToOne(mappedBy = "order", cascade = { PERSIST, REMOVE })
	private Delivery delivery;
	
	public Delivery getDelivery()
	{
		return delivery;
	}
	public void setDelivery(Delivery delivery)
	{
		this.delivery = delivery;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
	public String getShippingPhone()
	{
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone)
	{
		this.shippingPhone = shippingPhone;
	}
	public double getTotalAmount()
	{
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	public LocalDate getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate)
	{
		this.orderDate = orderDate;
	}
   
	
}
