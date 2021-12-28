package ir.infosphere.sport.entity;

import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.ShamsiDate;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseTakhfifEntity extends BaseEntity<Integer> {

	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasatTakhfif", nullable = false)
	private SiasatTakhfifEntity siasatTakhfif;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoePardakht", nullable = false)
	private NoePardakhtEntity noepardakht;

	@Column(name = "Mizan")
	private Integer mizan = 0;
	
	@Column(name = "TarikhShoro", nullable = false)
	private Date tarikhShoro;

	@Column(name = "TarikhPayan", nullable = false)
	private Date tarikhPayan;
	
	@Column(name = "Tozih", nullable = true, length = 10000)
	private String tozih;

	public SiasatTakhfifEntity getSiasatTakhfif() {
		return siasatTakhfif;
	}

	public void setSiasatTakhfif(SiasatTakhfifEntity siasatTakhfif) {
		this.siasatTakhfif = siasatTakhfif;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public NoePardakhtEntity getNoepardakht() {
		return noepardakht;
	}

	public void setNoepardakht(NoePardakhtEntity noepardakht) {
		this.noepardakht = noepardakht;
	}

	public Integer getMizan() {
		return mizan;
	}

	public void setMizan(Integer mizan) {
		this.mizan = mizan;
	}

	public Date getTarikhShoro() {
		return tarikhShoro;
	}

	public void setTarikhShoro(Date tarikhShoro) {
		this.tarikhShoro = tarikhShoro;
	}

	public Date getTarikhPayan() {
		return tarikhPayan;
	}

	public void setTarikhPayan(Date tarikhPayan) {
		this.tarikhPayan = tarikhPayan;
	}
	
	public String getPersianTarikhShoro() {
		try {
			return FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ShamsiDate.get_Fa_Date(this.tarikhShoro));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getPersianTarikhPayan() {
		try {
			return FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ShamsiDate.get_Fa_Date(this.tarikhPayan));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getMizanMonaseb() {
		return (this.getNoepardakht().getMablaghDarad()) ? FormatUtil.getPersianCommaSeparatedInteger(this.getMizan()) + " ریال " : FormatUtil.getPersianCommaSeparatedInteger(this.getMizan()) + " (درصد) ";
	}

	public String getTozih() {
		return tozih;
	}

	public void setTozih(String tozih) {
		this.tozih = tozih;
	}
}
