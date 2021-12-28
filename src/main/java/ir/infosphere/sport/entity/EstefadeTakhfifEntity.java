package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "estefadetakhfif")
public class EstefadeTakhfifEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeEstefadeTakhfif")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;

	@ManyToOne
	@JoinColumn(name = "NoeSiasatTakhfif", nullable = true)
	private KodEntity noeSiasatTakhfif;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifBelit", nullable = true)
	private TakhfifeBelitEntity takhfifBelit;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifKart", nullable = true)
	private TakhfifeKartEntity takhfifKart;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifKala", nullable = true)
	private TakhfifeKalaEntity takhfifKala;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifPoz", nullable = true)
	private TakhfifePozEntity takhfifePoz;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifService", nullable = true)
	private TakhfifeServiceEntity takhfifeService;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfifSharj", nullable = true)
	private TakhfifeSharjEntity takhfifeSharj;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeService", nullable = true)
	private ServiceBakhshEntity service;

	public Integer getId() {
		return id;
	}

	public KodEntity getNoeSiasatTakhfif() {
		return noeSiasatTakhfif;
	}

	public void setNoeSiasatTakhfif(KodEntity noeSiasatTakhfif) {
		this.noeSiasatTakhfif = noeSiasatTakhfif;
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

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public TakhfifeBelitEntity getTakhfifBelit() {
		return takhfifBelit;
	}

	public void setTakhfifBelit(TakhfifeBelitEntity takhfifBelit) {
		this.takhfifBelit = takhfifBelit;
	}

	public TakhfifeKartEntity getTakhfifKart() {
		return takhfifKart;
	}

	public void setTakhfifKart(TakhfifeKartEntity takhfifKart) {
		this.takhfifKart = takhfifKart;
	}

	public TakhfifeKalaEntity getTakhfifKala() {
		return takhfifKala;
	}

	public void setTakhfifKala(TakhfifeKalaEntity takhfifKala) {
		this.takhfifKala = takhfifKala;
	}

	public TakhfifePozEntity getTakhfifePoz() {
		return takhfifePoz;
	}

	public void setTakhfifePoz(TakhfifePozEntity takhfifePoz) {
		this.takhfifePoz = takhfifePoz;
	}

	public TakhfifeServiceEntity getTakhfifeService() {
		return takhfifeService;
	}

	public void setTakhfifeService(TakhfifeServiceEntity takhfifeService) {
		this.takhfifeService = takhfifeService;
	}

	public TakhfifeSharjEntity getTakhfifeSharj() {
		return takhfifeSharj;
	}

	public void setTakhfifeSharj(TakhfifeSharjEntity takhfifeSharj) {
		this.takhfifeSharj = takhfifeSharj;
	}

	public ServiceBakhshEntity getService() {
		return service;
	}

	public void setService(ServiceBakhshEntity service) {
		this.service = service;
	}
}
