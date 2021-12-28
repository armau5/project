package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziatehoghoghivaedarimadrese")
public class VaziateHoghoghiVaEdariMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateHoghoghiVaEdari")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeSabteEtelaateSahih")
	private KodMeghdarEntity sabteEtelaateSahih;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKarteOzviat")
	private KodMeghdarEntity karteOzviat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeDaftarEdari")
	private KodMeghdarEntity daftarEdari;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getSabteEtelaateSahih() {
		return sabteEtelaateSahih;
	}

	public void setSabteEtelaateSahih(KodMeghdarEntity sabteEtelaateSahih) {
		this.sabteEtelaateSahih = sabteEtelaateSahih;
	}

	public KodMeghdarEntity getKarteOzviat() {
		return karteOzviat;
	}

	public void setKarteOzviat(KodMeghdarEntity karteOzviat) {
		this.karteOzviat = karteOzviat;
	}

	public KodMeghdarEntity getDaftarEdari() {
		return daftarEdari;
	}

	public void setDaftarEdari(KodMeghdarEntity daftarEdari) {
		this.daftarEdari = daftarEdari;
	}
}
