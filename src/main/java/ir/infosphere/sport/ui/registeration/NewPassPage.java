package ir.infosphere.sport.ui.registeration;

import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class NewPassPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;
	private OzvEntity ozv;

	@Wire
	private Textbox txtNewPass;
	@Wire
	private Textbox txtNewPassRepeat;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("newPassPageName");
		super.doAfterCompose(comp);
		ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
		final Execution execution = Executions.getCurrent();
		ozv = (OzvEntity) execution.getArg().get("user");
	}

	@Listen("onClick = #btnSave")
	public void save() {
		String newPass = txtNewPass.getText();
		String newPassRepeat = txtNewPassRepeat.getText();
		if (newPass.isEmpty())
			throw new WrongValueException(txtNewPass, ErrorTypeEnum.RequiredField.getFaName());
		if (newPassRepeat.isEmpty())
			throw new WrongValueException(txtNewPassRepeat, ErrorTypeEnum.RequiredField.getFaName());
		if (!newPass.equals(newPassRepeat))
			throw new WrongValueException(txtNewPassRepeat, ErrorTypeEnum.InvalidRepeatPassword.getFaName());
		ozv.setHasheRamzeOboor(ozvBiz.getHash(newPass));
		ozvBiz.update(ozv);
		Messagebox.show("کلمه عبور شما با موفقیت تغییر کرد.", "اتمام تغییر کلمه عبور", 1, "",
			new EventListener<Event>() {
				@Override
				public void onEvent(Event arg0) throws Exception {
					Executions.sendRedirect("/");
				}
			});
	}

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(ForgotPassPage.class, args);
	}
	
	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/registeration/newPass.zul";
	}

}
