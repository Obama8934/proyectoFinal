package com.app.persistencia;

import java.util.List;

import com.app.logica.Prestamo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PrestamoJpaController {

	// "Fábrica" de conexiones (EntityManagerFactory).
	// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
	private EntityManagerFactory emf = null;

	// En el constructor decimos con qué unidad de persistencia vamos a trabajar.
	// Esa unidad está definida en persistence.xml con el nombre "ejemplojpa".
	public PrestamoJpaController() {
		emf = Persistence.createEntityManagerFactory("unidadBiblioteca");
	}

	// Método que crea y devuelve un nuevo "EntityManager".
	// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para guardar un Artista en la base de datos.
	public void crearPres(Prestamo pres) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (pedimos un empleado).
			em = getEntityManager();
			
			// Iniciamos una transacción: empieza la "promesa".
			em.getTransaction().begin();
			
			// Guardamos el objeto en la base (pero todavía no está confirmado).
			em.persist(pres);
			
			// Confirmamos la transacción: ahora sí el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
	}

	public Prestamo buscarPorIdPRESTAMO(int id) {

	    EntityManager em = getEntityManager();

	    Prestamo pres = em.find(Prestamo.class, id);

	    em.close();

	    return pres;
	}
	
	public List<Prestamo> buscarTodosPrestamos() {

	    EntityManager em = getEntityManager();

	    String jpql = "SELECT a FROM " + Prestamo.class.getSimpleName() + " a";
	    
	    List<Prestamo> lista = em.createQuery(jpql,Prestamo.class).getResultList();
	    
	    em.close();

	    return lista;
	}
	
	public void destruirPrestamo(int id) {

	    EntityManager em = getEntityManager();

	    em.getTransaction().begin();

	    Prestamo pres = em.find(Prestamo.class, id);

	    if (pres != null) {
	        em.remove(pres);
	    }

	    em.getTransaction().commit();
	    System.out.println("usuario eliminado con exito");
	    em.close();
	}
	
	public void editarPrestamo(Prestamo pres) {

	    EntityManager em = getEntityManager();

	    em.getTransaction().begin();

	    em.merge(pres);

	    em.getTransaction().commit();

	    em.close();
	}
}
