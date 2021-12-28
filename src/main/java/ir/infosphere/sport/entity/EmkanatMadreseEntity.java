package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "emkanatmadrese")
public class EmkanatMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeEmkanatMadrese")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTirDarvazeh")
	private KodMeghdarEntity tirDarvazeh;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeToreMonaseb")
	private KodMeghdarEntity toreMonaseb;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeParchamKorner")
	private KodMeghdarEntity parchamKorner;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTopeEstandard")
	private KodMeghdarEntity topeStandard;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeLavazemKomaki")
	private KodMeghdarEntity lavazemKomaki;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeDarvazehMoteharek")
	private KodMeghdarEntity darvazehMoshtarak;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKover")
	private KodMeghdarEntity kover;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeAyabZahab")
	private KodMeghdarEntity ayabZahab;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeBimeHavades")
	private KodMeghdarEntity bimeHavades;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeLebaseShakhsi")
	private KodMeghdarEntity lebasShakhsi;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeAlameZamin")
	private KodMeghdarEntity alameZamin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getTirDarvazeh() {
		return tirDarvazeh;
	}

	public void setTirDarvazeh(KodMeghdarEntity tirDarvazeh) {
		this.tirDarvazeh = tirDarvazeh;
	}

	public KodMeghdarEntity getToreMonaseb() {
		return toreMonaseb;
	}

	public void setToreMonaseb(KodMeghdarEntity toreMonaseb) {
		this.toreMonaseb = toreMonaseb;
	}

	public KodMeghdarEntity getParchamKorner() {
		return parchamKorner;
	}

	public void setParchamKorner(KodMeghdarEntity parchamKorner) {
		this.parchamKorner = parchamKorner;
	}

	public KodMeghdarEntity getTopeStandard() {
		return topeStandard;
	}

	public void setTopeStandard(KodMeghdarEntity topeStandard) {
		this.topeStandard = topeStandard;
	}

	public KodMeghdarEntity getLavazemKomaki() {
		return lavazemKomaki;
	}

	public void setLavazemKomaki(KodMeghdarEntity lavazemKomaki) {
		this.lavazemKomaki = lavazemKomaki;
	}

	public KodMeghdarEntity getDarvazehMoshtarak() {
		return darvazehMoshtarak;
	}

	public void setDarvazehMoshtarak(KodMeghdarEntity darvazehMoshtarak) {
		this.darvazehMoshtarak = darvazehMoshtarak;
	}

	public KodMeghdarEntity getKover() {
		return kover;
	}

	public void setKover(KodMeghdarEntity kover) {
		this.kover = kover;
	}

	public KodMeghdarEntity getAyabZahab() {
		return ayabZahab;
	}

	public void setAyabZahab(KodMeghdarEntity ayabZahab) {
		this.ayabZahab = ayabZahab;
	}

	public KodMeghdarEntity getBimeHavades() {
		return bimeHavades;
	}

	public void setBimeHavades(KodMeghdarEntity bimeHavades) {
		this.bimeHavades = bimeHavades;
	}

	public KodMeghdarEntity getLebasShakhsi() {
		return lebasShakhsi;
	}

	public void setLebasShakhsi(KodMeghdarEntity lebasShakhsi) {
		this.lebasShakhsi = lebasShakhsi;
	}

	public KodMeghdarEntity getAlameZamin() {
		return alameZamin;
	}

	public void setAlameZamin(KodMeghdarEntity alameZamin) {
		this.alameZamin = alameZamin;
	}
	
	
}
