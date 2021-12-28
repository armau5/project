package ir.infosphere.sport.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "bazi")
public class BaziEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBazi")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeDoreyeMosabeghat", nullable = false)
	private DoreyeMosabeghatEntity doreyeMosabeghat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMizban", nullable = false)
	private TimEntity mizban;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMihman", nullable = false)
	private TimEntity mihman;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity bakhsh;

	@Column(name = "ZamaneBazi", nullable = true)
	private Time zamaneBazi;

	@Column(name = "ForoosheBelitFaalAst", nullable = false)
	private Boolean foroosheBelitFaalAst;

	@Column(name = "BaziyeHassas", nullable = false)
	private Boolean baziyeHassas;

	@Column(name = "TarikheBazi", nullable = true)
	private Date tarikheBazi;

	@Column(name = "Sync", nullable = false)
	private Boolean sync = false;

	@Column(name = "TarikheLaghv")
	private Date tarikheLaghv;

	@OneToMany(mappedBy = "bazi")
	private List<GheymateBaziEntity> gheymatList;

	@Column(name = "ZamaneShorooeSyncShodan", nullable = true)
	private java.util.Date zamaneShorooeSyncShodan;

	@Column(name = "ZamanePayaneSyncShodan", nullable = true)
	private java.util.Date zamanePayaneSyncShodan;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TimEntity getMizban() {
		return mizban;
	}

	public void setMizban(TimEntity mizban) {
		this.mizban = mizban;
	}

	public TimEntity getMihman() {
		return mihman;
	}

	public void setMihman(TimEntity mihman) {
		this.mihman = mihman;
	}

	public BakhshEntity getBakhsh() {
		return bakhsh;
	}

	public void setBakhsh(BakhshEntity bakhsh) {
		this.bakhsh = bakhsh;
	}

	public Time getZamaneBazi() {
		return zamaneBazi;
	}

	public void setZamaneBazi(Time zamaneBazi) {
		this.zamaneBazi = zamaneBazi;
	}

	public Boolean getForoosheBelitFaalAst() {
		return foroosheBelitFaalAst;
	}

	public void setForoosheBelitFaalAst(Boolean foroosheBelitFaalAst) {
		this.foroosheBelitFaalAst = foroosheBelitFaalAst;
	}

	public Boolean getBaziyeHassas() {
		return baziyeHassas;
	}

	public void setBaziyeHassas(Boolean baziyeHassas) {
		this.baziyeHassas = baziyeHassas;
	}

	public Date getTarikheBazi() {
		return tarikheBazi;
	}

	public void setTarikheBazi(Date tarikheBazi) {
		this.tarikheBazi = tarikheBazi;
	}

	public Boolean getSync() {
		return sync;
	}

	public void setSync(Boolean sync) {
		this.sync = sync;
	}

	public Date getTarikheLaghv() {
		return tarikheLaghv;
	}

	public void setTarikheLaghv(Date tarikheLaghv) {
		this.tarikheLaghv = tarikheLaghv;
	}

	public DoreyeMosabeghatEntity getDoreyeMosabeghat() {
		return doreyeMosabeghat;
	}

	public void setDoreyeMosabeghat(DoreyeMosabeghatEntity doreyeMosabeghat) {
		this.doreyeMosabeghat = doreyeMosabeghat;
	}

	public List<GheymateBaziEntity> getGheymatList() {
		return gheymatList;
	}

	public void setGheymatList(List<GheymateBaziEntity> gheymatList) {
		this.gheymatList = gheymatList;
	}

	public java.util.Date getZamaneShorooeSyncShodan() {
		return zamaneShorooeSyncShodan;
	}

	public void setZamaneShorooeSyncShodan(
			java.util.Date zamaneShorooeSyncShodan) {
		this.zamaneShorooeSyncShodan = zamaneShorooeSyncShodan;
	}

	public java.util.Date getZamanePayaneSyncShodan() {
		return zamanePayaneSyncShodan;
	}

	public void setZamanePayaneSyncShodan(java.util.Date zamanePayaneSyncShodan) {
		this.zamanePayaneSyncShodan = zamanePayaneSyncShodan;
	}

}
