package net.IGaitanM.controller;
	
	import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		 * Método para mostrar index, renderiza las vacantes en la lista
		 * @param model
		 * @return vacantes/listVacantes
		 */
		@GetMapping("/index")
		public String mostrarIndex(Model model) {
			List<Vacante> lista = serviceVacantes.buscarTodas();	// Obttiene todas las vacantes (Se recuperan de la clase de servicio)
			model.addAttribute("vacantes", lista);					// Se agrega el listado de vacantes al modelo.
			// Renderiza las vacantes en la lista (integra el archivo template-empleos/listvACANTES.HTML)
			
			// Agrega la opción "Vacantes" al menú, configurando la URL "vacantes/index"
			
			return "vacantes/listVacantes";
		}
		
		/**
		 * Método para crear vacantes. 
		 * @param vacante (Vinculado con el objeto vacante del formulario)
		 * @return vacantes/formVacante
		 */
		@GetMapping("/create")
		public String crear(Vacante vacante) {
			return "vacantes/formVacante";
		}
		
		/**
		 * Método para guardar vacantes. Cuando llega el objeto vacante al controlador se agrega automaticamente a nuestra lista.
		 * Si hay un error lo mnuestra en consola utilizando un blucle for y retorna a la pantalla del formulario.
		 * @param 
		 * @return vacantes/listVacantes
		 */
		@PostMapping("/save")
		public String guardar(Vacante vacante, BindingResult result) {
			if (result.hasErrors()) {
				for (ObjectError error: result.getAllErrors()){                             //Para ver los errores en la consola
					System.out.println("Ocurrio un error: " + error.getDefaultMessage());
					}
				return "vacantes/formVacante"; 
			}
			serviceVacantes.guardar(vacante);
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

		@InitBinder
		public void initBinder(WebDataBinder webDataBinder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
}
