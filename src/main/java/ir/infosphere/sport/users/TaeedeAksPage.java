package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.biz.RahgiriBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.RahgiriEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;
import ir.infosphere.sport.util.ShamsiDate;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class TaeedeAksPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;
	private KodBiz kodBiz;
	private RahgiriBiz rahgiriBiz;
	private OzvEntity ozv;

	@Wire
	private Image imgAks;
	@Wire
	private Label lblName;
	@Wire
	private Label lblFamil;
	@Wire
	private Label lblUsername;
	@Wire
	private Label lblNoeOzv;
	@Wire
	private Label lblJensiat;
	@Wire
	private Label lblKodeMelli;
	@Wire
	private Label lblTavalod;
	@Wire
	private Label lblEmail;
	@Wire
	private Label lblMobile;
	@Wire
	private Label lblSabet;
	@Wire
	private Label lblOstan;
	@Wire
	private Label lblShahrestan;
	@Wire
	private Label lblShahr;
	@Wire
	private Label lblKodePosti;
	@Wire
	private Label lblAdres;
	@Wire
	private Label lblReshte;
	@Wire
	private Label lblTeam;
	@Wire
	private Label lblAks;
	@Wire
	private Textbox txtRadKardan;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_Approve);
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("TaeedRadPageName");
		super.doAfterCompose(comp);
		ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
		kodBiz = (KodBiz) appContext.getBean("kodBiz");
		rahgiriBiz = (RahgiriBiz) appContext.getBean("rahgiriBiz");
		final Execution execution = Executions.getCurrent();
		ozv = (OzvEntity) execution.getArg().get("ozv");
		
		try {
			AImage aks = new AImage(ozv.getAks().getNameFileAks());
			imgAks.setContent(aks);
		} catch (FileNotFoundException | NullPointerException e) {
			imgAks.setSrc(Labels.getLabel("notFoundUserPictureAddress"));
		}
		lblName.setValue(ozv.getName());
		lblFamil.setValue(ozv.getFamil());
		lblUsername.setValue(ozv.getNameKarbari());
		lblNoeOzv.setValue(ozv.getNoeOzv().getMeghdar());
		lblJensiat.setValue(ozv.getJensiat().getMeghdar());
		lblKodeMelli.setValue(
				FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ozv.getKodeMeli()));
		if(ozv.getTarikheTavallod() != null){
			lblTavalod.setValue(
					FormatUtil.convertToPersianNumbersWithoutChangeOtherText(
							ShamsiDate.get_Fa_Date(ozv.getTarikheTavallod())));
		}
		lblEmail.setValue(ozv.getPosteElectronik());
		lblMobile.setValue(
				FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ozv.getShomareyeHamrah()));
		lblSabet.setValue(
				FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ozv.getShomareyeSabet()));
		lblOstan.setValue(ozv.getNahiye().getValed().getValed().getNameNahiye());
		lblShahrestan.setValue(ozv.getNahiye().getValed().getNameNahiye());
		lblShahr.setValue(ozv.getNahiye().getNameNahiye());
		lblAdres.setValue(ozv.getAdres());
		lblKodePosti.setValue(
				FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ozv.getKodePosti()));
		lblReshte
				.setValue(ozv.getReshteyeVarzeshiyeMoredeAlaghe() != null ? ozv
						.getReshteyeVarzeshiyeMoredeAlaghe()
						.getNameReshteyeVarzeshi() : "");
		lblTeam.setValue(ozv.getTimeMoredeAlaghe() != null ? ozv
				.getTimeMoredeAlaghe().getNameTim() : "");
		lblAks.setValue(ozv.getTaeedeAks() != null ? ozv.getTaeedeAks()
				.getMeghdar() : "");
	}

	@Listen("onClick = #btnTaeed")
	public void taeed() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Approve))
		{
			KodEntity taeedeAks = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_TaeedShode);
			ozv.setTaeedeAks(taeedeAks);
			ozvBiz.update(ozv);
			navigate();
		}
	}

	@Listen("onClick = #btnRad")
	public void rad() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Approve))
		{
			String dalileRadkardan = txtRadKardan.getValue();
			if (dalileRadkardan.isEmpty())
				throw new WrongValueException(txtRadKardan, ErrorTypeEnum.RequiredField.getFaName());
			KodEntity taeedeAks = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_RadShode);
			ozv.setTaeedeAks(taeedeAks);
			ozv.setDalileRadkardan(dalileRadkardan);
			ozvBiz.update(ozv);
			
			
			KodEntity kodeRadKardan = kodBiz.getKodEntity(Constants.Rahgiri, Constants.Rahgiri_RadeKarbar);
			RahgiriEntity rahgiri = new RahgiriEntity();
			rahgiri.setDideShode(false);
			rahgiri.setKarbarAnjamDahandeh(PermissionUtil.getCurrentUser());
			rahgiri.setPortal(PermissionUtil.getCurrentPortal());
			rahgiri.setOzv(ozv);
			rahgiri.setNoeAmaliat(kodeRadKardan);
			rahgiri.setTarikhZaman(new Date());
			rahgiri.setSharheAmaliat(dalileRadkardan);
			rahgiriBiz.create(rahgiri);
			
			navigate();
		}
	}
	
	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(UsersPage.class, args);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/taeedeAks.zul";
	}

}
