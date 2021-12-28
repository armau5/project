package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "bank")
public class BankEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBank")
	private Short id;
	
	@Column(name = "NameBank")
	private String nameBank;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeLogo")
	private AksEntity logo;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public AksEntity getLogo() {
		return logo;
	}

	public void setLogo(AksEntity logo) {
		this.logo = logo;
	}
	
}
