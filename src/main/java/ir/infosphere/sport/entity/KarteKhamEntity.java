package ir.infosphere.sport.entity;

import ir.infosphere.sport.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "kartekham")
public class KarteKhamEntity extends BaseEntity<String> {

	@Id
	@Column(name = "Serial", length = 7)
	private String id;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}
}
