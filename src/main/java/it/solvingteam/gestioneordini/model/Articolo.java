package it.solvingteam.gestioneordini.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_articolo")
	private Long id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "prezzo_singolo")
	private Double prezzoSingolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordine_fk")
	private Ordine ordine;
	
	@ManyToMany
	@JoinTable(name = "articolo_categoria", joinColumns = @JoinColumn(name = "articolo_id", referencedColumnName = "id_articolo"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria"))
	private Set<Categoria> categorie = new HashSet<>();
	
	public Articolo() {}
	
	public Articolo(String descrizione, Double prezzoSingolo, Ordine ordine) {
		this.descrizione = descrizione;
		this.prezzoSingolo = prezzoSingolo;
		this.ordine = ordine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public void setPrezzoSingolo(Double prezzoSingoloDouble) {
		this.prezzoSingolo = prezzoSingoloDouble;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Articolo [id: " + id + ", descrizione: " + descrizione + ", prezzoSingolo: " + prezzoSingolo + ", ordine: "
				+ ordine + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (prezzoSingolo == null) {
			if (other.prezzoSingolo != null)
				return false;
		} else if (!prezzoSingolo.equals(other.prezzoSingolo))
			return false;
		return true;
	}

	
}
