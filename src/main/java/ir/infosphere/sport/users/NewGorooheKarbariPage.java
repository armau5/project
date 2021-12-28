package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.GorooheKarbariBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class NewGorooheKarbariPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private KodBiz kodBiz;
	private GorooheKarbariBiz gorooheKarbariBiz;

	@Wire
	private Textbox txtName;
	@Wire
	private Combobox cmbNoeGorooh;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_Group_New);
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("ADDGoroohPageName");
		super.doAfterCompose(comp);
		kodBiz = (KodBiz) appContext.getBean("kodBiz");
		gorooheKarbariBiz = (GorooheKarbariBiz) appContext.getBean("gorooheKarbariBiz");
	}
	
	@Listen("onClick = #btnSave")
	public void save() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_New))
		{
		String name = txtName.getValue();
		String noeGoroohStr = cmbNoeGorooh.getValue();
		KodEntity noeGorooh;

		if (name.isEmpty())
			throw new WrongValueException(txtName, ErrorTypeEnum.RequiredField.getFaName());
		else if (gorooheKarbariBiz.checkName(name) != 0)
			throw new WrongValueException(txtName, ErrorTypeEnum.RepeatedName.getFaName());
		if (noeGoroohStr.isEmpty())
			throw new WrongValueException(cmbNoeGorooh, ErrorTypeEnum.RequiredField.getFaName());
		else
			noeGorooh = kodBiz.getKodEntity("نوع گروه کاربری", noeGoroohStr);

		try {
			GorooheKarbariEntity entity = new GorooheKarbariEntity();
			entity.setNam(name);
			entity.setNoeGorooh(noeGorooh);
			entity.setGheyreFaal(false);
			entity.setPortal(PermissionUtil.getCurrentPortal());
			gorooheKarbariBiz.create(entity);
			Messagebox.show("گروه جدید با موفقیت ایجاد شد", "اتمام ایجاد گروه جدید", 1, "",
				new EventListener<Event>() {
	
					@Override
					public void onEvent(Event arg0) throws Exception {
						Map<String, Object> args = new HashMap<String, Object>();
						NavigationUtil.goTo(GoroheKarbariPage.class, args);
					}
				});

		} catch (Exception e) {
			Messagebox.show("پس از بررسی اطلاعات وارد شده، مجددا تلاش کنید", "خطا در ایجاد گروه کاربری جدید", 1, "");
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
		return "/WEB-INF/inner-pages/users/newGorooheKarbari.zul";
	}

}
