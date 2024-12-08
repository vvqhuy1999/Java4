package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Xjpa {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("Lab6");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}

