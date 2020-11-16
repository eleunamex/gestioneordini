package it.solvingteam.gestioneordini.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordine")
	private Long id;
	
	@Column(name = "nome_destinatario")
	private String nomeDestinatario;
	
	@Column(name = "indirizzo_spedizione")
	private String indirizzoSpedizione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordine")
	private Set<Articolo> articoli= new HashSet<>();
	
	public Ordine() {}
	
	public Ordine(String nomeDestinatario, String indirizzoSpedizione) {
		this.nomeDestinatario = nomeDestinatario;
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	@Override
	public String toString() {
		return "Ordine [id: " + id + ", nomeDestinatario: " + nomeDestinatario + ", indirizzoSpedizione: "
				+ indirizzoSpedizione + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indirizzoSpedizione == null) ? 0 : indirizzoSpedizione.hashCode());
		result = prime * result + ((nomeDestinatario == null) ? 0 : nomeDestinatario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		if (indirizzoSpedizione == null) {
			if (other.indirizzoSpedizione != null)
				return false;
		} else if (!indirizzoSpedizione.equals(other.indirizzoSpedizione))
			return false;
		if (nomeDestinatario == null) {
			if (other.nomeDestinatario != null)
				return false;
		} else if (!nomeDestinatario.equals(other.nomeDestinatario))
			return false;
		return true;
	}
	
	
}
