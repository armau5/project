package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakhtbelit")
public class PardakhtBelitEntity extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakhtBelit")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeVaziateSandali", nullable = false)
	private VaziateSandaliEntity vaziateSandali;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public VaziateSandaliEntity getVaziateSandali() {
		return vaziateSandali;
	}

	public void setVaziateSandali(VaziateSandaliEntity vaziateSandali) {
		this.vaziateSandali = vaziateSandali;
	}
}
