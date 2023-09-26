package com.tew.infrastructure;

import impl.tew.business.SimpleServicesFactory;
import impl.tew.persistence.SimplePersistenceFactory;

import com.tew.persistence.PersistenceFactory;
import com.tew.business.AlumnosService;
import com.tew.business.ServicesFactory;
import com.tew.model.Alumno;

/**
 * Esta clase es la que realemente relaciona las interfaces de las capas 
 * con sus implementaciones finales. Si se deben hacer cambios de implementaciï¿½ï¿½n
 * en algunas de las capas habrï¿½ï¿½a que retocar esta clase.
 * 
 * En desarrollos mas sofisticados esto se especificarï¿½ï¿½n en ficheros de 
 * configuraciï¿½ï¿½n lo que permitiria al "assembler" y "deployer" ajustar los 
 * ensamblajes entre capas sin necesidad de recompilar.
 * 		Assembler: forma la aplicaciï¿½ï¿½n a base de componentes 
 * 			desarrollados externamente
 * 		Deployer: Adapta la aplicaciï¿½ï¿½n, o reconfigura la aplicaciï¿½ï¿½n en 
 * 			explotaciï¿½ï¿½n a las mï¿½ï¿½quinas/contenedores (tiers/layers)
 * 
 * Hay frameworks especializados en eso precisamente, por ejemplo Spring.
 * 
 * @author Enrique
 *
 */
public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
	
	private Alumno alumno;
	private Alumno[] alumnos;
	

	public String listado() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			// Asi le damos información a toArray para poder hacer el casting a Alumno[]
			alumnos = (Alumno [])service.getAlumnos().toArray(new Alumno[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	public String edit() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			//Recargamos el alumno en la tabla de la base de datos por si hubiera cambios.
			alumno = service.findById(alumno.getId());
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String salva() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			//Salvamos o actualizamos el alumno segun sea una operacion de alta o de edición
			if (alumno.getId() == null) {
				service.saveAlumno(alumno);
			}
			else {
				service.updateAlumno(alumno);
			}
			//Actualizamos el javabean de alumnos inyectado en la tabla
			alumnos = (Alumno [])service.getAlumnos().toArray(new Alumno[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
    
	
	public String baja() {
	    AlumnosService service;
	    try {
	        // Acceso a la implementación de la capa de negocio
	        // a través de la factoría
	        service = Factories.services.createAlumnosService();
	        // Eliminamos el alumno 
	        service.deleteAlumno(alumno.getId());
	        // Actualizamos el javabean de alumnos inyectado en la tabla
	        alumnos = (Alumno [])service.getAlumnos().toArray(new Alumno[0]);
	        return "exito";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}


}
