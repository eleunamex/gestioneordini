package it.solvingteam.gestioneordini;

import it.solvingteam.gestioneordini.service.MyServiceFactory;
import it.solvingteam.gestioneordini.service.articolo.ArticoloService;
import it.solvingteam.gestioneordini.service.categoria.CategoriaService;
import it.solvingteam.gestioneordini.service.ordine.OrdineService;

import java.util.HashSet;
import java.util.Set;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaService = MyServiceFactory.getCategoriaServiceInstance();
		OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();

		try {

			Articolo a = new Articolo();
			Categoria c = new Categoria();
			Categoria c2 = new Categoria();
			Ordine o = new Ordine();

//			c2.setDescrizione("Cibo");
//
//			categoriaService.inserisciNuovo(c2);

//			for(Articolo ar : ordineService.caricaSingoloElemento(3l).getArticoli()) {
//				System.out.println(ar);
//			}
			
			a=articoloService.caricaSingoloElemento(5l);
			o=ordineService.caricaSingoloElemento(3l);
			
			articoloService.aggiungiOrdine(a, o);

//			Ordine o = ordineService.caricaSingoloElemento(1l);
//			a=articoloService.caricaSingoloElemento(1l);
//			articoloService.aggiungiOrdine(a, o);

//			a=articoloService.caricaSingoloElemento(4l);
//			c=categoriaService.caricaSingoloElemento(1l);
//			articoloService.aggiungiCategoria(a, c);

//			a=articoloService.caricaSingoloElemento(1l);
//			a.setDescrizione("Galaxy S12");
//			articoloService.aggiorna(a);

//			c.setId(1l);
//			for(Ordine ordine : ordineService.cercaTuttiTramiteCategoria(c)) {
//				System.out.println(ordine);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}
}
