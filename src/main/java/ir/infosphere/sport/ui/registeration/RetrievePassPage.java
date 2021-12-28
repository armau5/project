package ir.infosphere.sport.ui.registeration;

import nl.captcha.Captcha;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.CaptchaUtil;

public class RetrievePassPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;
	private Captcha captcha;
	
	@Wire
	private Textbox txtUsername;
	@Wire
	private Row selectRow;
	@Wire
	private Row answerRow;
	@Wire 
	private Row captchaRow;
	@Wire
	private Row btnRow;
	@Wire
	private Image imgCaptcha;
	@Wire
	private Textbox txtSecurityAnswer;
	@Wire
	private Textbox txtCaptchaText;
	@Wire
	private Radio radioMail;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
		generateNewCaptcha();
	}
	
	@Listen("onClick = #btnNewCaptcha")
	public void generateNewCaptcha() {
		captcha = CaptchaUtil.generateNewCaptcha();
		imgCaptcha.setContent(captcha.getImage());
	}
	
	@Listen("onClick = #btnUsername")
	public void validateUsername() {
		String username = txtUsername.getValue();
		if (username.isEmpty())
			throw new WrongValueException(txtUsername, ErrorTypeEnum.RequiredField.getFaName());
		if(!ozvBiz.IsUsernameValid(username))
			throw new WrongValueException(txtUsername, ErrorTypeEnum.InvalidValue.getFaName());
		selectRow.setVisible(true);
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", username));
		OzvEntity ozv = ozvBiz.retrieveByCriteria(criteria).get(0);
		if(ozv.getPosteElectronik().equals(""))
			radioMail.setVisible(false);
	}
	
	@Listen("onCheck = #radioSecurityAnswer")
	public void showSecurityAnswer() {
		answerRow.setVisible(true);
		captchaRow.setVisible(true);
		btnRow.setVisible(true);
	}
	
	@Listen("onCheck = #radioMail")
	public void showEmail() {
		answerRow.setVisible(false);
		captchaRow.setVisible(false);
		btnRow.setVisible(false);
		String username = txtUsername.getValue();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", username));
        try {
        	//sendMail.send();
		} catch (Exception e) {
			Messagebox.show("ایمیل ارسال نشد!", "خطا در ارسال ایمیل", 1, "");
			e.printStackTrace();
		}
        Messagebox.show("ایمیلی حاوی پیوند فعال سازی به پست الکترونیک شما ارسال شد.",
				"", 1, "", new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						Executions.sendRedirect("/index.zul");
					}
				});
	}
	
	@Listen("onClick = #btnSecurityAnswer")
	public void newPass() {
		String username = txtUsername.getValue();
		String securityAnswer = txtSecurityAnswer.getValue();
		String captchaText = txtCaptchaText.getValue();
		if (securityAnswer.isEmpty()) {
			throw new WrongValueException(txtSecurityAnswer, ErrorTypeEnum.RequiredField.getFaName());
		}
		if (captchaText.isEmpty()) {
			throw new WrongValueException(txtCaptchaText, ErrorTypeEnum.RequiredField.getFaName());
		}
		if (!captchaText.toLowerCase().equals(captcha.getAnswer().toLowerCase())) {
			txtCaptchaText.setValue("");
			generateNewCaptcha();
			throw new WrongValueException(txtCaptchaText, ErrorTypeEnum.WrongCaptcha.getFaName());
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", username));
		OzvEntity ozv = ozvBiz.retrieveByCriteria(criteria).get(0);
		if (!ozvBiz.getHash(securityAnswer).equals(ozv.getHashePasokheSoaleAmniati()))
			throw new WrongValueException(txtSecurityAnswer, ErrorTypeEnum.InvalidValue.getFaName());
		Sessions.getCurrent().setAttribute("user", ozv);
		Executions.sendRedirect("/newPass.zul");
	}

	@Override
	public String getPageAddress() {
		return "/retrievePass.zul";
	}

}
