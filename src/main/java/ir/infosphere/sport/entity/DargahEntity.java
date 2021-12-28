package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "dargah")
public class DargahEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeDargah")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBank")
	private BankEntity bank;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDasteBandi")
	private KodEntity dasteBandi;
	
	@Column(name = "DadeyeEzafi1", nullable = true, length = 20)
	private String dadeyeEzafi1;
	
	@Column(name = "DadeyeEzafi2", nullable = true, length = 20)
	private String dadeyeEzafi2;
	
	@Column(name = "DadeyeEzafi3", nullable = true, length = 200)
	private String dadeyeEzafi3;
	
	@Column(name = "DadeyeEzafi4", nullable = true, length = 200)
	private String dadeyeEzafi4;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BankEntity getBank() {
		return bank;
	}

	public void setBank(BankEntity bank) {
		this.bank = bank;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public String getDadeyeEzafi1() {
		return dadeyeEzafi1;
	}

	public void setDadeyeEzafi1(String dadeyeEzafi1) {
		this.dadeyeEzafi1 = dadeyeEzafi1;
	}

	public String getDadeyeEzafi2() {
		return dadeyeEzafi2;
	}

	public void setDadeyeEzafi2(String dadeyeEzafi2) {
		this.dadeyeEzafi2 = dadeyeEzafi2;
	}

	public String getDadeyeEzafi3() {
		return dadeyeEzafi3;
	}

	public void setDadeyeEzafi3(String dadeyeEzafi3) {
		this.dadeyeEzafi3 = dadeyeEzafi3;
	}

	public String getDadeyeEzafi4() {
		return dadeyeEzafi4;
	}

	public void setDadeyeEzafi4(String dadeyeEzafi4) {
		this.dadeyeEzafi4 = dadeyeEzafi4;
	}

	public KodEntity getDasteBandi() {
		return dasteBandi;
	}

	public void setDasteBandi(KodEntity dasteBandi) {
		this.dasteBandi = dasteBandi;
	}
	
}
