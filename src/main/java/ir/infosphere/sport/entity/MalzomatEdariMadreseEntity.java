package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "malzomatedarimadrese")
public class MalzomatEdariMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMalzomatEdari")
	private Integer id;

	@Column(name = "KhateTelefon")
	private Boolean khateTelefon;

	@Column(name = "DastgahFax")
	private Boolean dastgahFax;

	@Column(name = "Rayaneh")
	private Boolean rayaneh;
	
	@Column(name = "DastgahCopy")
	private Boolean dastgahCopy;
	
	@Column(name = "KomodLavazem")
	private Boolean komodLavazem;
	
	@Column(name = "Scaner")
	private Boolean scaner;
	
	@Column(name = "Printer")
	private Boolean printer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getKhateTelefon() {
		return khateTelefon;
	}

	public void setKhateTelefon(Boolean khateTelefon) {
		this.khateTelefon = khateTelefon;
	}

	public Boolean getDastgahFax() {
		return dastgahFax;
	}

	public void setDastgahFax(Boolean dastgahFax) {
		this.dastgahFax = dastgahFax;
	}

	public Boolean getRayaneh() {
		return rayaneh;
	}

	public void setRayaneh(Boolean rayaneh) {
		this.rayaneh = rayaneh;
	}

	public Boolean getDastgahCopy() {
		return dastgahCopy;
	}

	public void setDastgahCopy(Boolean dastgahCopy) {
		this.dastgahCopy = dastgahCopy;
	}

	public Boolean getKomodLavazem() {
		return komodLavazem;
	}

	public void setKomodLavazem(Boolean komodLavazem) {
		this.komodLavazem = komodLavazem;
	}

	public Boolean getScaner() {
		return scaner;
	}

	public void setScaner(Boolean scaner) {
		this.scaner = scaner;
	}

	public Boolean getPrinter() {
		return printer;
	}

	public void setPrinter(Boolean printer) {
		this.printer = printer;
	}	
}
