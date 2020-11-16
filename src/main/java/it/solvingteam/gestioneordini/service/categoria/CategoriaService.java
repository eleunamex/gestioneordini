package it.solvingteam.gestioneordini.service.categoria;

import java.util.List;

import it.solvingteam.gestioneordini.dao.categoria.CategoriaDAO;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public interface CategoriaService {
	
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;
	
	public List<Categoria> cercaTutteTramiteOrdine(Ordine ordine) throws Exception; 

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}
