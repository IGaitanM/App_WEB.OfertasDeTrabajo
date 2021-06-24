package net.IGaitanM.controller;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.IGaitanM.model.Vacante;
import net.IGaitanM.service.IVacantesService;
	
	/**
	 * Clase que genera URLs dinámicas con la anotación PathVariable
	 * 
	 */
	@Controller
	@RequestMapping("/vacantes")
	public class VacantesController {
		
		@Autowired                                 //Instancia de una clase de servicio
		private IVacantesService serviceVacantes; 
		
		/**
		 * Método para crear vacantes, renderiza el formulario de formVacante.html
		 * @param 
		 * @return vacantes/formVacante
		 */
		@GetMapping("/create")
		public String crear() {
			
			return "vacantes/formVacante";
		}
		
		/**
		 * Método para guardar vacantes
		 * @param 
		 * @return vacantes/formVacante
		 */
		
		@PostMapping("/save")
		public String guardar(Vacante vacante) {
			System.out.println("Vacante " + vacante);
			return "vacantes/listVacantes"; 
		}
		
		/*
		 @PostMapping("/save")
		public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, 
				@RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado, 
				@RequestParam("salario") double salario, @RequestParam("detalles") String detalles) {
			System.out.println("Nombre Vacante: " + nombre);
			System.out.println("Descripcion: " + descripcion);
			System.out.println("Estatus: " + estatus);
			System.out.println("Fecha Publicación: " + fecha);
			System.out.println("Destacado: " + destacado);
			System.out.println("Salario Ofrecido: " + salario);
			System.out.println("detalles: " + detalles);
			return "vacantes/listVacantes"; 
		} 
		*/
		 
		
		/**
		 * Método que responde a peticiones http tipo get y elimina una vacante por su id
		 * @param idVacante recoge el valor del id de la url en la variable id de tipo int
		 * @return mensaje
		 */
		@GetMapping("/delete")
		public String eliminar(@RequestParam("id") int idVacante, Model model) {
			System.out.println("Borrando vacante con id: " + idVacante);
			model.addAttribute("id", idVacante);
			return "mensaje";
		}
		
		/**
		 * Recibe el id desde la url de tabla.htm y lo guada en la variable id
		 * @param idVacante recoge el valor del id de la url en la variable id de tipo int
		 * @return vacantes/detalle
		 */
		@GetMapping("/view/{id}")
		public String verDetalle(@PathVariable("id") int idVacante, Model model) {	
			Vacante vacante = serviceVacantes.buscarPorId(idVacante);
			model.addAttribute("vacante", vacante);
			
			// Buscar los detalles de la vacante en la BBDD...
			
			return "detalle";
		}
		
		/**
		 * Método para controlar los errores en las fechas que vienen de formularios.
		 * Declara un objeto de clase SimpleDateFormat para indicarle el formato con el que manejaremos las fechas.
		 * Llama al método registerCustomEditor.
		 * @param WebDataBinder
		 */
		@InitBinder		//permite crear métodos para configurar el Data Binding										
		public void initBinder(WebDataBinder webDataBinder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
		

}
