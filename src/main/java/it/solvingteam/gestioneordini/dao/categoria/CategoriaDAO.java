package it.solvingteam.gestioneordini.dao.categoria;

import java.util.List;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	public List<Categoria> getAllByOrdine(Ordine ordine) throws Exception;
}
