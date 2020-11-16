package it.solvingteam.gestioneordini.service.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDAO;

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO = articoloDAO;
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			articoloDAO.setEntityManager(entityManager);
			// controllo
			Articolo a = articoloDAO.get(articoloInstance.getId());
			if (a.getOrdine() != null) {
				throw new Exception("Non puoi modificare un articolo ordinato");
			}

			articoloDAO.update(articoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	
	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			articoloDAO.setEntityManager(entityManager);

			// controllo
			List<Articolo> lista = articoloDAO.list();
			for (Articolo c : lista) {
				if (c.equals(articoloInstance)) {
					throw new Exception("Articolo già presente");
				}
			}

			articoloDAO.insert(articoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			articoloDAO.setEntityManager(entityManager);
			// controllo
			Articolo a = articoloDAO.get(articoloInstance.getId());
			if (a.getOrdine() != null) {
				throw new Exception("Non puoi eliminare un articolo ordinato");
			}

			articoloDAO.delete(articoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void aggiungiCategoria(Articolo articoloEsistente, Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoloDAO.setEntityManager(entityManager);

			articoloEsistente = entityManager.merge(articoloEsistente);
			categoriaInstance = entityManager.merge(categoriaInstance);
			// controllo
			for (Categoria c : articoloEsistente.getCategorie()) {
				if (c.equals(categoriaInstance)) {
					throw new Exception("L'articolo appartiene già a questa categoria ");
				}
			}

			articoloEsistente.getCategorie().add(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void aggiungiOrdine(Articolo articoloInstance, Ordine ordineInstance) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoloDAO.setEntityManager(entityManager);

			articoloInstance = entityManager.merge(articoloInstance);
			ordineInstance = entityManager.merge(ordineInstance);
			// controllo
			for (Articolo art : ordineInstance.getArticoli()) {
				if (art.equals(articoloInstance)) {
					throw new Exception("Articolo già incluso");
				}
			}

			articoloInstance.setOrdine(ordineInstance);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public double sommaPrezzoArticoliByCategoria(Categoria categoria) throws Exception {
		try {
			articoloDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			return articoloDAO.sumPrezzoArticoliByCategoria(categoria);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
