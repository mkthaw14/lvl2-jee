package com.mmit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmit.Customer.Gender;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class Test_1
{
	static EntityManagerFactory emf;
	static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		emf = Persistence.createEntityManagerFactory("jpa-assignment");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception
	{
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		em.close();
	}

	@Test
	@Order(0)
	void test()
	{
		Customer c1 = createCustomer("Hello", "No", Gender.Male, "1111", 
				List.of("photo 1", "photo 1"));
		
		User user1 = createUser("hell@gmail.com", "hell", "1234", true);
		
		Customer c2 = createCustomer("Win", "BB", Gender.Female, "2222", 
				List.of("photo 1", "photo 2"));
		
		User user2 = createUser("win@gmail.com", "BB", "1234", true);
		
		User user3 = createUser("myo@gmail.com", "AA", "1234", true);
		
		Merchant m1 = createMerchant("9999", "shop 1", "Yangon");
		
		c1.setUser(user1);	
		c2.setUser(user2);
		m1.setUser(user3);
		
		
		em.getTransaction().begin();
		em.persist(c1);
		em.persist(c2);
		em.persist(m1);
		em.getTransaction().commit();
	}
	
	//@Test
	//@Order(1)
	void test2()
	{
		var c1 = em.find(Customer.class, 1);
		c1.getPhotos().remove(0);
		em.getTransaction().begin();
		//em.remove(c1);
		em.persist(c1);
		em.getTransaction().commit();
	}
	
	@Test
	@Order(2)
	void test3()
	{		
		Category c1 = createCategory("category 1");
		Product p1 = createProduct(c1, "apple", 12.99);
		Product p2 = createProduct(c1, "orange", 9.99);
		
		Category c2 = createCategory("category 2");
		Product p3 = createProduct(c2, "microsoft", 99.99);
		Product p4 = createProduct(c2, "linux", 0.0);
		
		
		em.getTransaction().begin();
		em.persist(c1);
		em.persist(c2);
		em.getTransaction().commit();
	}

	@Test
	@Order(3)
	void test4()
	{
		com.mmit.Order o1 = createOrder("911", 35.55, em.find(Customer.class, 1));
		com.mmit.Order o2 = createOrder("999", 40.55, em.find(Customer.class, 1));
		com.mmit.Order o3 = createOrder("888", 60.55, em.find(Customer.class, 2));
		
		Delivery d1 = createDelivery(o1, "delivery guy1");
		Delivery d2 = createDelivery(o2, "delivery guy2");
		Delivery d3 = createDelivery(o3, "delivery guy3");
		
		var p1 = em.find(Product.class, 1);
		var p2 = em.find(Product.class, 2);
		var p3 = em.find(Product.class, 3);
		
		OrderItem item1 = createOrderItem(o1, p1, 12);
		OrderItem item2 = createOrderItem(o1, p2, 14);
		OrderItem item3 = createOrderItem(o2, p3, 15);
		OrderItem item4 = createOrderItem(o2, p2, 19);
		OrderItem item5 = createOrderItem(o2, p1, 8);
		
		em.getTransaction().begin();
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
		em.persist(item4);
		em.persist(item5);
		em.getTransaction().commit();
	}

	private OrderItem createOrderItem(com.mmit.Order order, Product product, int subQty)
	{
		OrderItem item = new OrderItem();
		item.setOrder(order);
		item.setProduct(product);
		item.setSubQty(subQty);
		
		return item;
	}
	

	
	private Category createCategory(String name)
	{
		Category c = new Category();
		c.setName(name);
		c.setDescription("Bla Bla");
		return c;
	}
	
	private Product createProduct(Category category, String name, double price)
	{
		Product p = new Product();
		p.setName(name);
		p.setPrice(price);
		p.setCategory(category);
		category.addProduct(p);
		return p;
	}
	
	private Delivery createDelivery(com.mmit.Order o1, String deliveryName)
	{
		Delivery d1 = new Delivery();
		d1.setDeliveryMan(deliveryName);
		d1.setDeliveryDate(LocalDate.now());
		d1.setOrder(o1);
		
		o1.setDelivery(d1);
		return d1;
	}

	private com.mmit.Order createOrder(String shippingPhone, double totalAmt, Customer customer)
	{
		com.mmit.Order o1 = new com.mmit.Order();
		o1.setOrderDate(LocalDate.now());
		o1.setShippingPhone(shippingPhone);
		o1.setTotalAmount(totalAmt);
		
		o1.setCustomer(customer);
		return o1;
	}
	
	Customer createCustomer(String firstName, String lastName, Gender gender, String phone, List<String> photos)
	{
		Customer c = new Customer();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setGender(gender);
		c.setPhone(phone);
		c.setPhotos(photos);
		
		return c;
	}
	
	User createUser(String email, String name, String password, boolean active)
	{
		User user = new User();
		user.setEmail(email);	
		user.setUserName(name);
		user.setPassword(password);
		user.setActive(active);
		
		return user;
	}
	
	Merchant createMerchant(String pphone, String shopName, String address)
	{
		Merchant m = new Merchant();
		m.setPrimaryPhone(pphone);
		m.setShopName(shopName);
		m.setAddress(address);
		
		return m;
	}

}
