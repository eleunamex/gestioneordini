package it.solvingteam.gestioneordini.dao.ordine;

import java.util.List;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	public List<Ordine> getAllByCategoria(Categoria categoria) throws Exception;
}
