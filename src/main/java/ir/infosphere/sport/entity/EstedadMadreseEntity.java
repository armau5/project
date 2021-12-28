package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "estedademadrese")
public class EstedadMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeEstedadeMadrese")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeEstedadeBashgah")
	private KodMeghdarEntity estedadBashgah;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeEstedadeTimMelli")
	private KodMeghdarEntity estedadTimMelli;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getEstedadBashgah() {
		return estedadBashgah;
	}

	public void setEstedadBashgah(KodMeghdarEntity estedadBashgah) {
		this.estedadBashgah = estedadBashgah;
	}

	public KodMeghdarEntity getEstedadTimMelli() {
		return estedadTimMelli;
	}

	public void setEstedadTimMelli(KodMeghdarEntity estedadTimMelli) {
		this.estedadTimMelli = estedadTimMelli;
	}
}
