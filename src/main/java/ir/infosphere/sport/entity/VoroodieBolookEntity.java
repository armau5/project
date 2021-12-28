package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "voroodiebolook")
public class VoroodieBolookEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVoroodieBolook")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBolook", nullable = false)
	private BolookEntity bolook;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeVoroodi", nullable = false)
	private VoroodiEntity voroodi;

	@Column(name = "PishFarz", nullable = false)
	private Boolean pishFarz;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BolookEntity getBolook() {
		return bolook;
	}

	public void setBolook(BolookEntity bolook) {
		this.bolook = bolook;
	}

	public VoroodiEntity getVoroodi() {
		return voroodi;
	}

	public void setVoroodi(VoroodiEntity voroodi) {
		this.voroodi = voroodi;
	}

	public Boolean getPishFarz() {
		return pishFarz;
	}

	public void setPishFarz(Boolean pishFarz) {
		this.pishFarz = pishFarz;
	}



}
