package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity(name = "tarakoneshehesab")
public class TarakonesheHesabEntity extends BaseEntity<Long>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTarakonesheHesab")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeHesab", nullable = false)
	private HesabEntity hesab;
	
	@ManyToOne
	@JoinColumn(name = "NoeTarakonesh", nullable = false)
	private KodEntity noeTarakonesh;
	
	@Column(name = "ZamanTarakonesh")
	@Type(type="timestamp")
	private Date zamanTarakonesh;
	
	@Column(name = "Mablagh", nullable = false)
	private Long mablagh;
	
	public OzviyyatEntity getShenaseyeOzviyat() {
		return shenaseyeOzviyat;
	}

	public void setShenaseyeOzviyat(OzviyyatEntity shenaseyeOzviyat) {
		this.shenaseyeOzviyat = shenaseyeOzviyat;
	}

	public SharjEntity getShenaseyeSharj() {
		return shenaseyeSharj;
	}

	public void setShenaseyeSharj(SharjEntity shenaseyeSharj) {
		this.shenaseyeSharj = shenaseyeSharj;
	}

	public VaziateSandaliEntity getBelit() {
		return belit;
	}

	public void setBelit(VaziateSandaliEntity belit) {
		this.belit = belit;
	}

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart", nullable = false)
	private KartEntity kart;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzviyateService")
	private OzviyyatEntity shenaseyeOzviyat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSharj")
	private SharjEntity shenaseyeSharj;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeVaziateSandali")
	private VaziateSandaliEntity belit;
	
	

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public Long getMablagh() {
		return mablagh;
	}

	public void setMablagh(Long mablagh) {
		this.mablagh = mablagh;
	}

	public Date getZamanTarakonesh() {
		return zamanTarakonesh;
	}

	public void setZamanTarakonesh(Date zamanTarakonesh) {
		this.zamanTarakonesh = zamanTarakonesh;
	}

	public HesabEntity getHesab() {
		return hesab;
	}

	public void setHesab(HesabEntity hesab) {
		this.hesab = hesab;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public KodEntity getNoeTarakonesh() {
		return noeTarakonesh;
	}

	public void setNoeTarakonesh(KodEntity noeTarakonesh) {
		this.noeTarakonesh = noeTarakonesh;
	}
}
