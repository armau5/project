package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "filezamime")
public class FileZamimeEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeFileZamime")
	private Integer id;
	
	@Column(name = "NameFileZamime", length = 256)
	private String nameFileZamime;
	
	@Column(name = "OnvanFileZamime", length = 100)
	private String onvanFileZamime;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameFileZamime() {
		return nameFileZamime;
	}

	public void setNameFileZamime(String nameFileZamime) {
		this.nameFileZamime = nameFileZamime;
	}

	public String getOnvanFileZamime() {
		return onvanFileZamime;
	}

	public void setOnvanFileZamime(String onvanFileZamime) {
		this.onvanFileZamime = onvanFileZamime;
	}

}
