package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "soaleamniati")
public class SoaleAmniatiEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSoaleAmniati")
	private Short id;

	@Column(name = "Soal", length = 255, nullable = false)
	private String soal;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getSoal() {
		return soal;
	}

	public void setSoal(String soal) {
		this.soal = soal;
	}

	

}
