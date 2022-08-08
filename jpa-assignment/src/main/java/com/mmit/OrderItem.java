package com.mmit;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table( uniqueConstraints = {
		@UniqueConstraint(columnNames = {"order_id", 
				"product_id"})
})
public class OrderItem implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade ={ PERSIST, REMOVE } )
	private Order order;
	
	@ManyToOne(cascade = { PERSIST, REMOVE })
	private Product product;
	
	private int subQty;

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public int getSubQty()
	{
		return subQty;
	}

	public void setSubQty(int subQty)
	{
		this.subQty = subQty;
	}
}
