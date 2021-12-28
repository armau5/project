package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sansepardakhtservice")
public class SansePardakhtServiceEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSansePardakhtService")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakhtService", nullable = false)
	private OzviyyatEntity ozviyyat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeSans", nullable = false)
	private SansEntity shenaseyeSans;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzviyyatEntity getOzviyyat() {
		return ozviyyat;
	}

	public void setOzviyyat(OzviyyatEntity ozviyyat) {
		this.ozviyyat = ozviyyat;
	}

	public SansEntity getShenaseyeSans() {
		return shenaseyeSans;
	}

	public void setShenaseyeSans(SansEntity shenaseyeSans) {
		this.shenaseyeSans = shenaseyeSans;
	}

}
