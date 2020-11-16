package it.solvingteam.gestioneordini.dao.articolo;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo>{
	public double sumPrezzoArticoliByCategoria(Categoria categoria) throws Exception;
}
