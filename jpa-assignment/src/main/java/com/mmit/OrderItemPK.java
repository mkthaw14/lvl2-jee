package com.mmit;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemPK implements Serializable
{
	private int order_id;
	private int product_id;
	@Override
	public int hashCode()
	{
		return Objects.hash(order_id, product_id);
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return order_id == other.order_id && product_id == other.product_id;
	}
}
