package net.IGaitanM.service;

import java.util.List;
import net.IGaitanM.model.Vacante;

/**
 * Método para buscar vacantes.
 * 
 */

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	
}

