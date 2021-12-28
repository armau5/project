package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "madrese")
public class MadreseEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMadrese")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahie", nullable = false)
	private NahiyeEntity nahiye;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzveMasool", nullable = false)
	private OzvEntity ozveMasool;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity tozih;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeReshteVarzeshi")
	private ReshteyeVarzeshiEntity reshteVarzeshi;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeJensiat")
	private KodEntity jensiat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks")
	private AksEntity logo;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDarajeMadrese")
	private KodEntity darajeMadrese;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoeMadrese")
	private KodEntity noeMadrese;
	
	@Column(name = "Email", length = 50)
	private String email;
	
	@Column(name = "Fax", length = 20)
	private String fax;

	@Column(name = "NameMadrese", nullable = false, length = 100)
	private String nameMadrese;
	
	@Column(name = "Telefon", length = 20)
	private String telefon;
	
	@Column(name = "AdreseMadrese", length = 200)
	private String adreseMadrese;
	
	@Column(name = "AdreseZaminChaman", length = 200)
	private String adreseZamineChaman;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal;
	
	@Column(name = "TarikheSabtMadrese")
	private Date tarikheSabt;


	// ////////////////////////

	public KodEntity getJensiat() {
		return jensiat;
	}

	public void setJensiat(KodEntity jensiat) {
		this.jensiat = jensiat;
	}

	public AksEntity getLogo() {
		return logo;
	}

	public void setLogo(AksEntity logo) {
		this.logo = logo;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdreseMadrese() {
		return adreseMadrese;
	}

	public void setAdreseMadrese(String adreseMadrese) {
		this.adreseMadrese = adreseMadrese;
	}

	public String getAdreseZamineChaman() {
		return adreseZamineChaman;
	}

	public void setAdreseZamineChaman(String adreseZamineChaman) {
		this.adreseZamineChaman = adreseZamineChaman;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public OzvEntity getOzveMasool() {
		return ozveMasool;
	}

	public void setOzveMasool(OzvEntity OzveMasool) {
		this.ozveMasool = OzveMasool;
	}

	public TozihEntity getTozih() {
		return tozih;
	}
	
	public void setTozih(TozihEntity shenaseyeTozih) {
		this.tozih = shenaseyeTozih;
	}
	
	public ReshteyeVarzeshiEntity getReshteVarzeshi() {
		return reshteVarzeshi;
	}
	
	public void setReshteVarzeshi(ReshteyeVarzeshiEntity reshteVarzeshi) {
		this.reshteVarzeshi = reshteVarzeshi;
	}
	
	public String getNameMadrese() {
		return nameMadrese;
	}

	public void setNameMadrese(String nameMadrese) {
		this.nameMadrese = nameMadrese;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}


	public KodEntity getDarajeMadrese() {
		return darajeMadrese;
	}

	public void setDarajeMadrese(KodEntity darajeMadrese) {
		this.darajeMadrese = darajeMadrese;
	}

	public KodEntity getNoeMadrese() {
		return noeMadrese;
	}

	public void setNoeMadrese(KodEntity noeMadrese) {
		this.noeMadrese = noeMadrese;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getTarikheSabt() {
		return tarikheSabt;
	}

	public void setTarikheSabt(Date tarikheSabt) {
		this.tarikheSabt = tarikheSabt;
	}
}
