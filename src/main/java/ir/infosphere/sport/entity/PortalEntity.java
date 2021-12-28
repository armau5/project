package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "portal")
public class PortalEntity extends BaseEntity<Long> {

	@Id
	@Column(name = "PortalID",columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@Column(name = "KodeSazeman", length = 2, nullable = false)
	private String code;

	@Column(name = "NameSazeman", length = 80, nullable = false)
	private String nameSazman;
	
	@Column(name = "Damane", length = 100, nullable = true)
	private String damane;
	
	@Column(name = "Email", length = 100, nullable = true)
	private String email;
	
	@Column(name = "EmailPassword", length = 100, nullable = true)
	private String emailPassword;
	
	@Column(name = "EmailSMTP", length = 100, nullable = true)
	private String emailSMTP;
	
	@Column(name = "SupportEmail", length = 100, nullable = true)
	private String supportEmail;
	
	@Column(name = "MatneSafheyeLogin", length = 1000, nullable = true)
	private String matneSafheyeLogin;

	@Column(name = "Template", length = 200, nullable = true)
	private String template;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTim", nullable = true)
	private TimEntity tim;
	
	@Column(name = "DarkhasteKartePishkhanFaalAst", nullable = false)
	private Boolean darkhasteKartePishkhanFaalAst;
	
	@Column(name = "DarkhasteKartePostiFaalAst", nullable = false)
	private Boolean darkhasteKartePostiFaalAst;
	
	@Column(name = "GheymateKart", nullable = false)
	private Integer gheymateKart;

	@Column(name = "GheymateKarteAlmosana", nullable = false)
	private Integer gheymateKarteAlmosana;

	
	@Column(name = "GheymatePost", nullable = false)
	private Integer gheymatePost;
	
	@Column(name = "GheymateEzafeyePishkhan", nullable = false)
	private Integer gheymateEzafeyePishkhan;
	
	@Column(name = "BazeyeBeroozresanieRahgiri", nullable = false)
	private Integer bazeyeBeroozresanieRahgiri;

	public Integer getBazeyeBeroozresanieRahgiri() {
		return bazeyeBeroozresanieRahgiri;
	}

	public void setBazeyeBeroozresanieRahgiri(Integer bazeyeBeroozresanieRahgiri) {
		this.bazeyeBeroozresanieRahgiri = bazeyeBeroozresanieRahgiri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailSMTP() {
		return emailSMTP;
	}

	public void setEmailSMTP(String emailSMTP) {
		this.emailSMTP = emailSMTP;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameSazman() {
		return nameSazman;
	}

	public void setNameSazman(String nameSazman) {
		this.nameSazman = nameSazman;
	}
	
	public String getDamane() {
		return damane;
	}

	public void setDamane(String damane) {
		this.damane = damane;
	}

	public String getMatneSafheyeLogin() {
		return matneSafheyeLogin;
	}

	public void setMatneSafheyeLogin(String matneSafheyeLogin) {
		this.matneSafheyeLogin = matneSafheyeLogin;
	}

	public Boolean getDarkhasteKartePishkhanFaalAst() {
		return darkhasteKartePishkhanFaalAst;
	}

	public void setDarkhasteKartePishkhanFaalAst(
			Boolean darkhasteKartePishkhanFaalAst) {
		this.darkhasteKartePishkhanFaalAst = darkhasteKartePishkhanFaalAst;
	}

	public Boolean getDarkhasteKartePostiFaalAst() {
		return darkhasteKartePostiFaalAst;
	}

	public void setDarkhasteKartePostiFaalAst(Boolean darkhasteKartePostiFaalAst) {
		this.darkhasteKartePostiFaalAst = darkhasteKartePostiFaalAst;
	}

	public Integer getGheymateKart() {
		return gheymateKart;
	}

	public void setGheymateKart(Integer gheymateKart) {
		this.gheymateKart = gheymateKart;
	}

	public Integer getGheymatePost() {
		return gheymatePost;
	}

	public void setGheymatePost(Integer gheymatePost) {
		this.gheymatePost = gheymatePost;
	}

	public Integer getGheymateEzafeyePishkhan() {
		return gheymateEzafeyePishkhan;
	}

	public void setGheymateEzafeyePishkhan(Integer gheymateEzafeyePishkhan) {
		this.gheymateEzafeyePishkhan = gheymateEzafeyePishkhan;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public TimEntity getTim() {
		return tim;
	}

	public void setTim(TimEntity tim) {
		this.tim = tim;
	}

	public Integer getGheymateKarteAlmosana() {
		return gheymateKarteAlmosana;
	}

	public void setGheymateKarteAlmosana(Integer gheymateKarteAlmosana) {
		this.gheymateKarteAlmosana = gheymateKarteAlmosana;
	}
	
	
}
