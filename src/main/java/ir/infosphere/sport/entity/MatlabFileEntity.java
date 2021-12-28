package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "matlabfile")
public class MatlabFileEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMatlabFile")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMatlab", nullable = false)
	private MatlabEntity matlab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeFileZamime", nullable = false)
	private FileZamimeEntity file;

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

	public FileZamimeEntity getFile() {
		return file;
	}

	public void setFile(FileZamimeEntity file) {
		this.file = file;
	}

}
