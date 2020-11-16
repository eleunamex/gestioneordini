package it.solvingteam.gestioneordini.dao.articolo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo",Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Articolo o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Articolo o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));
	}
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public double sumPrezzoArticoliByCategoria(Categoria categoria) throws Exception {
		if (categoria == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Articolo> query =entityManager.createQuery("select a from Articolo a INNER JOIN a.categorie c "
				+ " where c.id = ?1 ", Articolo.class);
		List<Articolo> lista = new ArrayList<>();
		lista=query.setParameter(1, categoria.getId()).getResultList();
		Double prezzo=0d;
		for(Articolo a : lista) {
			prezzo+=a.getPrezzoSingolo();
		}
		return prezzo;
	}

}
