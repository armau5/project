package ir.infosphere.sport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ozvebasteforoosh")
public class OzveBasteForooshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeOzveBasteForoosh")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv")
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBasteForoosh")
	private BasteForooshEntity basteForoosh;
	
	@Column(name = "tarikhKharid")
	private Date tarikhKharid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public BasteForooshEntity getBasteForoosh() {
		return basteForoosh;
	}

	public void setBasteForoosh(BasteForooshEntity basteForoosh) {
		this.basteForoosh = basteForoosh;
	}

	public Date getTarikhKharid() {
		return tarikhKharid;
	}

	public void setTarikhKharid(Date tarikhKharid) {
		this.tarikhKharid = tarikhKharid;
	}
}

