package com.app.persistencia;

import java.util.List;

import com.app.logica.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioJpaController {

	// "Fábrica" de conexiones (EntityManagerFactory).
				// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
				private EntityManagerFactory emf = null;

				// En el constructor decimos con qué unidad de persistencia vamos a trabajar.
				// Esa unidad está definida en persistence.xml con el nombre "ejemplojpa".
				public UsuarioJpaController() {
					emf = Persistence.createEntityManagerFactory("unidadBiblioteca");
				}

				// Método que crea y devuelve un nuevo "EntityManager".
				// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
				public EntityManager getEntityManager() {
					return emf.createEntityManager();
				}

				// Método para guardar un Artista en la base de datos.
				public void crearUsu(Usuario usu) {
					EntityManager em = null;
					try {
						// Abrimos la conexión (pedimos un empleado).
						em = getEntityManager();
						
						// Iniciamos una transacción: empieza la "promesa".
						em.getTransaction().begin();
						
						// Guardamos el objeto en la base (pero todavía no está confirmado).
						em.persist(usu);
						
						// Confirmamos la transacción: ahora sí el objeto queda guardado.
						em.getTransaction().commit();
					} finally {
						// Cerramos la conexión (el empleado ya no hace falta).
						if (em != null) {
							em.close();
						}
					}
				}

				public Usuario buscarPorIdUSUARIO(int id) {

				    EntityManager em = getEntityManager();

				    Usuario usu = em.find(Usuario.class, id);

				    em.close();

				    return usu;
				}
				
				public List<Usuario> buscarTodosUsuarios() {

				    EntityManager em = getEntityManager();

				    String jpql = "SELECT a FROM " + Usuario.class.getSimpleName() + " a";
				    
				    List<Usuario> lista = em.createQuery(jpql,Usuario.class).getResultList();
				    
				    em.close();

				    return lista;
				}
				
				public void destruirUsuario(int id) {

				    EntityManager em = getEntityManager();

				    em.getTransaction().begin();

				    Usuario usu = em.find(Usuario.class, id);

				    if (usu != null) {
				        em.remove(usu);
				    }

				    em.getTransaction().commit();
				    System.out.println("usuario eliminado con exito");
				    em.close();
				}
				
				public void editarUsuario(Usuario usu) {

				    EntityManager em = getEntityManager();

				    em.getTransaction().begin();

				    em.merge(usu);

				    em.getTransaction().commit();

				    em.close();
				}
}
