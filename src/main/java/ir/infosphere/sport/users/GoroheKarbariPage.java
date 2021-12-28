package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.GorooheKarbariBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataIntegrityViolationException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class GoroheKarbariPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private GorooheKarbariBiz gorooheKarbariBiz;
	private KodBiz kodBiz;

	@Wire
	private Listbox listbox;
	@Wire
	private Combobox cmbActivation;
	@Wire
	private Textbox txtName;
	@Wire
	private Combobox cmbNoeGorooh;
	@Wire
	private Button btnNew, btnDelete, btnEdit, btnActivation, btnMojavez;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_Group_Package);
		return super.doBeforeCompose(page, parent, compInfo);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("usersGroupPageName");
		super.doAfterCompose(comp);
		gorooheKarbariBiz = (GorooheKarbariBiz) appContext
				.getBean("gorooheKarbariBiz");
		kodBiz = (KodBiz) appContext.getBean("kodBiz");

		cmbActivation.setSelectedIndex(2);

		List<GorooheKarbariEntity> gorooheKarbari = gorooheKarbariBiz
				.retrieveAll();
		setGroups(gorooheKarbari);

		checkAuthentication();
		
		selectItemChanged();
	}

	private void checkAuthentication() {
		PermissionUtil.CheckButton(btnNew, PermissionEnum.USER_Group_New);
		PermissionUtil.CheckButton(btnEdit, PermissionEnum.USER_Group_Edit);
		PermissionUtil.CheckButton(btnDelete, PermissionEnum.USER_Group_Delete);
		PermissionUtil.CheckButton(btnActivation, PermissionEnum.USER_Group_Activation);
		PermissionUtil.CheckButton(btnMojavez, PermissionEnum.USER_Group_Permission);
	}
	
	@Listen("onSelect = #listbox")
	public void selectItemChanged() {
		int size = listbox.getSelectedItems().size();
		if (size == 0) {
			btnNew.setDisabled(false);
			btnDelete.setDisabled(true);
			btnActivation.setDisabled(true);
			btnEdit.setDisabled(true);
			btnMojavez.setDisabled(true);

		} else if (size == 1) {
			btnNew.setDisabled(false);
			btnDelete.setDisabled(false);
			btnActivation.setDisabled(false);
			btnEdit.setDisabled(false);
			btnMojavez.setDisabled(false);
		} else {
			btnNew.setDisabled(false);
			btnDelete.setDisabled(false);
			btnActivation.setDisabled(false);
			btnEdit.setDisabled(true);
			btnMojavez.setDisabled(true);
		}

	}

	private void setGroups(List<GorooheKarbariEntity> groups) {
		listbox.getItems().clear();

		for (int i = 0; i < groups.size(); i++) {
			Listitem item = new Listitem();
			// ///////set Id
			Listcell idCell = new Listcell();
			Label idlbl = new Label();
			idlbl.setValue(groups.get(i).getId().toString());
			idlbl.setVisible(false);
			idCell.appendChild(idlbl);
			item.appendChild(idCell);
			// ///////set Name
			Listcell nameCell = new Listcell();
			nameCell.setLabel(groups.get(i).getNam());
			item.appendChild(nameCell);
			// ///////set noe gorooh
			Listcell noeGoroohCell = new Listcell();
			noeGoroohCell.setLabel(groups.get(i).getNoeGorooh().getMeghdar());
			item.appendChild(noeGoroohCell);
			// ///////set Activation
			if (groups.get(i).getGheyreFaal())
				item.setStyle("background-color:rgb(150,150,150)");

			listbox.appendChild(item);

		}
	}

	@Listen("onClick = #btnNew")
	public void newGroup() {
		{
			if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_New)) {
				Map<String, Object> args = new HashMap<String, Object>();
				NavigationUtil.goTo(NewGorooheKarbariPage.class, args);
			}
		}
	}

	@Listen("onClick = #btnDelete")
	public void delete() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Delete)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else {
				Messagebox.show("آیا برای حذف این گروه اطمینان دارید؟", "",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
						new EventListener<Event>() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getName().equals("onYes")) {
									for (Listitem item : listbox
											.getSelectedItems()) {
										Short id = Short.valueOf(((Label) item
												.getFirstChild()
												.getFirstChild()).getValue());
										try {
											gorooheKarbariBiz.delete(id);
											Map<String, Object> args = new HashMap<String, Object>();
											NavigationUtil.goTo(
													GoroheKarbariPage.class,
													args);
										} catch (DataIntegrityViolationException e) {
											Messagebox.show(
													gorooheKarbariBiz
															.retrieveById(id)
															.getNam()
															+ " : "
															+ Labels.getLabel("canNotDelete"),
													Labels.getLabel("errorTitle"),
													Messagebox.OK,
													Messagebox.ERROR);
										}
									}
								}
							}
						});
			}
		}
	}

	@Listen("onClick = #btnEdit")
	public void edit() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Edit)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else if (listbox.getSelectedItems().size() > 1)
				Messagebox.show(Labels.getLabel("pleaseSelectJustOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else {
				Short id = Short.valueOf(((Label) listbox.getSelectedItem()
						.getFirstChild().getFirstChild()).getValue());
				Map<String, Object> args = new HashMap<String, Object>();
				args.put("gorooh", gorooheKarbariBiz.retrieveById(id));
				NavigationUtil.goTo(EditGorooheKarbariPage.class, args);
			}
		}
	}

	@Listen("onClick = #btnActivation")
	public void activation() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Activation)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else {
				for (Listitem item : listbox.getSelectedItems()) {
					Short id = Short.valueOf(((Label) item.getFirstChild()
							.getFirstChild()).getValue());
					GorooheKarbariEntity group = gorooheKarbariBiz
							.retrieveById(id);
					group.setGheyreFaal(!group.getGheyreFaal());
					gorooheKarbariBiz.update(group);
				}
				Map<String, Object> args = new HashMap<String, Object>();
				NavigationUtil.goTo(GoroheKarbariPage.class, args);
			}
		}
	}

	@Listen("onClick = #btnMojavez")
	public void mojavez() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Permission)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else if (listbox.getSelectedItems().size() > 1)
				Messagebox.show(Labels.getLabel("pleaseSelectJustOne"),
						Labels.getLabel("errorTitle"), Messagebox.OK,
						Messagebox.ERROR);
			else {
				Short id = Short.valueOf(((Label) listbox.getSelectedItem()
						.getFirstChild().getFirstChild()).getValue());
				GorooheKarbariEntity gorooh = gorooheKarbariBiz
						.retrieveById(id);
				if (gorooh.getNoeGorooh().equals(
						kodBiz.getKodEntity("نوع گروه کاربری", "گروه مجوز"))) {
					Map<String, Object> args = new HashMap<String, Object>();
					args.put("gorooh", gorooh);
					NavigationUtil.goTo(MojavezPage.class, args);
				} else {
					Messagebox.show(Labels.getLabel("pleaseSelectValidGroup"),
							Labels.getLabel("errorTitle"), Messagebox.OK,
							Messagebox.ERROR);
				}
			}
		}
	}

	@Listen("onClick = #btnFilter; onOK=#txtName")
	public void filter() {
		String name = txtName.getValue();
		String noeGorooh = cmbNoeGorooh.getValue();

		DetachedCriteria criteria = DetachedCriteria
				.forClass(GorooheKarbariEntity.class);

		if (!name.isEmpty())
			criteria.add(Restrictions.like("nam", name));
		if (!noeGorooh.isEmpty())
			criteria.add(Restrictions.eq("noeGorooh",
					kodBiz.getKodEntity("نوع گروه کاربری", noeGorooh)));

		if (cmbActivation.getSelectedIndex() == 0)
			criteria.add(Restrictions.eq("gheyreFaal", false));
		else if (cmbActivation.getSelectedIndex() == 1)
			criteria.add(Restrictions.eq("gheyreFaal", true));

		List<GorooheKarbariEntity> groups = gorooheKarbariBiz
				.retrieveByCriteria(criteria);
		setGroups(groups);
	}

	@Listen("onClick = #btnLaghv")
	public void laghveFilter() {
		cmbActivation.setSelectedIndex(2);
		txtName.setValue("");
		cmbNoeGorooh.setValue("");
		List<GorooheKarbariEntity> groups = gorooheKarbariBiz.retrieveAll();
		setGroups(groups);
	}

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(UsersPage.class, args);
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/gorooheKarbari.zul";
	}

}
