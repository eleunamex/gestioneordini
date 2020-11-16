package it.solvingteam.gestioneordini.service.articolo;

import java.util.List;

import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public interface ArticoloService {

	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;
	
	public void aggiungiCategoria(Articolo articoloEsistente, Categoria categoriaInstance) throws Exception;
	
	public void aggiungiOrdine(Articolo articoloInstance, Ordine ordineInstance) throws Exception;
	
	public double sommaPrezzoArticoliByCategoria(Categoria categoria) throws Exception;
	
	// per injection
	public void setArticoloDAO(ArticoloDAO articoloDAO);
	
}
