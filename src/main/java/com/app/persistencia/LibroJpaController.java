package com.app.persistencia;

import java.util.List;

import com.app.logica.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LibroJpaController {

	// "Fábrica" de conexiones (EntityManagerFactory).
			// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
			private EntityManagerFactory emf = null;

			// En el constructor decimos con qué unidad de persistencia vamos a trabajar.
			// Esa unidad está definida en persistence.xml con el nombre "ejemplojpa".
			public LibroJpaController() {
				emf = Persistence.createEntityManagerFactory("unidadBiblioteca");
			}

			// Método que crea y devuelve un nuevo "EntityManager".
			// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
			public EntityManager getEntityManager() {
				return emf.createEntityManager();
			}

			// Método para guardar un Artista en la base de datos.
			public void crearLib(Libro lib) {
				EntityManager em = null;
				try {
					// Abrimos la conexión (pedimos un empleado).
					em = getEntityManager();
					
					// Iniciamos una transacción: empieza la "promesa".
					em.getTransaction().begin();
					
					// Guardamos el objeto en la base (pero todavía no está confirmado).
					em.persist(lib);
					
					// Confirmamos la transacción: ahora sí el objeto queda guardado.
					em.getTransaction().commit();
				} finally {
					// Cerramos la conexión (el empleado ya no hace falta).
					if (em != null) {
						em.close();
					}
				}
			}

			public Libro buscarPorIdLIBRO(int id) {

			    EntityManager em = getEntityManager();

			    Libro lib = em.find(Libro.class, id);

			    em.close();

			    return lib;
			}
			
			public List<Libro> buscarTodosLibros() {

			    EntityManager em = getEntityManager();

			    String jpql = "SELECT a FROM " + Libro.class.getSimpleName() + " a";
			    
			    List<Libro> lista = em.createQuery(jpql,Libro.class).getResultList();
			    
			    em.close();

			    return lista;
			}
			
			public void destruirLibro(int id) {

			    EntityManager em = getEntityManager();

			    em.getTransaction().begin();

			    Libro lib = em.find(Libro.class, id);

			    if (lib != null) {
			        em.remove(lib);
			    }

			    em.getTransaction().commit();
			    System.out.println("libro eliminado con exito");
			    em.close();
			}
			
			public void editarLibro(Libro lib) {

			    EntityManager em = getEntityManager();

			    em.getTransaction().begin();

			    em.merge(lib);

			    em.getTransaction().commit();

			    em.close();
			}
}
