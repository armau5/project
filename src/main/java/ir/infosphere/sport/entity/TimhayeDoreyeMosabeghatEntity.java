package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "timhayedoreyemosabeghat")
public class TimhayeDoreyeMosabeghatEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTimhayeDoreyeMosabeghat")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTim", nullable = false)
	private TimEntity tim;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeDoreyeMosabeghat", nullable = false)
	private DoreyeMosabeghatEntity doreyeMosabeghat;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TimEntity getTim() {
		return tim;
	}

	public void setTim(TimEntity tim) {
		this.tim = tim;
	}

	public DoreyeMosabeghatEntity getDoreyeMosabeghat() {
		return doreyeMosabeghat;
	}

	public void setDoreyeMosabeghat(DoreyeMosabeghatEntity doreyeMosabeghat) {
		this.doreyeMosabeghat = doreyeMosabeghat;
	}


	
}
