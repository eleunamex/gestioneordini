package it.solvingteam.gestioneordini.service.categoria;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.dao.categoria.CategoriaDAO;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			categoriaDAO.update(categoriaInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			// controllo
			List<Categoria> lista = categoriaDAO.list();
			for (Categoria c : lista) {
				if (c.equals(categoriaInstance)) {
					throw new Exception("Categoria già presente");
				}
			}
			categoriaDAO.insert(categoriaInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			Categoria categoriaEsistente = categoriaDAO.get(categoriaInstance.getId());
			// controllo
			if (categoriaEsistente.getArticoli().isEmpty()) {
				categoriaDAO.delete(categoriaInstance);
				entityManager.getTransaction().commit();
				System.out.println("eliminata");
			} else {
				throw new SQLIntegrityConstraintViolationException(
						"Non puoi eliminare una categoria se ha degli articoli");
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Categoria> cercaTutteTramiteOrdine(Ordine ordine) throws Exception {
		try {
			categoriaDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			return categoriaDAO.getAllByOrdine(ordine);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
