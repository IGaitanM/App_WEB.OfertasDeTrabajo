package net.IGaitanM.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.IGaitanM.model.Vacante;

/**
 * Clase que implementa la interfaz IVacantesService, crea las vacantes y las añade a la variable lista
 * @param lista , variable de tipo list que recoge todas las vacantes 
 */

@Service
public class VacantesServiceImpl implements IVacantesService {
	
	private List<Vacante> lista = null;
	
	public VacantesServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		try {
			// Creamos la oferta de Trabajo 1.
						Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil"); // Titulo de la vacante
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacante1.setFecha(sdf.parse("17-06-2021"));
			vacante1.setSalario(27200.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
									
			// Creamos la oferta de Trabajo 2.
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Desarrollador Junior");
			vacante2.setDescripcion("Multinacional española busca Desarrollador sin experiencia con ganas de aprender .");
			vacante2.setFecha(sdf.parse("18-06-2021"));
			vacante2.setSalario(21000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
						
			// Creamos la oferta de Trabajo 3.
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Electricista");
			vacante3.setDescripcion("Empresa internacional solicita electricista para mantenimiento de la instalación eléctrica.");
			vacante3.setFecha(sdf.parse("24-06-2021"));
			vacante3.setSalario(16500.0);
			vacante3.setDestacado(0);
						
			// Creamos la oferta de Trabajo 4.
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Solicitamos Diseñador Gráfico para diseñar publicidad de la empresa.");
			vacante4.setFecha(sdf.parse("25-06-2021"));
			vacante4.setSalario(15500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa3.png");
						
			/**
			 * Agregamos los 4 objetos de tipo Vacante a la lista ...
			*/
			lista.add(vacante1);			
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	/**
	 * Método que devuelve la lista de todas las vacantes
	*/
	public List<Vacante> buscarTodas() {
		return lista;
	}

	/**
	 * Método que recorre la lista de vacantes y compara el id con el pasado por parámetro
	*/
	public Vacante buscarPorId(Integer idVacante) {
		for (Vacante v : lista) {
			if (v.getId()==idVacante) {
				return v;
			}
		}
		
		return null;
	}

}

