package it.solvingteam.gestioneordini.service.ordine;

import java.util.List;

import it.solvingteam.gestioneordini.dao.ordine.OrdineDAO;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;
	
	public List<Ordine> cercaTuttiTramiteCategoria(Categoria categoria) throws Exception;
	
	public void setOrdineDAO(OrdineDAO ordineDAO);
}
