package ir.infosphere.sport.ui.registeration;

import ir.faasaa.persiandatebox.PersianDatebox;
import ir.infosphere.sport.biz.AksBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.NahiyeBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.biz.ReshteyeVarzeshiBiz;
import ir.infosphere.sport.biz.SoaleAmniatiBiz;
import ir.infosphere.sport.biz.TimBiz;
import ir.infosphere.sport.constraint.ZipCodeConstraint;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.GenderEnum;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.SoaleAmniatiEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.ui.common.DarkhasteKartPage;
import ir.infosphere.sport.ui.common.LoginPage;
import ir.infosphere.sport.ui.users.UsersPage;
import ir.infosphere.sport.util.CaptchaUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class RegisterPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private NahiyeBiz nahiyeBiz;
	private OzvBiz ozvBiz;
	private SoaleAmniatiBiz soaleAmniatiBiz;
	private ReshteyeVarzeshiBiz reshteyeVarzeshiBiz;
	private TimBiz timBiz;
	private KodBiz kodBiz;
	private String returnUrl;

	
	@Wire
	private Radio radioReal;
	@Wire
	private Radio radioInternational;
	@Wire
	private Div divRealAccount;
	@Wire
	private Div divInternationalAccount;
	@Wire
	private Textbox txtName;
	@Wire
	private Textbox txtLegalName;
	@Wire
	private Textbox txtInternationalName;
	@Wire
	private Textbox txtFamily;
	@Wire
	private Textbox txtInternationalFamily;
	@Wire
	private Textbox txtNationalCode;
	@Wire
	private Textbox txtPassportNumber;
	@Wire
	private PersianDatebox dateboxBirthday;
	@Wire
	private Textbox txtInternationalBirthday;
	@Wire
	private Combobox cmbGender;
	@Wire
	private Combobox cmbInternationalGender;
	@Wire
	private Combobox cmbOstan;
	@Wire
	private Combobox cmbShahrestan;
	@Wire
	private Combobox cmbShahr;
	@Wire
	private Textbox txtAddress;
	@Wire
	private Textbox lbPostalCode;
	@Wire
	private Textbox txtEmail;
	@Wire
	private Textbox txtMobilePish;
	@Wire
	private Textbox txtMobilePas;
	@Wire
	private Textbox txtPhonePish;
	@Wire
	private Textbox txtPhonePas;
	@Wire
	private Combobox cmbFavoriteTeam;
	@Wire
	private Combobox cmbFavoriteSportField;
	@Wire
	private Textbox txtUsername;
	@Wire
	private Textbox txtPassword;
	@Wire
	private Textbox txtPasswordAgain;
	@Wire
	private Combobox cmbSecurityQuestion, cmbKeshvar;
	@Wire
	private Textbox txtSecurityAnswer;
	@Wire
	private Image imgCaptcha;
	@Wire
	private Textbox txtCaptchaText;
	@Wire
	private Image imgAkseOzv;
	@Wire
	private Image imgAkseOzveInternational;
	@Wire
	private Button btnUpload;
	@Wire
	private Button btnInternationalUpload, btnRemoveImage;
	@Wire
	private Label lblImageEjbari;
	@Wire
	private Radiogroup rgAccountType;

	private Captcha captcha;

	private byte[] picture;

	private String fileName;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = "ثبت نام";
		super.doAfterCompose(comp);
		nahiyeBiz = appContext.getBean(NahiyeBiz.class);
		ozvBiz = appContext.getBean(OzvBiz.class);
		soaleAmniatiBiz = appContext.getBean(SoaleAmniatiBiz.class);
		reshteyeVarzeshiBiz = appContext.getBean(ReshteyeVarzeshiBiz.class);
		timBiz = appContext.getBean(TimBiz.class);
		kodBiz = appContext.getBean(KodBiz.class);

		if (PermissionUtil.getCurrentPortal().getId().toString()
				.equals(Constants.TicketPortalID)) {
			lblImageEjbari.setVisible(false);
		}

		cmbGender.setSelectedIndex(0);
		cmbInternationalGender.setSelectedIndex(0);
		picture = null;

		fillOstan();
		fillReshteyeVarzeshi();
		fillSecurityQuestions();
		fillKeshvar();
		generateNewCaptcha();
		final Execution execution = Executions.getCurrent();
		returnUrl = (String) execution.getArg().get("returnUrl");

		if (!PermissionUtil.getCurrentPortal().getId().toString()
				.equals(Constants.MembershipPortalID))
			rgAccountType.setVisible(false);
	}

	public void fillKeshvar() {
		cmbKeshvar.getItems().clear();

		List<KodEntity> list = kodBiz.getAllCodesByGorooheKod(
				Constants.Keshvar, true);
		for (KodEntity kod : list) {
			Comboitem item = new Comboitem();
			item.setValue(kod.getId());
			item.setLabel(kod.getMeghdar());
			item.setSclass("rtl");
			item.setParent(cmbKeshvar);
		}
	}

	@SuppressWarnings("deprecation")
	@Listen("onClick = #btnRegister; onOK = .savePage")
	public void register() throws WrongValueException, ParseException {
		
		String captchaText = txtCaptchaText.getValue();
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
		
		String name = "";
		String legalName = "";
		String internationalName = "";
		String family = "";
		String internationalFamily = "";
		String nationalCode = "";
		String registrationNumber = "";
		String passportNumber = "";
		Date birthday = null;
		Date foundation = null;
		GenderEnum gender = GenderEnum.Male;
		GenderEnum internationalGender = null;
		Integer shahrId;
		String address = txtAddress.getValue();
		String postalCode = lbPostalCode.getText();
		String email = txtEmail.getValue();
		String mobilePish = txtMobilePish.getValue().toString();
		String mobilePas = txtMobilePas.getValue().toString();
		String phonePish = txtPhonePish.getValue().toString();
		String phonePas = txtPhonePas.getValue().toString();
		String phone = phonePish + "-" + phonePas;
		Short favoriteTeamId = null;
		Short favoriteSportFieldId = null;
		String username = txtUsername.getValue();
		String password = txtPassword.getValue();
		String passwordAgain = txtPasswordAgain.getValue();
		Short securityQuestionId;
		String securityAnswer = txtSecurityAnswer.getValue();
		KodEntity keshvar = null;

		if (radioReal.isSelected()) {// Haghighi
			name = txtName.getValue();
			family = txtFamily.getValue();
			nationalCode = txtNationalCode.getValue().toString();
			gender = ((cmbGender.getSelectedItem().getValue().equals("male")) ? GenderEnum.Male
					: GenderEnum.Female);
			if (name.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtName,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (family.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtFamily,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (nationalCode.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtNationalCode,
						ErrorTypeEnum.InValidNationalCode.getFaName());
			}
			if (!nationalCodeIsValid(nationalCode)) {
				generateNewCaptcha();
				throw new WrongValueException(txtNationalCode,
						ErrorTypeEnum.InvalidNationalCode.getFaName());
			}
			if (dateboxBirthday.getText() == null) {
				generateNewCaptcha();
				throw new WrongValueException(dateboxBirthday,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			try {
				birthday = dateboxBirthday.getDate();
			} catch (Exception e){
				generateNewCaptcha();
				throw new WrongValueException(dateboxBirthday,
						ErrorTypeEnum.InvalidDateFarsi.getFaName());
			}
			if (birthday == null) {
				generateNewCaptcha();
				throw new WrongValueException(dateboxBirthday,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (!PermissionUtil.getCurrentPortal().getId().toString()
					.equals(Constants.TicketPortalID)) {
				if (picture == null) {
					generateNewCaptcha();
					throw new WrongValueException(btnUpload,
							ErrorTypeEnum.RequiredField.getFaName());
				}
			}
		}
		if (radioInternational.isSelected()) {// Beynolmelali
			internationalName = txtInternationalName.getValue();
			internationalFamily = txtInternationalFamily.getValue();
			passportNumber = txtPassportNumber.getValue();
			if (cmbKeshvar.getSelectedItem() != null)
				keshvar = kodBiz.retrieveKodById(Short.valueOf(cmbKeshvar
						.getSelectedItem().getValue().toString()));
			else{
				generateNewCaptcha();
				throw new WrongValueException(cmbKeshvar,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (txtInternationalBirthday.getValue().isEmpty()){
				generateNewCaptcha();
				throw new WrongValueException(txtInternationalBirthday,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			birthday = new Date(txtInternationalBirthday.getValue());
			internationalGender = ((cmbInternationalGender.getSelectedItem()
					.getValue().equals("male")) ? GenderEnum.Male
					: GenderEnum.Female);
			if (internationalName.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtInternationalName,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (internationalFamily.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtInternationalFamily,
						ErrorTypeEnum.RequiredField.getFaName());
			}
			if (passportNumber.isEmpty()) {
				generateNewCaptcha();
				throw new WrongValueException(txtPassportNumber,
						ErrorTypeEnum.RequiredField.getFaName());
			} else {
				if (ozvBiz.passportIsRepeat(passportNumber)) {
					generateNewCaptcha();
					throw new WrongValueException(txtPassportNumber,
							ErrorTypeEnum.RepeatedPassportNumber.getFaName());
				}
			}
			if (picture == null) {
				generateNewCaptcha();
				throw new WrongValueException(btnInternationalUpload,
						ErrorTypeEnum.RequiredField.getFaName());
			}
		}

		if (cmbOstan.getSelectedItem() == null) {
			generateNewCaptcha();
			throw new WrongValueException(cmbOstan,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (cmbShahrestan.getSelectedItem() == null) {
			generateNewCaptcha();
			throw new WrongValueException(cmbShahrestan,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (cmbShahr.getSelectedItem() == null) {
			generateNewCaptcha();
			throw new WrongValueException(cmbShahr,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (address.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtAddress,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (postalCode.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(lbPostalCode,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!ZipCodeConstraint.postalCodeIsValid(postalCode)) {
			generateNewCaptcha();
			throw new WrongValueException(lbPostalCode,
					ErrorTypeEnum.InvalidValue.getFaName());
		}
		if (mobilePish.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtMobilePish,
					ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePish);
			} catch (IllegalArgumentException e) {
				generateNewCaptcha();
				throw new WrongValueException(txtMobilePish,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
		}
		if (mobilePas.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtMobilePas,
					ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePas);
			} catch (IllegalArgumentException e) {
				generateNewCaptcha();
				throw new WrongValueException(txtMobilePas,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
		}
		if (!phonePish.isEmpty()) {
			try {
				Integer.parseInt(phonePish);
			} catch (IllegalArgumentException e) {
				generateNewCaptcha();
				throw new WrongValueException(txtPhonePish,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
			if (!phonePas.isEmpty()) {
				try {
					Integer.parseInt(phonePas);
				} catch (IllegalArgumentException e) {
					generateNewCaptcha();
					throw new WrongValueException(txtPhonePas,
							ErrorTypeEnum.InvalidValue.getFaName());
				}
			}
		}
		if (username.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtUsername,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (ozvBiz.usernameIsRepeat(username)) {
			generateNewCaptcha();
			throw new WrongValueException(txtUsername,
					ErrorTypeEnum.RepeatedUsername.getFaName());
		}
		if (!nationalCode.isEmpty())
			if (ozvBiz.kodemelliIsRepeat(nationalCode)) {
				generateNewCaptcha();
				throw new WrongValueException(txtNationalCode,
						ErrorTypeEnum.RepeatedNationalCode.getFaName());
			}
		if (password.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtPassword,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (passwordAgain.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtPasswordAgain,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (cmbSecurityQuestion.getSelectedItem() == null) {
			generateNewCaptcha();
			throw new WrongValueException(cmbSecurityQuestion,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (securityAnswer.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtSecurityAnswer,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!password.equals(passwordAgain)) {
			generateNewCaptcha();
			throw new WrongValueException(txtPasswordAgain,
					ErrorTypeEnum.InvalidRepeatPassword.getFaName());
		}

		shahrId = cmbShahr.getSelectedItem().getValue();
		if (cmbFavoriteTeam.getSelectedItem() != null)
			favoriteTeamId = cmbFavoriteTeam.getSelectedItem().getValue();
		if (cmbFavoriteSportField.getSelectedItem() != null)
			favoriteSportFieldId = cmbFavoriteSportField.getSelectedItem()
					.getValue();
		securityQuestionId = cmbSecurityQuestion.getSelectedItem().getValue();

		KodEntity noeOzv = kodBiz.getKodEntity(Constants.NoeOzv,
				Constants.Haghighi);
		KodEntity jensiat = kodBiz.getKodEntity(Constants.Jensiat,
				Constants.Mard);
		if (radioReal.isSelected()) {// Haghighi
			noeOzv = kodBiz.getKodEntity(Constants.NoeOzv, Constants.Haghighi);
			if (gender.equals(GenderEnum.Male))
				jensiat = kodBiz
						.getKodEntity(Constants.Jensiat, Constants.Mard);
			if (gender.equals(GenderEnum.Female))
				jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Zan);
		}
		if (radioInternational.isSelected()) {// Beynolmelali
			noeOzv = kodBiz.getKodEntity(Constants.NoeOzv,
					Constants.Beynolmelali);
			if (internationalGender.equals(GenderEnum.Male))
				jensiat = kodBiz
						.getKodEntity(Constants.Jensiat, Constants.Mard);
			if (internationalGender.equals(GenderEnum.Female))
				jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Zan);
		}

		AksEntity aksEntity = null;
		if (picture != null)
			aksEntity = AksBiz.getAksEntityAndSave(fileName, picture);

		KodEntity vaziateKarbar;
		if (PermissionUtil.getCurrentPortal().getId().toString()
				.equals(Constants.TicketPortalID)) {
			vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar,
					Constants.VaziateKarbar_TaeedShode);
		} else {
			vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar,
					Constants.VaziateKarbar_BarresiNashode);
		}

		PortalEntity portal = PermissionUtil.getCurrentPortal();

		try {
			OzvEntity ozv = ozvBiz
					.createOzv(
							nahiyeBiz.getNahiyeById(shahrId),
							aksEntity,
							noeOzv,
							passportNumber,
							(name.isEmpty()) ? internationalName : name,
							(family.isEmpty()) ? internationalFamily : family,
							legalName,
							nationalCode,
							registrationNumber,
							mobilePish + mobilePas,
							phone,
							postalCode,
							address,
							email,
							jensiat,
							birthday,
							foundation,
							username,
							password,
							soaleAmniatiBiz
									.getSecurityQuestionById(securityQuestionId),
							ozvBiz.getHash(securityAnswer),
							(favoriteTeamId != null) ? timBiz
									.getTimById(favoriteTeamId) : null,
							(favoriteSportFieldId != null) ? reshteyeVarzeshiBiz
									.getReshtehById(favoriteSportFieldId)
									: null, vaziateKarbar, portal, keshvar);
			if (PermissionUtil.getCurrentUser() != null) {
				Messagebox
						.show("حساب کاربری شما با موفقیت ایجاد شد. لطفا  وارد سامانه شوید",
								"اتمام ایجاد حساب کاربری جدید", 1, "",
								new EventListener<Event>() {
									@Override
									public void onEvent(Event arg0)
											throws Exception {
										Executions.sendRedirect("/");
									}
								});
			} else {
				PermissionUtil.setCurrentUser(ozv);
				Messagebox
						.show("حساب کاربری شما با موفقیت ایجاد شد. ضمنا شما از این لحظه وارد سامانه شده‌اید.",
								"اتمام ایجاد حساب کاربری جدید", 1, "",
								new EventListener<Event>() {
									@Override
									public void onEvent(Event arg0)
											throws Exception {
										if (!PermissionUtil
												.getCurrentPortal()
												.getId()
												.toString()
												.equals(Constants.TicketPortalID)) {
											Executions.sendRedirect("/");
										} else {
											NavigationUtil
													.goTo(DarkhasteKartPage.class);
										}
									}
								});
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید",
					"خطا در ایجاد حساب کاربری جدید", 1, "");
			generateNewCaptcha();
		}

	}

	@Listen("onCheck = #radioReal")
	public void showRealRegistration() {
		divRealAccount.setVisible(true);
		divInternationalAccount.setVisible(false);
	}

	@Listen("onCheck = #radioInternational")
	public void showInternationalRegistration() {
		divRealAccount.setVisible(false);
		divInternationalAccount.setVisible(true);
	}

	@Listen("onUpload = #btnUpload")
	public void uploadPhoto(UploadEvent event) {
		if (!event.getMedia().getFormat().equals("jpeg")) {
			Messagebox.show("فرمت فایل انتخاب شده مناسب نمی باشد",
					"خطا در آپلود فایل", 1, "");
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
		btnRemoveImage.setVisible(false);
	}

	@Listen("onUpload = #btnInternationalUpload")
	public void uploadInternationalPhoto(UploadEvent event) {
		if (!event.getMedia().getFormat().equals("jpeg"))
			Messagebox.show("فرمت فایل انتخاب شده مناسب نمی باشد",
					"خطا در آپلود فایل", 1, "");
		else {
			AImage aks;
			try {
				picture = event.getMedia().getByteData();
				aks = new AImage("aks", picture);
				imgAkseOzveInternational.setContent(aks);
			} catch (IOException e) {
			}
		}
		fileName = event.getMedia().getName();
	}

	private void fillReshteyeVarzeshi() {
		cmbFavoriteSportField.getItems().clear();

		ReshteyeVarzeshiEntity defaultReshte = null;
		if (PermissionUtil.getCurrentPortal().getTim() != null) {
			defaultReshte = PermissionUtil.getCurrentPortal().getTim()
					.getGorooheReshteyeVarzeshi().getReshteyeVarzeshi();
		}

		List<ReshteyeVarzeshiEntity> allReshteh = reshteyeVarzeshiBiz
				.getAllReshteh();
		if (allReshteh == null)
			return;
		for (ReshteyeVarzeshiEntity reshteh : allReshteh) {
			Comboitem item = new Comboitem();
			item.setValue(reshteh.getId());
			item.setLabel(reshteh.getNameReshteyeVarzeshi());
			item.setSclass("rtl");
			item.setParent(cmbFavoriteSportField);
			if (reshteh.equals(defaultReshte))
				cmbFavoriteSportField.setSelectedItem(item);
		}
		
		if (cmbFavoriteSportField.getSelectedItem() != null)
			fillTimByExactTim(PermissionUtil.getCurrentPortal().getTim());

	}
	
	private void fillTimByExactTim(TimEntity defaultTim) {
		cmbFavoriteTeam.setText("");
		cmbFavoriteTeam.getItems().clear();
		List<TimEntity> allTim = timBiz.getAllTimByReshteh(reshteyeVarzeshiBiz
				.getReshteh(cmbFavoriteSportField.getText()));
		if (allTim == null)
			return;
		for (TimEntity tim : allTim) {
			Comboitem item = new Comboitem();
			item.setValue(tim.getId());
			item.setLabel(tim.getNameTim());
			item.setSclass("rtl");
			item.setParent(cmbFavoriteTeam);
			if (tim.equals(defaultTim))
				cmbFavoriteTeam.setSelectedItem(item);
		}
	}

	@Listen("onChange = #cmbFavoriteSportField")
	public void fillTim() {
		fillTimByExactTim(null);
	}

	private void fillOstan() {
		cmbOstan.getItems().clear();

		List<String> allOstan = nahiyeBiz.getAllOstan();
		if (allOstan == null)
			return;
		for (String ostan : allOstan) {
			Comboitem item = new Comboitem();
			item.setValue(ostan);
			item.setLabel(ostan);
			item.setSclass("rtl");
			item.setParent(cmbOstan);
		}
	}

	@Listen("onChange = #cmbOstan")
	public void fillShahrestan() {
		cmbShahrestan.setText("");
		cmbShahrestan.getItems().clear();
		cmbShahr.getItems().clear();

		List<String> allShahrestan = nahiyeBiz.getAllShahrestan(cmbOstan
				.getText());
		if (allShahrestan == null)
			return;
		for (String shahrestan : allShahrestan) {
			Comboitem item = new Comboitem();
			item.setValue(shahrestan);
			item.setLabel(shahrestan);
			item.setSclass("rtl");
			item.setParent(cmbShahrestan);
		}
	}

	@Listen("onChange = #cmbShahrestan")
	public void fillShahr() {
		cmbShahr.setText("");
		cmbShahr.getItems().clear();

		List<NahiyeEntity> allShahr = nahiyeBiz.getAllShahr(cmbOstan.getText(),
				cmbShahrestan.getText());
		if (allShahr == null)
			return;
		for (NahiyeEntity shahr : allShahr) {
			Comboitem item = new Comboitem();
			item.setValue(shahr.getId());
			item.setLabel(shahr.getNameNahiye());
			item.setSclass("rtl");
			item.setParent(cmbShahr);
		}
	}

	private void fillSecurityQuestions() {
		cmbSecurityQuestion.getItems().clear();
		List<SoaleAmniatiEntity> allQuestions = soaleAmniatiBiz
				.getAllSecurityQuestions();
		if (allQuestions == null)
			return;
		for (SoaleAmniatiEntity question : allQuestions) {
			Comboitem item = new Comboitem();
			item.setValue(question.getId());
			item.setLabel(question.getSoal());
			item.setSclass("rtl");
			item.setParent(cmbSecurityQuestion);
		}
	}

	@Listen("onClick = #btnNewCaptcha")
	public void generateNewCaptcha() {
		captcha = CaptchaUtil.generateNewCaptcha();
		imgCaptcha.setContent(captcha.getImage());
		txtCaptchaText.setValue("");
	}

	private boolean nationalCodeIsValid(String nationalCode) {
		if (nationalCode.length() != 10)
			return false;
		int digit[] = new int[10];
		for (int i = 0; i < digit.length; i++)
			try {
				digit[i] = Integer.parseInt(nationalCode.charAt(i) + "");
			} catch (NumberFormatException e) {
				return false;
			}

		int sum = 0;
		for (int i = 0; i < digit.length - 1; i++)
			sum += (10 - i) * digit[i];

		if ((sum % 11) < 2) {
			if (digit[9] != (sum % 11))
				return false;
		} else if (digit[9] != 11 - (sum % 11))
			return false;

		return true;
	}

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		if (returnUrl == "UsersPage")
			NavigationUtil.goTo(UsersPage.class, args);
		else
			NavigationUtil.goTo(LoginPage.class, args);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/registeration/register.zul";
	}

}
