package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.AksBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.GenderEnum;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.CaptchaUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;
import ir.infosphere.sport.util.ShamsiDate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import nl.captcha.Captcha;

import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class EditUserPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;
	private KodBiz kodBiz;

	private OzvEntity ozv;
	private AksEntity aks;

	@Wire
	private Label lblUserInfo;
	@Wire
	private Textbox txtName;
	@Wire
	private Textbox txtFamily;
	@Wire
	private Textbox txtNationalCode;
	@Wire
	private Textbox txtBirthday;
	@Wire
	private Combobox cmbGender;
	@Wire
	private Image imgCaptcha;
	@Wire
	private Textbox txtCaptchaText;
	@Wire
	private Image imgAkseOzv;
	@Wire
	private Button btnUpload;
	@Wire
	private Button btnRemoveImage;

	private Captcha captcha;

	private byte[] picture;

	private String fileName;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_EditAdmin);
		return super.doBeforeCompose(page, parent, compInfo);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("editUserPageName");
		super.doAfterCompose(comp);
		ozvBiz = appContext.getBean(OzvBiz.class);
		kodBiz = appContext.getBean(KodBiz.class);
		final Execution execution = Executions.getCurrent();
		ozv = (OzvEntity) execution.getArg().get("ozv");
		lblUserInfo.setValue("تغییر اطلاعات هویتی برای \"" + ozv.getName()
				+ " " + ozv.getFamil() + "\" به شماره " + ozv.getId()
				+ " با نام کاربری " + ozv.getNameKarbari());
		txtName.setText(ozv.getName());
		txtFamily.setText(ozv.getFamil());
		txtNationalCode.setText(ozv.getKodeMeli());
		txtBirthday.setText(ShamsiDate.get_Fa_Date(ozv.getTarikheTavallod()));
		cmbGender
				.setSelectedIndex(ozv.getJensiat().equals(
						kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard)) ? 0
						: 1);
		aks = ozv.getAks();
		if (aks != null) {
			try {
				AImage image = new AImage(aks.getNameFileAks());
				imgAkseOzv.setContent(image);
				btnRemoveImage.setVisible(true);
				picture = image.getByteData();
				fileName = image.getName();
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException for user "
						+ ozv.getId());
			}
		}
		generateNewCaptcha();
	}

	@Listen("onClick = #btnRegister; onOK = .savePage")
	public void register() throws WrongValueException, ParseException {
		String name = "";
		String family = "";
		Date birthday = null;
		GenderEnum gender = GenderEnum.Male;
		String captchaText = txtCaptchaText.getValue();
		name = txtName.getValue();
		family = txtFamily.getValue();
		birthday = ShamsiDate.get_En_Date(txtBirthday.getValue());
		gender = ((cmbGender.getSelectedItem().getValue().equals("male")) ? GenderEnum.Male
				: GenderEnum.Female);
		// if (name.isEmpty()) {
		// throw new WrongValueException(txtName,
		// ErrorTypeEnum.RequiredField.getFaName());
		// }
		// if (family.isEmpty()) {
		// throw new WrongValueException(txtFamily,
		// ErrorTypeEnum.RequiredField.getFaName());
		// }
		// if (birthday == null) {
		// throw new WrongValueException(txtBirthday,
		// ErrorTypeEnum.RequiredField.getFaName());
		// }
		// if (!PermissionUtil.getCurrentPortal().getId().toString()
		// .equals(Constants.TicketPortalID)) {
		// if (picture == null) {
		// throw new WrongValueException(btnUpload,
		// ErrorTypeEnum.RequiredField.getFaName());
		// }
		// }

		if (captchaText.isEmpty()) {
			throw new WrongValueException(txtCaptchaText,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!captchaText.toLowerCase()
				.equals(captcha.getAnswer().toLowerCase())) {
			txtCaptchaText.setValue("");
			generateNewCaptcha();
			throw new WrongValueException(txtCaptchaText,
					ErrorTypeEnum.WrongCaptcha.getFaName());
		}
		KodEntity jensiat = kodBiz.getKodEntity(Constants.Jensiat,
				Constants.Mard);
		if (gender.equals(GenderEnum.Male))
			jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard);
		if (gender.equals(GenderEnum.Female))
			jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Zan);

		if (aks == null && picture != null)
			aks = AksBiz.getAksEntityAndSave(fileName, picture);
		if (aks != null && picture != null)
			AksBiz.updateAksEntityAndSave(aks, picture);

		try {
			ozv.setName(name);
			ozv.setFamil(family);
			ozv.setTarikheTavallod(birthday);
			ozv.setJensiat(jensiat);
			ozv.setAks(aks);
			ozvBiz.update(ozv);
			Messagebox.show("اطلاعات هویتی با موفقیت به روز شد",
					"تغییر اطلاعات هویتی", Messagebox.OK,
					Messagebox.INFORMATION, new EventListener<Event>() {

						@Override
						public void onEvent(Event event) throws Exception {
							gotoUsersPage();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید",
					"خطا در ایجاد حساب کاربری جدید", Messagebox.OK,
					Messagebox.ERROR);
			generateNewCaptcha();
		}

	}

	@Listen("onUpload = #btnUpload")
	public void uploadPhoto(UploadEvent event) {
		if (!event.getMedia().getFormat().equals("jpeg")) {
			Messagebox.show("فرمت فایل انتخاب شده مناسب نمی باشد",
					"خطا در آپلود فایل", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		AImage aks;
		try {
			picture = event.getMedia().getByteData();
			aks = new AImage("aks", picture);
			imgAkseOzv.setContent(aks);
			btnRemoveImage.setVisible(true);
		} catch (IOException e) {
		}
		fileName = event.getMedia().getName();
	}

	@Listen("onClick = #btnRemoveImage")
	public void deleteImage() {
		imgAkseOzv.setContent((org.zkoss.image.Image) null);
		aks = null;
		picture = null;
		btnRemoveImage.setVisible(false);
	}

	@Listen("onClick = #btnNewCaptcha")
	public void generateNewCaptcha() {
		captcha = CaptchaUtil.generateNewCaptcha();
		imgCaptcha.setContent(captcha.getImage());
	}

	@Listen("onClick = #btnBack")
	public void gotoUsersPage() {
		NavigationUtil.goTo(UsersPage.class);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/editUser.zul";
	}

}
