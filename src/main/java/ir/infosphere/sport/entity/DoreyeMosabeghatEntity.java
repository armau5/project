package ir.infosphere.sport.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "doreyemosabeghat")
public class DoreyeMosabeghatEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeDoreyeMosabeghat")
	private Integer id;
	
	@Column(name = "nameDore", nullable = false, length = 100)
	private String nameDore;

	@Column(name = "TarikhSorou", nullable = false)
	private Date tarikhSorou;
	
	@Column(name = "TarikhPayan", nullable = false)
	private Date tarikhPayan;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNameDore() {
		return nameDore;
	}

	public void setNameDore(String nameDore) {
		this.nameDore = nameDore;
	}

	public Date getTarikhSorou() {
		return tarikhSorou;
	}

	public void setTarikhSorou(Date tarikhSorou) {
		this.tarikhSorou = tarikhSorou;
	}

	public Date getTarikhPayan() {
		return tarikhPayan;
	}

	public void setTarikhPayan(Date tarikhPayan) {
		this.tarikhPayan = tarikhPayan;
	}
	
	


}
