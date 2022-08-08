package com.mmit;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Delivery
 *
 */
@Entity
@Table(name = "deliveries")
public class Delivery implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "delivery_man", nullable = false)
	private String deliveryMan;
	@OneToOne(optional = false, cascade = { PERSIST, REMOVE })
	private Order order;
	
	@Column(name = "delivery_date")
	private LocalDate deliveryDate;
	@Column(name = "is_delivered")
	private boolean isDelivered;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getDeliveryMan()
	{
		return deliveryMan;
	}
	public void setDeliveryMan(String deliveryMan)
	{
		this.deliveryMan = deliveryMan;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order = order;
	}
	public LocalDate getDeliveryDate()
	{
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}
	public boolean isDelivered()
	{
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered)
	{
		this.isDelivered = isDelivered;
	}
	
	
   
}
