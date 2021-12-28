package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tagekart")
public class TageKartEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTageKart")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTag")
	private TagEntity tag;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKart")
	private KartEntity kart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TagEntity getTag() {
		return tag;
	}

	public void setTag(TagEntity tag) {
		this.tag = tag;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}
}
