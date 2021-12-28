package ir.infosphere.sport.ui.registeration;

import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.biz.SiasatBiz;
import ir.infosphere.sport.biz.SoaleAmniatiBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.GenderEnum;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.ui.registeration.ForgotPassPage;
import ir.infosphere.sport.ui.registeration.RegisterPage;
import ir.infosphere.sport.ui.siasat.PardakhtPage;
import ir.infosphere.sport.util.CaptchaUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
//import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class LoginPage extends BasePage {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	private OzvBiz ozvBiz;
	@SuppressWarnings("unused")
	private Class<? extends BasePage> prePage;
	private Class<? extends BasePage> postPage;
	private Map<String, Object> postPageArgs;
	private Boolean mayHacker = false;
	private Captcha captcha;
	private final static Integer maxWrongUserOrPass = 3;
	private KodBiz kodBiz;
	private SoaleAmniatiBiz soaleAmniatiBiz;

	@Wire
	private Textbox txtUserName;

	@Wire
	private Textbox txtPassword;

	@Wire
	private Checkbox chkSaveUserAndPass;

	@Wire
	private Html htmlPortalContent;

	@Wire
	private Div divCaptcha;
	@Wire
	private Div divCaptcha1;

	@Wire
	private Image imgCaptcha;
	@Wire
	private Image imgCaptcha1;

	@Wire
	private Button btnNewCaptcha;
	@Wire
	private Button btnNewCaptcha1;

	@Wire
	private Textbox txtCaptchaText;
	// @Wire
	// private Combobox cmbGender;
	@Wire
	private Textbox txtMobilePish;
	@Wire
	private Textbox txtMobilePas;
	@Wire
	private Textbox txtName;
	@Wire
	private Textbox txtFamily;
	@Wire
	private Textbox txtNationalCode;
	@Wire
	private Textbox txtCaptchaText1;

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		pageName = Labels.getLabel("logInPageName");
		super.doAfterCompose(comp);

		kodBiz = appContext.getBean(KodBiz.class);
		ozvBiz = appContext.getBean(OzvBiz.class);
		soaleAmniatiBiz = appContext.getBean(SoaleAmniatiBiz.class);
		Object user = Sessions.getCurrent().getAttribute("user");
		if (user != null)
			txtUserName.setText(((OzvEntity) user).getNameKarbari());
		else {
			Object username = Sessions.getCurrent().getAttribute("username");
			if (username != null)
				txtUserName.setText((String) username);
			else
				setUserAndPassFromCookies();
		}

		final Execution execution = Executions.getCurrent();
		prePage = (Class<? extends BasePage>) execution.getArg().get("prePage");
		postPage = (Class<? extends BasePage>) execution.getArg().get("postPage");
		postPageArgs = (Map<String, Object>) execution.getArg().get("postPageArgs");

		if (PermissionUtil.getCurrentPortal() != null)
			htmlPortalContent.setContent(PermissionUtil.getCurrentPortal().getMatneSafheyeLogin());

		if (PermissionUtil.getWrongUserOrPassCounter() >= maxWrongUserOrPass) {
			showCaptcha(0);
		}
		generateNewCaptchaRegister();
	}

	private void setUserAndPassFromCookies() {
		Cookie[] cookies = ((HttpServletRequest) Executions.getCurrent().getNativeRequest()).getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(getServerAddress() + "userName")) {
					if (cookie.getValue() != null && !cookie.getValue().equals(""))
						txtUserName.setValue(cookie.getValue());
				} else if (cookie.getName().equals(getServerAddress() + "password")) {
					if (cookie.getValue() != null && !cookie.getValue().equals("")) {
						txtPassword.setValue(cookie.getValue());
						chkSaveUserAndPass.setChecked(true);
					}
				}
			}
		}
	}

	private String getServerAddress() {
		String port = (Executions.getCurrent().getServerPort() == 80) ? ""
				: (":" + Executions.getCurrent().getServerPort());
		String serverAddress = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName()
				+ port + Executions.getCurrent().getContextPath();
		return serverAddress;
	}

	private void setCookies(String userName, String password) {
		((HttpServletResponse) Executions.getCurrent().getNativeResponse())
				.addCookie(new Cookie(getServerAddress() + "userName", userName));
		((HttpServletResponse) Executions.getCurrent().getNativeResponse())
				.addCookie(new Cookie(getServerAddress() + "password", password));
	}

	private void checkCaptcha(int i) {
		if (i == 0) {
			String captchaText = txtCaptchaText.getValue();
			if (captchaText.isEmpty()) {
				throw new WrongValueException(txtCaptchaText, ErrorTypeEnum.RequiredField.getFaName());
			}
			if (!captchaText.toLowerCase().equals(captcha.getAnswer().toLowerCase())) {
				txtCaptchaText.setValue("");
				generateNewCaptcha();
				throw new WrongValueException(txtCaptchaText, ErrorTypeEnum.WrongCaptcha.getFaName());
			}
		} else {
			String captchaText1 = txtCaptchaText1.getValue();
			if (captchaText1.isEmpty()) {
				throw new WrongValueException(txtCaptchaText1, ErrorTypeEnum.RequiredField.getFaName());
			}
			if (!captchaText1.toLowerCase().equals(captcha.getAnswer().toLowerCase())) {
				txtCaptchaText1.setValue("");
				generateNewCaptchaRegister();
				throw new WrongValueException(txtCaptchaText1, ErrorTypeEnum.WrongCaptcha.getFaName());
			}
		}
	}

	private void showCaptcha(int i) {
		if (i == 0) {
			mayHacker = true;
			divCaptcha.setVisible(true);
			generateNewCaptcha();
		} else {
			divCaptcha1.setVisible(true);
			generateNewCaptchaRegister();
		}

	}

	@Listen("onClick = #btnLogin; onOK = textbox, #chkSaveUserAndPass")
	public void login() {
		if (mayHacker) {
			checkCaptcha(0);
		}
		String userName = txtUserName.getValue();
		String password = txtPassword.getValue();
		if (password == null || password.equals(""))
			throw new WrongValueException(txtPassword, "گذرواژه ضروری است");
		OzvEntity ozv = ozvBiz.authenticate(userName, password);
		if (ozv == null) {
			Messagebox.show(ErrorTypeEnum.WrongUserOrPass.getFaName(), ErrorTypeEnum.MessageBoxTitleError.getFaName(),
					Messagebox.OK, Messagebox.ERROR);
			PermissionUtil.increaseWrongUserOrPassCounter();
			Integer wrongUserOrPassCounter = PermissionUtil.getWrongUserOrPassCounter();
			logger.warn("Wrong username or password for user: " + userName + " password: " + password + " IP: "
					+ Executions.getCurrent().getRemoteAddr() + ", Browser: " + Executions.getCurrent().getBrowser()
					+ ", UserAgent: " + Executions.getCurrent().getUserAgent() + ", Header: "
					+ Executions.getCurrent().getHeader("X-Forwarded-For") + ", Number Of Wrong Attempts: "
					+ wrongUserOrPassCounter + ", Date: " + new Date());
			if (wrongUserOrPassCounter >= maxWrongUserOrPass) {
				showCaptcha(0);
			}
			txtPassword.setText("");
		} else {
			PermissionUtil.removeWrongUserOrPassCounter();
			if (chkSaveUserAndPass.isChecked())
				setCookies(userName, password);
			else
				setCookies("", "");
			PermissionUtil.setCurrentUser(ozv);

			if (PermissionUtil.getCurrentPortal().getId().toString().equals(Constants.FootballSchoolPortalID)
					|| PermissionUtil.getCurrentPortal().getId().toString().equals(Constants.MembershipPortalID)) {
				SiasatBiz siasatBiz = appContext.getBean(SiasatBiz.class);
				if (siasatBiz.getAllSiasatHayePardakht(ozv, false).size() != 0) {
					PermissionUtil.putEmptyMojavezHayeKarbar();
					Map<String, Object> args = new HashMap<String, Object>();
					args.put("messageLabel", true);
					NavigationUtil.goTo(PardakhtPage.class, args);
					return;
				}
			}

			if (postPage == null)
				Executions.sendRedirect("/");
			else
				NavigationUtil.goTo(postPage, postPageArgs);

		}
	}

	@Listen("onClick = #btnNewCaptcha")
	public void generateNewCaptcha() {
		captcha = CaptchaUtil.generateNewCaptcha();
		imgCaptcha.setContent(captcha.getImage());
		txtCaptchaText.setValue("");
	}

	@Listen("onClick = #btnNewCaptcha1")
	public void generateNewCaptchaRegister() {
		captcha = CaptchaUtil.generateNewCaptcha();
		imgCaptcha1.setContent(captcha.getImage());
		txtCaptchaText1.setValue("");
	}

	@Listen("onClick = #btnQuickRegister; onOK = .savePage")
	public void register() throws WrongValueException, ParseException {

		String captchaText = txtCaptchaText1.getValue();
		if (captchaText.isEmpty()) {
			throw new WrongValueException(txtCaptchaText1, ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!captchaText.toLowerCase().equals(captcha.getAnswer().toLowerCase())) {
			txtCaptchaText1.setValue("");
			generateNewCaptcha();
			throw new WrongValueException(txtCaptchaText1, ErrorTypeEnum.WrongCaptcha.getFaName());
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
		String address = "";
		String postalCode = "";
		String email = "";
		String mobilePish = txtMobilePish.getValue().toString();
		String mobilePas = txtMobilePas.getValue().toString();
		String username = mobilePish + mobilePas;
		String password = mobilePish + mobilePas;
		Short securityQuestionId = 4;
		String securityAnswer = mobilePish + mobilePas;
		KodEntity keshvar = null;

		// Haghighi
		name = txtName.getValue();
		family = txtFamily.getValue();
		nationalCode = txtNationalCode.getValue().toString();
		username = nationalCode;
		// gender = ((cmbGender.getSelectedItem().getValue().equals("male")) ?
		// GenderEnum.Male : GenderEnum.Female);
		if (name.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtName, ErrorTypeEnum.RequiredField.getFaName());
		}
		if (family.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtFamily, ErrorTypeEnum.RequiredField.getFaName());
		}
		if (nationalCode.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtNationalCode, ErrorTypeEnum.InValidNationalCode.getFaName());
		}
		if (!nationalCodeIsValid(nationalCode)) {
			generateNewCaptcha();
			throw new WrongValueException(txtNationalCode, ErrorTypeEnum.InvalidNationalCode.getFaName());
		}

		if (!PermissionUtil.getCurrentPortal().getId().toString().equals(Constants.TicketPortalID)) {

		}

		if (mobilePish.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtMobilePish, ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePish);
			} catch (IllegalArgumentException e) {
				generateNewCaptcha();
				throw new WrongValueException(txtMobilePish, ErrorTypeEnum.InvalidValue.getFaName());
			}
		}
		if (mobilePas.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtMobilePas, ErrorTypeEnum.RequiredField.getFaName());
		} else {
			try {
				Integer.parseInt(mobilePas);
			} catch (IllegalArgumentException e) {
				generateNewCaptcha();
				throw new WrongValueException(txtMobilePas, ErrorTypeEnum.InvalidValue.getFaName());
			}
		}

		if (!nationalCode.isEmpty())
			if (ozvBiz.kodemelliIsRepeat(nationalCode)) {
				generateNewCaptcha();
				throw new WrongValueException(txtNationalCode, ErrorTypeEnum.RepeatedNationalCode.getFaName());
			}
		if (password.isEmpty()) {
			generateNewCaptcha();
			throw new WrongValueException(txtPassword, ErrorTypeEnum.RequiredField.getFaName());
		}

		KodEntity noeOzv = kodBiz.getKodEntity(Constants.NoeOzv, Constants.Haghighi);
		KodEntity jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard);
		// Haghighi
		noeOzv = kodBiz.getKodEntity(Constants.NoeOzv, Constants.Haghighi);
		if (gender.equals(GenderEnum.Male))
			jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard);
		if (gender.equals(GenderEnum.Female))
			jensiat = kodBiz.getKodEntity(Constants.Jensiat, Constants.Zan);

		AksEntity aksEntity = null;

		KodEntity vaziateKarbar;
		if (PermissionUtil.getCurrentPortal().getId().toString().equals(Constants.TicketPortalID)) {
			vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_TaeedShode);
		} else {
			vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_BarresiNashode);
		}

		PortalEntity portal = PermissionUtil.getCurrentPortal();

		try {
			OzvEntity ozv = ozvBiz.createOzv(null, aksEntity, noeOzv, passportNumber,
					(name.isEmpty()) ? internationalName : name, (family.isEmpty()) ? internationalFamily : family,
					legalName, nationalCode, registrationNumber, mobilePish + mobilePas, null, postalCode, address,
					email, jensiat, birthday, foundation, username, password,
					soaleAmniatiBiz.getSecurityQuestionById(securityQuestionId), ozvBiz.getHash(securityAnswer), null,
					null, vaziateKarbar, portal, keshvar);
			if (PermissionUtil.getCurrentUser() != null) {
				Messagebox.show("حساب کاربری شما با موفقیت ایجاد شد. لطفا  وارد سامانه شوید",
						"اتمام ایجاد حساب کاربری جدید", 1, "", new EventListener<Event>() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								Executions.sendRedirect("/");
							}
						});
			} else {
				PermissionUtil.setCurrentUser(ozv);
				Messagebox.show("حساب کاربری شما با موفقیت ایجاد شد. ضمنا شما از این لحظه وارد سامانه شده‌اید.",
						"اتمام ایجاد حساب کاربری جدید", 1, "", new EventListener<Event>() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								if (!PermissionUtil.getCurrentPortal().getId().toString()
										.equals(Constants.TicketPortalID)) {
									Executions.sendRedirect("/");
								} else {
									// NavigationUtil.goTo(DarkhasteKartPage.class);
									Executions.sendRedirect("/");
								}
							}
						});
				if (postPage != null)
					NavigationUtil.goTo(postPage, postPageArgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید", "خطا در ایجاد حساب کاربری جدید", 1, "");
			generateNewCaptcha();
		}
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

	@Listen("onClick = #aForgetPass")
	public void gotoForgetPassPage() {
		NavigationUtil.goTo(ForgotPassPage.class);
	}

	@Listen("onClick = #btnRegister")
	public void gotoRegisterPage() {
		NavigationUtil.goTo(RegisterPage.class);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/common/login.zul";
	}
}
