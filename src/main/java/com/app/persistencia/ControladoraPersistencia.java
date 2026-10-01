package com.app.persistencia;

import java.util.List;

import com.app.logica.Libro;
import com.app.logica.Prestamo;
import com.app.logica.Usuario;

public class ControladoraPersistencia {


	// Artista: Creamos una instancia del controlador que sabe cómo hablar con la
		// base de datos
		LibroJpaController LibroJpa = new LibroJpaController();
		UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
		PrestamoJpaController PrestamoJpa = new PrestamoJpaController();
		
		// Este método es el que se usa desde otras capas para crear un ARTISTA
		public void crearLibro(Libro lib) {
			// Delegamos la tarea al controlador especializado
			LibroJpa.crearLib(lib);
		}

		public Libro buscarPorIdLIB(int id) {
			// TODO Auto-generated method stub
			return LibroJpa.buscarPorIdLIBRO(id);
		}
		
		
		public List<Libro> buscarTodosLosLibros(){
			
			return LibroJpa.buscarTodosLibros();
		}
		
		public void eliminarLibro(int id) {
			
			LibroJpa.destruirLibro(id);
			
		}
		
		public void editarLibro(Libro celu) {
			LibroJpa.editarLibro(celu);
		}
		
		/*------------------------------------------*/
		/*USUARIO*/
		
		// Este método es el que se usa desde otras capas para crear un ARTISTA
				public void crearUsuario(Usuario usu) {
					// Delegamos la tarea al controlador especializado
					UsuarioJpa.crearUsu(usu);
				}

				public Usuario buscarPorIdUSU(int id) {
					// TODO Auto-generated method stub
					return UsuarioJpa.buscarPorIdUSUARIO(id);
				}
				
				
				public List<Usuario> buscarTodosLosUsuarios(){
					
					return UsuarioJpa.buscarTodosUsuarios();
				}
				
				public void eliminarUsuario(int id) {
					
					UsuarioJpa.destruirUsuario(id);
					
				}
				
				public void editarUsuario(Usuario usu) {
					UsuarioJpa.editarUsuario(usu);
				}
				
				/*------------------------------------------*/
				/*PRESTAMO*/
				
				// Este método es el que se usa desde otras capas para crear un ARTISTA
				public void crearPrestamo(Prestamo pres) {
					// Delegamos la tarea al controlador especializado
					PrestamoJpa.crearPres(pres);
				}

				public Prestamo buscarPorIdPRES(int id) {
					// TODO Auto-generated method stub
					return PrestamoJpa.buscarPorIdPRESTAMO(id);
				}
				
				
				public List<Prestamo> buscarTodosLosPrestamos(){
					
					return PrestamoJpa.buscarTodosPrestamos();
				}
				
				public void eliminarPrestamo(int id) {
					
					PrestamoJpa.destruirPrestamo(id);
					
				}
				
				public void editarPrestamom(Prestamo pres) {
					PrestamoJpa.editarPrestamo(pres);
				}	
				
}
