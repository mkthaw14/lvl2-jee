package com.mmit;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "merchants")
public class Merchant implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
    @Column(name = "primary_phone", unique = true)
    private String primaryPhone;
    @Column(name = "secondary_phone")
    private String secondaryPhone;
    @Column(name = "shop_name", nullable = false)
    private String shopName;
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @OneToOne(cascade = { PERSIST, REMOVE })
    @MapsId
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "merchant_id")
    private User user;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getPrimaryPhone()
	{
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone)
	{
		this.primaryPhone = primaryPhone;
	}
	public String getSecondaryPhone()
	{
		return secondaryPhone;
	}
	public void setSecondaryPhone(String secondaryPhone)
	{
		this.secondaryPhone = secondaryPhone;
	}
	public String getShopName()
	{
		return shopName;
	}
	public void setShopName(String shopName)
	{
		this.shopName = shopName;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
    
    
}
