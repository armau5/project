package ir.infosphere.sport.ui.registeration;

import ir.infosphere.sport.biz.AksBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.NahiyeBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.biz.ReshteyeVarzeshiBiz;
import ir.infosphere.sport.biz.SoaleAmniatiBiz;
import ir.infosphere.sport.biz.TimBiz;
import ir.infosphere.sport.constraint.ZipCodeConstraint;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.SoaleAmniatiEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.ui.common.WelcomePage;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;
import ir.infosphere.sport.util.ShamsiDate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
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
import org.zkoss.zul.Textbox;

public class EditInfoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private NahiyeBiz nahiyeBiz;
	private OzvBiz ozvBiz;
	private KodBiz kodBiz;
	private ReshteyeVarzeshiBiz reshteyeVarzeshiBiz;
	private TimBiz timBiz;
	private SoaleAmniatiBiz soaleAmniatiBiz;

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
	private Textbox txtPhonePas, txtSecurityAnswer;
	@Wire
	private Combobox cmbFavoriteTeam;
	@Wire
	private Combobox cmbFavoriteSportField, cmbSecurityQuestion;
	@Wire
	private Label Username, name, family, NationalCode, Birthday, Gender;
	@Wire
	private Label lblTitleAlternative;
	@Wire
	private Image imgAkseOzv;
	@Wire
	private Button btnChangeSecureAnswer;
	@Wire
	private Div showAnswer;

	private byte[] picture;
	private String fileName;
	private OzvEntity ozv;
	private Boolean changeAnswerFlag;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = "";
		super.doAfterCompose(comp);
		changeAnswerFlag = false;
		nahiyeBiz = appContext.getBean(NahiyeBiz.class);
		ozvBiz = appContext.getBean(OzvBiz.class);
		reshteyeVarzeshiBiz = (ReshteyeVarzeshiBiz) appContext
				.getBean(ReshteyeVarzeshiBiz.class);
		timBiz = appContext.getBean(TimBiz.class);
		kodBiz = appContext.getBean(KodBiz.class);
		soaleAmniatiBiz = (SoaleAmniatiBiz) appContext
				.getBean(SoaleAmniatiBiz.class);
		picture = null;
		ozv = PermissionUtil.getCurrentUser();
		fillOstan();
		fillSecurityQuestions();
		fillReshteyeVarzeshi();

		lblTitleAlternative.setValue("ویرایش اطلاعات " + ozv.getName() + " "
				+ ozv.getFamil());

		picture = null;
		if (ozv.getAks() != null) {
			try {
				AImage aks = new AImage(ozv.getAks().getNameFileAks());
				imgAkseOzv.setContent(aks);
			} catch (FileNotFoundException e) {
				imgAkseOzv
						.setSrc(Labels.getLabel("notFoundUserPictureAddress"));
			}
		}
		if (ozv.getNahiye() != null) {
			cmbOstan.setValue(ozv.getNahiye().getValed().getValed()
					.getNameNahiye());
			fillShahrestan();
			cmbShahrestan.setValue(ozv.getNahiye().getValed().getNameNahiye());
			fillShahr();
			cmbShahr.setValue(ozv.getNahiye().getNameNahiye());

		}
		try {
			txtAddress.setValue(ozv.getAdres());
		} catch (Exception e) {
		}

		try {
			lbPostalCode.setValue(ozv.getKodePosti());
		} catch (Exception e) {
		}

		if (ozv.getShomareyeHamrah() != null
				&& !ozv.getShomareyeHamrah().equals("")) {
			txtMobilePish.setValue(ozv.getShomareyeHamrah().substring(0, 4));
			txtMobilePas.setValue(ozv.getShomareyeHamrah().substring(4));
		}

		if (ozv.getPosteElectronik() != null
				&& !ozv.getPosteElectronik().equals(""))
			txtEmail.setValue(ozv.getPosteElectronik());

		if (ozv.getShomareyeSabet() != null
				&& !ozv.getShomareyeSabet().equals("")) {
			int index = ozv.getShomareyeSabet().indexOf("-");
			if (index > 0) {
				txtPhonePish.setValue(ozv.getShomareyeSabet().substring(0,
						index));
				txtPhonePas.setValue(ozv.getShomareyeSabet().substring(
						index + 1));
			}
		}

		try {
			if (ozv.getReshteyeVarzeshiyeMoredeAlaghe() != null)
				cmbFavoriteSportField.setValue(ozv
						.getReshteyeVarzeshiyeMoredeAlaghe()
						.getNameReshteyeVarzeshi());
		} catch (Exception e) {
		}

		try {
			if (ozv.getTimeMoredeAlaghe() != null) {
				fillTimAndSelect();
			}
		} catch (Exception e) {
		}

		Username.setValue(ozv.getNameKarbari());
		name.setValue(ozv.getName());
		family.setValue(ozv.getFamil());
		NationalCode.setValue(FormatUtil
				.convertToPersianNumbersWithoutChangeOtherText(ozv
						.getKodeMeli()));
		if (ozv.getTarikheTavallod() != null)
			Birthday.setValue(FormatUtil
					.convertToPersianNumbersWithoutChangeOtherText((ShamsiDate
							.get_Fa_Date(ozv.getTarikheTavallod()))));
		Gender.setValue(ozv.getJensiat().getMeghdar());
	}

	@Listen("onClick = #btnChangeSecureAnswer")
	public void changeAnswer() {
		changeAnswerFlag = !changeAnswerFlag;
		showAnswer.setVisible(changeAnswerFlag);
	}

	@Listen("onClick = #btnRegister")
	public void register() {
		Integer shahrId;
		String address = txtAddress.getValue();
		String postalCode = lbPostalCode.getText();
		String email = txtEmail.getValue();
		String mobilePish = txtMobilePish.getValue();
		String mobilePas = txtMobilePas.getValue();
		String phonePish = txtPhonePish.getValue();
		String phonePas = txtPhonePas.getValue();
		Short favoriteTeamId = null;
		Short favoriteSportFieldId = null;
		Short soaleamniatiID = null;
		String pasokheAmniati = txtSecurityAnswer.getValue();
		if (cmbOstan.getValue() == "") {
			throw new WrongValueException(cmbOstan,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (cmbShahrestan.getValue() == "") {
			throw new WrongValueException(cmbShahrestan,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (cmbShahr.getValue() == "") {
			throw new WrongValueException(cmbShahr,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (address.isEmpty()) {
			throw new WrongValueException(txtAddress,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (postalCode.isEmpty()) {
			throw new WrongValueException(lbPostalCode,
					ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!ZipCodeConstraint.postalCodeIsValid(postalCode)) {
			throw new WrongValueException(lbPostalCode,
					ErrorTypeEnum.InvalidValue.getFaName());
		}
		if (mobilePish.isEmpty()) {
			throw new WrongValueException(txtMobilePish,
					ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePish);
			} catch (IllegalArgumentException e) {
				throw new WrongValueException(txtMobilePish,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
		}
		if (mobilePas.isEmpty()) {
			throw new WrongValueException(txtMobilePas,
					ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePas);
			} catch (IllegalArgumentException e) {
				throw new WrongValueException(txtMobilePas,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
		}
		if (!phonePish.isEmpty()) {
			try {
				Integer.parseInt(phonePish);
			} catch (IllegalArgumentException e) {
				throw new WrongValueException(txtPhonePish,
						ErrorTypeEnum.InvalidValue.getFaName());
			}
			if (!phonePas.isEmpty()) {
				try {
					Integer.parseInt(phonePas);
				} catch (IllegalArgumentException e) {
					throw new WrongValueException(txtPhonePas,
							ErrorTypeEnum.InvalidValue.getFaName());
				}
			}
		}
		shahrId = cmbShahr.getSelectedItem().getValue();
		if (cmbFavoriteTeam.getValue() != "")
			favoriteTeamId = cmbFavoriteTeam.getSelectedItem().getValue();
		if (cmbFavoriteSportField.getSelectedItem() != null)
			favoriteSportFieldId = cmbFavoriteSportField.getSelectedItem()
					.getValue();

		if (changeAnswerFlag == true) {
			if (cmbSecurityQuestion.getValue() != "")
				soaleamniatiID = cmbSecurityQuestion.getSelectedItem()
						.getValue();
			else
				throw new WrongValueException(cmbSecurityQuestion,
						ErrorTypeEnum.RequiredField.getFaName());
			if (pasokheAmniati.isEmpty())
				throw new WrongValueException(txtSecurityAnswer,
						ErrorTypeEnum.RequiredField.getFaName());
		}

		AksEntity aksEntity = ozv.getAks();
		if (fileName != null) {
			aksEntity = AksBiz.changeUserAks(aksEntity, fileName, picture);
		}

		try {
			if (changeAnswerFlag) {
				ozv.setSoaleAmniati(soaleAmniatiBiz
						.getSecurityQuestionById(soaleamniatiID));
				ozv.setHashePasokheSoaleAmniati(ozvBiz.getHash(pasokheAmniati));
			}
			ozv.setNahiye(nahiyeBiz.getNahiyeById(shahrId));
			ozv.setAks(aksEntity);
			ozv.setShomareyeHamrah(mobilePish + mobilePas);
			if ((phonePas + phonePish).equals(""))
				ozv.setShomareyeSabet("");
			else
				ozv.setShomareyeSabet(phonePish + "-" + phonePas);
			ozv.setKodePosti(postalCode);
			ozv.setAdres(address);
			ozv.setPosteElectronik(email);
			if (favoriteTeamId != null)
				ozv.setTimeMoredeAlaghe(timBiz.getTimById(favoriteTeamId));
			if (favoriteSportFieldId != null)
				ozv.setReshteyeVarzeshiyeMoredeAlaghe(reshteyeVarzeshiBiz
						.getReshtehById(favoriteSportFieldId));

			KodEntity vaziateKarbar;
			if (PermissionUtil.getCurrentPortal().getId().toString()
					.equals(Constants.TicketPortalID)) {
				vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar,
						Constants.VaziateKarbar_TaeedShode);
			} else {
				vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar,
						Constants.VaziateKarbar_BarresiNashode);
			}
			ozv.setTaeedeAks(vaziateKarbar);

			ozvBiz.update(ozv);
			Messagebox.show("اطلاعات کاربری شما با موفقیت به روز رسانی شد.",
					"اتمام ویرایش اطلاعات کاربری", 1, "",
					new EventListener<Event>() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							Map<String, Object> args = new HashMap<String, Object>();
							NavigationUtil.goTo(WelcomePage.class, args);
						}
					});
		} catch (Exception e) {
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید",
					"خطا در ویرایش اطلاعات کاربری", 1, "");
		}
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
		} catch (IOException e) {
		}
		fileName = event.getMedia().getName();
	}

	private void fillReshteyeVarzeshi() {
		cmbFavoriteSportField.getItems().clear();

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
			if (ozv.getSoaleAmniati() != null)
				if (ozv.getSoaleAmniati().equals(question))
					cmbSecurityQuestion.setSelectedItem(item);
		}
	}

	private void fillTimAndSelect() {
		fillTim();
		for (Comboitem item : cmbFavoriteTeam.getItems()) {
			if (ozv.getTimeMoredeAlaghe().getId().equals(item.getValue())) {
				cmbFavoriteTeam.setSelectedItem(item);
				break;
			}
		}
	}

	@Listen("onChange = #cmbFavoriteSportField")
	public void fillTim() {
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
		}
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
		cmbShahr.setText("");
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

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(WelcomePage.class, args);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/registeration/editInfo.zul";
	}

}
