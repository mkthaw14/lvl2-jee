package com.mmit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "customers")
public class Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	private String phone;
	
	@Enumerated(STRING)
	private Gender gender;
	public enum Gender
	{
		Male, Female
	}
	
	@OneToOne(cascade = { PERSIST, REMOVE })
	@MapsId
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "customer_id")
	private User user;
	
	@ElementCollection
	@CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "photo")
	private List<String> photos = new ArrayList<String>();
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public Gender getGender()
	{
		return gender;
	}
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public List<String> getPhotos()
	{
		return photos;
	}
	public void setPhotos(List<String> photos)
	{
		this.photos = photos;
	}
	
	public void addPhoto(String photoName)
	{
		this.photos.add(photoName);
	}
	
	public void removePhoto(String photoName)
	{
		this.photos.remove(photoName);
	}
	
}
