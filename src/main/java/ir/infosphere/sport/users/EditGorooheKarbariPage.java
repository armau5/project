package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.GorooheKarbariBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.HashMap;
import java.util.Map;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class EditGorooheKarbariPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private GorooheKarbariBiz gorooheKarbariBiz;
	private GorooheKarbariEntity gorooh;

	@Wire
	private Textbox txtName;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_Group_Edit);
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName="ویرایش گروه کاربری";
		super.doAfterCompose(comp);
		gorooheKarbariBiz = (GorooheKarbariBiz) appContext
				.getBean("gorooheKarbariBiz");
		final Execution execution = Executions.getCurrent();
		gorooh = (GorooheKarbariEntity) execution.getArg().get("gorooh");
		txtName.setValue(gorooh.getNam());
	}

	@Listen("onClick = #btnSave")
	public void save() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Edit))
		{
		String name = txtName.getValue();

		if (name.isEmpty())
			throw new WrongValueException(txtName,
					ErrorTypeEnum.RequiredField.getFaName());
		else if (gorooheKarbariBiz.checkName(name) != 0
				&& gorooheKarbariBiz.checkName(name) != gorooh.getId())
			throw new WrongValueException(txtName,
					ErrorTypeEnum.RepeatedName.getFaName());

		try {
			gorooh.setNam(name);
			gorooheKarbariBiz.update(gorooh);
			Messagebox.show("اطلاعات گروه با موفقیت اصلاح شد",
				"اتمام اصلاح اطلاعات گروه", 1, "",
				new EventListener<Event>() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Map<String, Object> args = new HashMap<String, Object>();
						NavigationUtil.goTo(GoroheKarbariPage.class, args);
					}
				});

		} catch (Exception e) {
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید",
					"خطا در اصلاح اطلاعات گروه کاربری", 1, "");
			e.printStackTrace();
		}
		}
	}
	
	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(GoroheKarbariPage.class, args);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/editGorooheKarbari.zul";
	}

}
