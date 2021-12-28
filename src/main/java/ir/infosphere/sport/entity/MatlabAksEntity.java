package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "matlabaks")
public class MatlabAksEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMatlabAks")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMatlab", nullable = false)
	private MatlabEntity matlab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks", nullable = false)
	private AksEntity aks;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MatlabEntity getMatlab() {
		return matlab;
	}

	public void setMatlab(MatlabEntity matlab) {
		this.matlab = matlab;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

}
