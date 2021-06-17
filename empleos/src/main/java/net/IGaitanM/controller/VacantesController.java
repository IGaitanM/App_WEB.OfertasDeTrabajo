package net.IGaitanM.controller;
	
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	
	/**
	 * Clase que genera URLs dinámicas con la anotación PathVariable
	 * 
	 */

	@Controller
	@RequestMapping("/vacantes")
	public class VacantesController {
		
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
		 * Método que responde a peticiones http tipo get y recoge el id de la vacante
		 * @param idVacante recoge el valor del id de la url en la variable id de tipo int
		 * @return vacantes/detalle
		 */
		@GetMapping("/view/{id}")
		public String verDetalle(@PathVariable("id") int idVacante, Model model) {		
			System.out.println("IdVacante: " + idVacante);
			model.addAttribute("idVacante", idVacante);
			
			// Buscar los detalles de la vacante en la BBDD...
			
			return "vacantes/detalle";
		}

}
