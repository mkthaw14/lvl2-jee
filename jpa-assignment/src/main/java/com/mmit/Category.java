package com.mmit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;


/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = { PERSIST, REMOVE })
	private List<Product> products = new ArrayList<Product>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}
	
	public void addProduct(Product item)
	{
		item.setCategory(this);
		this.products.add(item);
	}
	
	public void removeProduct(Product item)
	{
		item.setCategory(null);
		this.products.remove(item);
	}
   
}
