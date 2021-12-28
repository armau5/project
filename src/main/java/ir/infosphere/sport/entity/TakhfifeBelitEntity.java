package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "takhfifebelit")
public class TakhfifeBelitEntity extends BaseTakhfifEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTakhfifeBelit")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTimMizban")
	private TimEntity timMizban;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTimMihman")
	private TimEntity timMihman;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeDoreyeMosabeghat")
	private DoreyeMosabeghatEntity doreyeMosabeghat;

	@Column(name = "Tedad")
	private Integer tedad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TimEntity getTimMizban() {
		return timMizban;
	}

	public void setTimMizban(TimEntity timMizban) {
		this.timMizban = timMizban;
	}

	public TimEntity getTimMihman() {
		return timMihman;
	}

	public void setTimMihman(TimEntity timMihman) {
		this.timMihman = timMihman;
	}

	public DoreyeMosabeghatEntity getDoreyeMosabeghat() {
		return doreyeMosabeghat;
	}

	public void setDoreyeMosabeghat(DoreyeMosabeghatEntity doreyeMosabeghat) {
		this.doreyeMosabeghat = doreyeMosabeghat;
	}

	public Integer getTedad() {
		return tedad;
	}

	public void setTedad(Integer tedad) {
		this.tedad = tedad;
	}

}
