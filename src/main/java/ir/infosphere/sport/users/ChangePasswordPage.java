package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class ChangePasswordPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;

	@Wire
	private Textbox txtNewPassword, txtNewPasswordRepeat;

	@Wire
	private Label lblUserInfo;

	private OzvEntity ozv;
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_NewPass);
		return super.doBeforeCompose(page, parent, compInfo);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("changePasswordPageName");
		super.doAfterCompose(comp);
		ozvBiz = appContext.getBean(OzvBiz.class);
		final Execution execution = Executions.getCurrent();
		ozv = (OzvEntity) execution.getArg().get("ozv");
		lblUserInfo.setValue("تغییر کلمه عبور برای \"" + ozv.getName() + " "
				+ ozv.getFamil() + "\" به شماره " + ozv.getId()
				+ " با نام کاربری " + ozv.getNameKarbari());
	}

	@Listen("onClick = #btnChangePassword")
	public void changePassword() {
		String newPassword = txtNewPassword.getText();
		if (newPassword.isEmpty())
			throw new WrongValueException(txtNewPassword,
					"کلمه عبور جدید نباید خالی باشد");
		if (!newPassword.equals(txtNewPasswordRepeat.getText()))
			throw new WrongValueException(txtNewPasswordRepeat,
					"کلمه عبور جدید به درستی تکرار نشده است");
		ozv.setHasheRamzeOboor(ozvBiz.getHash(newPassword));
		ozvBiz.update(ozv);
		Messagebox.show("کلمه عبور با موفقیت تغییر کرد", "تغییر کلمه عبور",
				Messagebox.OK, Messagebox.INFORMATION,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						gotoUsersPage();
					}
				});
	}

	@Listen("onClick = #btnBack")
	public void gotoUsersPage() {
		NavigationUtil.goTo(UsersPage.class);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/changePassword.zul";
	}

}
