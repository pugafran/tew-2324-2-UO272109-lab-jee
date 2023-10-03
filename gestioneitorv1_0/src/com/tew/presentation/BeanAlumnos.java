package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.AlumnosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Alumno;

@ManagedBean
@SessionScoped
public class BeanAlumnos implements Serializable{
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el alumno concreto seleccionado
	// de la tabla o de un formulario.
	// Es necesario inicializarlo para que al entrar desde el formulario de
	// AltaForm.xhtml se puedan dejar los valores en un objeto existente.


	private Alumno[] alumnos = null;

	@ManagedProperty(value="#{alumno}")
	private BeanAlumno alumno;


	

	public void iniciaAlumno(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Obtenemos el archivo de propiedades correspondiente al idioma que
		//esta seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		alumno.setId(null);
		alumno.setIduser(bundle.getString("valorDefectoUserId"));
		alumno.setNombre(bundle.getString("valorDefectoNombre"));
		alumno.setApellidos(bundle.getString("valorDefectoApellidos"));
		alumno.setEmail(bundle.getString("valorDefectoCorreo")); 
	}

	public BeanAlumno getAlumno() { return alumno; }
	public void setAlumno(BeanAlumno alumno) {this.alumno = alumno;}



	public Alumno[] getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}

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


	public String baja(Alumno alumno) {
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

	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanAlumnos - PostConstruct");
		//Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
		alumno = (BeanAlumno)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new
						String("alumno"));
		//si no existe lo creamos e inicializamos
		if (alumno == null) {
			System.out.println("BeanAlumnos - No existia");
			alumno = new BeanAlumno();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "alumno",
					alumno);
		}
	}
	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}

}