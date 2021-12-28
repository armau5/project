package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sanadetasviyehesab")
public class SanadetasviyehesabEntity extends BaseEntity<Integer> {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSanadeTasviye")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeHesab", nullable = false)
	private HesabEntity hesab;
	
	@ManyToOne
	@JoinColumn(name = "FaktorTasviye", nullable = false)
	private FaktorEntity faktorTasviye;
	
	@ManyToOne
	@JoinColumn(name = "NoeTasviye", nullable = false)
	private KodEntity noeTasviye;
	
	@Column(name = "ShomareSanad", nullable = true, length = 255)
	private String shomareSanad;
	
	@Column(name = "TarikheSanad", nullable = true)
	private Date tarikheSanad;
	
	@Column(name = "TarikheTasviye", nullable = false)
	private Date tarikheTasviye;
	
	@Column(name = "MablagheMande", nullable = false)
	private Long mablagheMande;
	
	@Column(name = "MablagheTasvieh", nullable = false)
	private Long mablagheTasvieh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShomareSanad() {
		return shomareSanad;
	}

	public void setShomareSanad(String shomareSanad) {
		this.shomareSanad = shomareSanad;
	}

	public Long getMablagheTasvieh() {
		return mablagheTasvieh;
	}

	public void setMablagheTasvieh(Long mablagheTasvieh) {
		this.mablagheTasvieh = mablagheTasvieh;
	}

	public HesabEntity getHesab() {
		return hesab;
	}

	public void setHesab(HesabEntity hesab) {
		this.hesab = hesab;
	}

	public Long getMablagheMande() {
		return mablagheMande;
	}

	public void setMablagheMande(Long mablagheMande) {
		this.mablagheMande = mablagheMande;
	}

	public KodEntity getNoeTasviye() {
		return noeTasviye;
	}

	public void setNoeTasviye(KodEntity noeTasviye) {
		this.noeTasviye = noeTasviye;
	}

	public Date getTarikheSanad() {
		return tarikheSanad;
	}

	public void setTarikheSanad(Date tarikheSanad) {
		this.tarikheSanad = tarikheSanad;
	}

	public Date getTarikheTasviye() {
		return tarikheTasviye;
	}

	public void setTarikheTasviye(Date tarikheTasviye) {
		this.tarikheTasviye = tarikheTasviye;
	}

	public FaktorEntity getFaktorTasviye() {
		return faktorTasviye;
	}

	public void setFaktorTasviye(FaktorEntity faktorTasviye) {
		this.faktorTasviye = faktorTasviye;
	}
	


}
