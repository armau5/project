package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "faktor")
public class FaktorEntity extends BaseEntity<Integer> {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeFaktor")
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeHesab", nullable = false)
	private HesabEntity hesab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks", nullable = true)
	private AksEntity aks;

	@Column(name = "Tarikh", nullable = false)
	private Date tarikh;
	
	@Column(name = "ShomareyeFaktor", nullable = true)
	private Integer shomareyeFaktor;
	
	@Column(name = "ShomarePeygiri", nullable = false)
	private Long peygiri;
	
	@Column(name = "Tozih", nullable = true, length = 255)
	private String tozih;
	
	@ManyToOne
	@JoinColumn(name = "NoeFaktor", nullable = false)
	private KodEntity noeFaktor;
	
	@ManyToOne
	@JoinColumn(name = "DalileFaktor", nullable = false)
	private KodEntity dalileFaktor;	
	

	public Integer getShomareyeFaktor() {
		return shomareyeFaktor;
	}

	public void setShomareyeFaktor(Integer shomareyeFaktor) {
		this.shomareyeFaktor = shomareyeFaktor;
	}

	public Long getPeygiri() {
		return peygiri;
	}

	public void setPeygiri(Long peygiri) {
		this.peygiri = peygiri;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Date getTarikh() {
		return tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}

	public String getTozih() {
		return tozih;
	}

	public void setTozih(String tozih) {
		this.tozih = tozih;
	}

	public KodEntity getNoeFaktor() {
		return noeFaktor;
	}

	public void setNoeFaktor(KodEntity noeFaktor) {
		this.noeFaktor = noeFaktor;
	}

	public HesabEntity getHesab() {
		return hesab;
	}

	public void setHesab(HesabEntity hesab) {
		this.hesab = hesab;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public KodEntity getDalileFaktor() {
		return dalileFaktor;
	}

	public void setDalileFaktor(KodEntity dalileFaktor) {
		this.dalileFaktor = dalileFaktor;
	}

}
