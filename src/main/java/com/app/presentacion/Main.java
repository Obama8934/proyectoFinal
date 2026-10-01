package com.app.presentacion;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.app.logica.ControladoraLogica;
import com.app.logica.Libro;
import com.app.logica.Prestamo;
import com.app.logica.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner entrada = new Scanner(System.in);
		
		System.out.println("ESTOY EJECUTANDO MI MAIN");
		
	
		
		ControladoraLogica conLog = new ControladoraLogica();
		try {
		// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES
			// INSTANCIAR DOS ARTISTAS 
			  Libro libro1 = new Libro("El Principito","Antoine de Saint-Exupéry",96,8500);
			  Libro libro2 = new Libro("El BEN 10","MIGUEL",100,9000);
			  
			   Usuario usuario1 = new Usuario("Juan","Perez","juan@gmail.com");
			   Usuario usuario2 = new Usuario("ale","marca","ale1234@gmail.com");
			   
			   Date fechaPrestamo = new Date();

		       Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7L * 24 * 60 * 60 * 1000));

		      Prestamo prestamo1 = new Prestamo(fechaPrestamo,fechaDevolucion,libro1,usuario1);
		      Prestamo prestamo2 = new Prestamo(fechaPrestamo,fechaDevolucion,libro2,usuario2);

			   
			   
			  conLog.crearLibro(libro1);
			  conLog.crearLibro(libro2);
			  conLog.crearUsuario(usuario1);
			  conLog.crearUsuario(usuario2);
			  conLog.crearPrestamo(prestamo1);
			  conLog.crearPrestamo(prestamo2);
		
		System.out.println("datos creados");
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}

	/* buscar por id */
//	Celular celularEncontrado = conLog.buscarPorId(1);
//	System.out.println(celularEncontrado);
//	
//	/* mostrar todos los celulares */
//	List<Libro> listaLibros = conLog.listaCelulares();
//	for (Libro celular : listaLibros) {
//		System.out.println(celular);
//		System.out.println("----------");
//	}
	
	/* eliminar celular*/
//	System.out.println("-------------(((_7-_///");
//	try {
//		
//		System.out.println("ingrese dato a eliminar segun ID: ");
//		conLog.eliminarLibro(entrada.nextInt());
//	} catch (Exception e) {
//		System.out.println("error al eliminar: " + e.getMessage());
//	}
//	
//	/* eidtando un dato*/
//	System.out.println("elija un celular a editar segun la lista anterior");
//	int idEditar = entrada.nextInt();
//	Libro libroEditable = conLog.buscarPorIdLibro(idEditar);
//
//			if(libroEditable == null) {
//				System.out.println("no existe un celular con id" + idEditar);
//				System.out.println("finalizando");
//			}
//			
//		
//			System.out.println("que desea editar?");
//			System.out.println("1. titulo");
//			System.out.println("2. modelo");
//			System.out.println("3. precio");
//			
//			int opcion = entrada.nextInt();
//			entrada.nextLine();
//			
//			switch (opcion) {
//			case 1:
//				System.out.println("nuevo titulo:");
//				libroEditable.setTitulo(entrada.nextLine());
//				break;
//			case 2:
//				System.out.println("nuevo autor:");
//				libroEditable.setAutor(entrada.nextLine());
//				break;
//			case 3:
//				System.out.println("nuevo precio:");
//				libroEditable.setCosto(entrada.nextDouble());;
//				break;
//			default:
//				System.out.println("opcion invalida");
//				break;
//			}
//			try {
//				conLog.editarCelular(libroEditable);
//			} catch (Exception e) {
//				System.err.println("error: " + e.getMessage());
//				System.err.println("programa finalizado");
//			}
	}

}
