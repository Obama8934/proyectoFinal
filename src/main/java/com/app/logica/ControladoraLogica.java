package com.app.logica;

import java.util.List;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {


	// La controladora de persistencia se encarga de hablar con la BD.
		// Desde aquí la usamos como "puente" para guardar datos.
		ControladoraPersistencia controlPersis = new ControladoraPersistencia();

		// ----- ARTISTA -----
		// Método de la capa lógica para crear un artista.
		// Antes de guardarlo en la BD, se hacen validaciones.
		public void crearLibro(Libro libro) throws Exception {
			// Validación: el artista debe tener un nombre
			if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
				throw new Exception("el libro debe tener titulo.");
			}
			// Validación: el artista debe tener un género musical
			if (libro.getAutor() == null || libro.getAutor().isEmpty()) {
				throw new Exception("el libro debe tener autor.");
			}
			// Validación: la edad debe ser positiva
			if (libro.getCosto() < 0) {
				throw new Exception("el libro no puede valer 0.");
			}
			// Si pasa todas las validaciones, recién ahí se manda a la persistencia
			controlPersis.crearLibro(libro);
		}
		
		public void editarLibro(Libro libro) throws Exception {
			// Validación: el artista debe tener un nombre
			if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
				throw new Exception("el libro debe tener titulo.");
			}
			// Validación: el artista debe tener un género musical
			if (libro.getAutor() == null || libro.getAutor().isEmpty()) {
				throw new Exception("el libro debe tener autor.");
			}
			// Validación: la edad debe ser positiva
			if (libro.getCosto() < 0) {
				throw new Exception("el libro no puede valer 0.");
			}
			// Si pasa todas las validaciones, recién ahí se manda a la persistencia
			controlPersis.editarLibro(libro);
		}

		public Libro buscarPorIdLibro(int id) {

		    return controlPersis.buscarPorIdLIB(id);
		}
		
		
		public List<Libro> listaLibros() {

		    return controlPersis.buscarTodosLosLibros();
		}
		
		
		public void eliminarLibro(int id)throws Exception {
			
			Libro lib = controlPersis.buscarPorIdLIB(id);
			
			if(lib == null) {
				throw new Exception("no existe un celular con ID " + id);
			}
			controlPersis.eliminarLibro(id);
			
		}
		
		/*-----------------------*/
		/*USUARIO*/
		
		// ----- ARTISTA -----
				// Método de la capa lógica para crear un artista.
				// Antes de guardarlo en la BD, se hacen validaciones.
				public void crearUsuario(Usuario usu) throws Exception {
					// Validación: el artista debe tener un nombre
					if (usu.getNombre() == null || usu.getNombre().isEmpty()) {
						throw new Exception("el usuario debe tener nombre.");
					}
					// Validación: el artista debe tener un género musical
					if (usu.getApellido() == null || usu.getApellido().isEmpty()) {
						throw new Exception("el usuario debe tener apellido.");
					}
					// Validación: la edad debe ser positiva
					if (usu.getEmail() == null || usu.getEmail().isEmpty()) {
						throw new Exception("el usuario debe tener gmail.");
					}
					// Si pasa todas las validaciones, recién ahí se manda a la persistencia
					controlPersis.crearUsuario(usu);
				}
				
				public void editarUsuario(Usuario usu) throws Exception {
					// Validación: el artista debe tener un nombre
					if (usu.getNombre() == null || usu.getNombre().isEmpty()) {
						throw new Exception("el usuario debe tener nombre.");
					}
					// Validación: el artista debe tener un género musical
					if (usu.getApellido() == null || usu.getApellido().isEmpty()) {
						throw new Exception("el usuario debe tener apellido.");
					}
					// Validación: la edad debe ser positiva
					if (usu.getEmail() == null || usu.getEmail().isEmpty()) {
						throw new Exception("el usuario debe tener gmail.");
					}
					// Si pasa todas las validaciones, recién ahí se manda a la persistencia
					controlPersis.editarUsuario(usu);
				}

				public Usuario buscarPorIdUsuario(int id) {

				    return controlPersis.buscarPorIdUSU(id);
				}
				
				
				public List<Usuario> listaUsuarios() {

				    return controlPersis.buscarTodosLosUsuarios();
				}
				
				
				public void eliminarUsuario(int id)throws Exception {
					
					Usuario usu = controlPersis.buscarPorIdUSU(id);
					
					if(usu == null) {
						throw new Exception("no existe un celular con ID " + id);
					}
					controlPersis.eliminarUsuario(id);
					
				}
		
				/*-----------------------------------------*/
				/*PRESTAMO*/
				
				// ----- ARTISTA -----
				// Método de la capa lógica para crear un artista.
				// Antes de guardarlo en la BD, se hacen validaciones.
				public void crearPrestamo(Prestamo pres) throws Exception {
					// Validación: el artista debe tener un nombre
					if (pres.getFechaPrestamo() == null) {
						throw new Exception("el prestamo debe tener fecha de prestamo.");
					}
					// Validación: el artista debe tener un género musical
					if (pres.getFechaDevolucion() == null) {
						throw new Exception("el prestamo debe tener fecha de devolucion.");
					}
					// Si pasa todas las validaciones, recién ahí se manda a la persistencia
					controlPersis.crearPrestamo(pres);
				}
				
				public void editarPrestamo(Prestamo pres ) throws Exception {
					// Validación: el artista debe tener un nombre
					if (pres.getFechaPrestamo() == null) {
						throw new Exception("el prestamo debe tener fecha de prestamo.");
					}
					// Validación: el artista debe tener un género musical
					if (pres.getFechaDevolucion() == null) {
						throw new Exception("el prestamo debe tener fecha de devolucion.");
					}
					// Si pasa todas las validaciones, recién ahí se manda a la persistencia
					controlPersis.editarPrestamom(pres);
				}

				public Prestamo buscarPorIdPrestamo(int id) {

				    return controlPersis.buscarPorIdPRES(id);
				}
				
				
				public List<Prestamo> listaPrestamo() {

				    return controlPersis.buscarTodosLosPrestamos();
				}
				
				
				public void eliminarPrestamo(int id)throws Exception {
					
					Prestamo pres = controlPersis.buscarPorIdPRES(id);
					
					if(pres == null) {
						throw new Exception("no existe un celular con ID " + id);
					}
					controlPersis.eliminarPrestamo(id);
					
				}
}
