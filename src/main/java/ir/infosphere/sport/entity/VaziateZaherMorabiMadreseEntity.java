package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziatezahermorabimadrese")
public class VaziateZaherMorabiMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateZaherMorabiMadrese")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeLebasKafsh")
	private KodMeghdarEntity lebasKafsh;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeNamayeshHarkat")
	private KodMeghdarEntity namayeshHarkat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTarheDars")
	private KodMeghdarEntity tarheDars;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTeedieSalamat")
	private KodMeghdarEntity taeedieSalamat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKeyfiatAmozesh")
	private KodMeghdarEntity keyfiatAmozesh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getLebasKafsh() {
		return lebasKafsh;
	}

	public void setLebasKafsh(KodMeghdarEntity lebasKafsh) {
		this.lebasKafsh = lebasKafsh;
	}

	public KodMeghdarEntity getNamayeshHarkat() {
		return namayeshHarkat;
	}

	public void setNamayeshHarkat(KodMeghdarEntity namayeshHarkat) {
		this.namayeshHarkat = namayeshHarkat;
	}

	public KodMeghdarEntity getTarheDars() {
		return tarheDars;
	}

	public void setTarheDars(KodMeghdarEntity tarheDars) {
		this.tarheDars = tarheDars;
	}

	public KodMeghdarEntity getTaeedieSalamat() {
		return taeedieSalamat;
	}

	public void setTaeedieSalamat(KodMeghdarEntity taeedieSalamat) {
		this.taeedieSalamat = taeedieSalamat;
	}

	public KodMeghdarEntity getKeyfiatAmozesh() {
		return keyfiatAmozesh;
	}

	public void setKeyfiatAmozesh(KodMeghdarEntity keyfiatAmozesh) {
		this.keyfiatAmozesh = keyfiatAmozesh;
	}

}
