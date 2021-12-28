package ir.infosphere.sport.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> {
	@Column(name = "ShenaseyeKarbareTaghirDahande", nullable = false)
	private Integer shenaseyeKarbareTaghirDahande = 0;

	@Column(name = "ZamaneAkharinTaghir", nullable = false)
	private Timestamp zamaneAkharinTaghir;

	@Column(name = "NoeTaghir", nullable = false)
	private Boolean noeTaghir = false;

	@Column(name = "PortaleTaghirDahandeh", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long portaleTaghirDahandeh = 0L;

	public abstract T getId();

	public abstract void setId(T entity);



	public Integer getShenaseyeKarbareTaghirDahande() {
		return shenaseyeKarbareTaghirDahande;
	}

	public void setShenaseyeKarbareTaghirDahande(Integer shenaseyeKarbareTaghirDahande) {
		this.shenaseyeKarbareTaghirDahande = shenaseyeKarbareTaghirDahande;
	}

	public Timestamp getZamaneAkharinTaghir() {
		return zamaneAkharinTaghir;
	}

	public void setZamaneAkharinTaghir(Timestamp zamaneAkharinTaghir) {
		this.zamaneAkharinTaghir = zamaneAkharinTaghir;
	}

	public Boolean getNoeTaghir() {
		return noeTaghir;
	}

	public void setNoeTaghir(Boolean noeTaghir) {
		this.noeTaghir = noeTaghir;
	}

	public Long getPortaleTaghirDahandeh() {
		return portaleTaghirDahandeh;
	}

	public void setPortaleTaghirDahandeh(Long portaleTaghirDahandeh) {
		this.portaleTaghirDahandeh = portaleTaghirDahandeh;
	}

	@Override
	public String toString() {
		return "Entity:" + this.getClass().getSimpleName() + "[" + getId()
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		BaseEntity<?> entity = (BaseEntity<?>) obj;
		return getId().equals(entity.getId());
	}

}
