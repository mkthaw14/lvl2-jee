import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmitt.model.entity.Level;

@TestMethodOrder(OrderAnnotation.class)
class Testing_1
{

	static EntityManagerFactory emf;
	static EntityManager em;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		emf = Persistence.createEntityManagerFactory("z2p-bootcamp-management");
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
	void test()
	{
		Level l1 = em.find(Level.class, 1);
		l1.setName("Lol");
		em.getTransaction().begin();
		em.persist(l1);
		em.getTransaction().commit();
	}

}
