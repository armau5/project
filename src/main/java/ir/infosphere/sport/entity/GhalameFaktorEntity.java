package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ghalamefaktor")
public class GhalameFaktorEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGhalameFaktor")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeFaktor", nullable = true)
	private FaktorEntity faktor;
	
	@Column(name = "RadifeGhalam", nullable = false)
	private Integer radifeGhalam;

	@Column(name = "NamKalaYaKhadamat", nullable = false, length = 255)
	private String namKalaYaKhadamat;
	
	@Column(name = "GheymatVahed", nullable = false)
	private Long gheymatVahed;
	
	@Column(name = "TedadYaMeghdar", nullable = false)
	private Integer tedadYaMeghdar;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FaktorEntity getFaktor() {
		return faktor;
	}

	public void setFaktor(FaktorEntity faktor) {
		this.faktor = faktor;
	}

	public Integer getRadifeGhalam() {
		return radifeGhalam;
	}

	public void setRadifeGhalam(Integer radifeGhalam) {
		this.radifeGhalam = radifeGhalam;
	}

	public String getNamKalaYaKhadamat() {
		return namKalaYaKhadamat;
	}

	public void setNamKalaYaKhadamat(String namKalaYaKhadamat) {
		this.namKalaYaKhadamat = namKalaYaKhadamat;
	}

	public Long getGheymatVahed() {
		return gheymatVahed;
	}

	public void setGheymatVahed(Long gheymatVahed) {
		this.gheymatVahed = gheymatVahed;
	}

	public Integer getTedadYaMeghdar() {
		return tedadYaMeghdar;
	}

	public void setTedadYaMeghdar(Integer tedadYaMeghdar) {
		this.tedadYaMeghdar = tedadYaMeghdar;
	}


}
