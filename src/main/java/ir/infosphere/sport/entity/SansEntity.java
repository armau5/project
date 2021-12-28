package ir.infosphere.sport.entity;


import ir.infosphere.sport.util.FormatUtil;

import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sans")
public class SansEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSans")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity shenaseyeBakhsh;

	@Column(name = "NameSans")
	private String nameSans;

	@Column(name = "ShorooeSans")
	private Time shorooeSans;

	@Column(name = "PayaneSans")
	private Time payaneSans;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BakhshEntity getShenaseyeBakhsh() {
		return shenaseyeBakhsh;
	}

	public void setShenaseyeBakhsh(BakhshEntity shenaseyeBakhsh) {
		this.shenaseyeBakhsh = shenaseyeBakhsh;
	}

	public String getNameSans() {
		return nameSans;
	}

	public void setNameSans(String nameSans) {
		this.nameSans = nameSans;
	}

	public Time getShorooeSans() {
		return shorooeSans;
	}

	public void setShorooeSans(Time shorooeSans) {
		this.shorooeSans = shorooeSans;
	}

	public Time getPayaneSans() {
		return payaneSans;
	}

	public void setPayaneSans(Time payaneSans) {
		this.payaneSans = payaneSans;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}
	
	public String getSimpleShoroSans() {
		SimpleDateFormat formatter = new SimpleDateFormat ("hh:mm");
		return FormatUtil.convertToPersianNumbersWithoutChangeOtherText(formatter.format(shorooeSans));
	}
	
	public String getSimplePayanSans() {
		SimpleDateFormat formatter = new SimpleDateFormat ("hh:mm");
		return FormatUtil.convertToPersianNumbersWithoutChangeOtherText(formatter.format(payaneSans));
	}
	
	public String getSimpleTimeSans() {
		SimpleDateFormat formatter = new SimpleDateFormat ("hh:mm");
		return "(" +  FormatUtil.convertToPersianNumbersWithoutChangeOtherText(formatter.format(payaneSans)) + " - " + FormatUtil.convertToPersianNumbersWithoutChangeOtherText(formatter.format(shorooeSans)) + ")";
	}

}
