package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "takhfifesharj")
public class TakhfifeSharjEntity extends BaseTakhfifEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTakhfifeSharj")
	private Integer id;
	
	@Column(name = "TarikheEngheza")
	private Date tarikheEngheza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTarikheEngheza() {
		return tarikheEngheza;
	}

	public void setTarikheEngheza(Date tarikheEngheza) {
		this.tarikheEngheza = tarikheEngheza;
	}
}
