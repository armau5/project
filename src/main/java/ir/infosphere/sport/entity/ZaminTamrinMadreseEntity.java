package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "zaminetamrinmadrese")
public class ZaminTamrinMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeZamineTamrin")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKeyfiateZamin")
	private KodMeghdarEntity keyfiateZamin;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeNoreZamin")
	private KodMeghdarEntity noreZamin;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeAbadeZamin")
	private KodMeghdarEntity abadeZamin;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeVazehBodan")
	private KodMeghdarEntity vazehBodan;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeAshyaeKhatarnak")
	private KodMeghdarEntity ashyayeKhatarnak;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeHefaz")
	private KodMeghdarEntity hefaz;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getKeyfiateZamin() {
		return keyfiateZamin;
	}

	public void setKeyfiateZamin(KodMeghdarEntity keyfiateZamin) {
		this.keyfiateZamin = keyfiateZamin;
	}

	public KodMeghdarEntity getNoreZamin() {
		return noreZamin;
	}

	public void setNoreZamin(KodMeghdarEntity noreZamin) {
		this.noreZamin = noreZamin;
	}

	public KodMeghdarEntity getAbadeZamin() {
		return abadeZamin;
	}

	public void setAbadeZamin(KodMeghdarEntity abadeZamin) {
		this.abadeZamin = abadeZamin;
	}

	public KodMeghdarEntity getVazehBodan() {
		return vazehBodan;
	}

	public void setVazehBodan(KodMeghdarEntity vazehBodan) {
		this.vazehBodan = vazehBodan;
	}

	public KodMeghdarEntity getAshyayeKhatarnak() {
		return ashyayeKhatarnak;
	}

	public void setAshyayeKhatarnak(KodMeghdarEntity ashyayeKhatarnak) {
		this.ashyayeKhatarnak = ashyayeKhatarnak;
	}

	public KodMeghdarEntity getHefaz() {
		return hefaz;
	}

	public void setHefaz(KodMeghdarEntity hefaz) {
		this.hefaz = hefaz;
	}
	
	

}
