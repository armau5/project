package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.DarkhastKartBiz;
import ir.infosphere.sport.biz.GorooheKarbariBiz;
import ir.infosphere.sport.biz.GoroohhayeKarbarBiz;
import ir.infosphere.sport.biz.KartBiz;
import ir.infosphere.sport.biz.KodBiz;
import ir.infosphere.sport.biz.MadreseBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.biz.OzveMadreseBiz;
import ir.infosphere.sport.biz.OzveTimBiz;
import ir.infosphere.sport.biz.ReshteyeVarzeshiBiz;
import ir.infosphere.sport.biz.TimBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveMadreseEntity;
import ir.infosphere.sport.entity.PardakhteDarkhasteKartEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.ui.common.DarkhasteKartGorohiPage;
import ir.infosphere.sport.ui.registeration.RegisterPage;
import ir.infosphere.sport.ui.siasat.PardakhtGorohiPage;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;
import ir.infosphere.sport.util.ShamsiDate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataIntegrityViolationException;
import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class UsersPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private OzvBiz ozvBiz;
	private KodBiz kodBiz;
	private TimBiz timBiz;
	private DarkhastKartBiz darkhastKartBiz;
	private KartBiz kartBiz;
	private ReshteyeVarzeshiBiz reshteyeVarzeshiBiz;
	private GorooheKarbariBiz gorooheKarbariBiz;
	private GoroohhayeKarbarBiz goroohhayeKarbarBiz;
	private MadreseBiz madreseBiz;
	private OzveMadreseBiz ozveMadreseBiz;
	private OzveTimBiz ozveTimBiz;

	@Wire
	private Listbox listbox;
	@Wire
	private Textbox txtName, txtFamily, txtNationalCode, txtUsername, txtDateStart, txtDateEnd, txtChangeDateStart, txtChangeDateEnd;
	@Wire
	private Combobox cmbActivation, cmbUsertype, cmbReshte, cmbTim, cmbGorooh, cmbAks;
	@Wire
	private Paging paging;
	@Wire
	private Window winMain;
	@Wire
	private Button btnActivation, btnAks, btnDelete, btnNew, btnGorooh, btnTakhsiseGorooh, btnChangePassword, btnEditUser, btnDarkhasteKart,
			btnDarkhasteKartRaygan, btnPayOzviat;
	private Integer pageNumber;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("usersPageName");
		super.doAfterCompose(comp);

		PermissionUtil.CheckPage(PermissionEnum.USER_Package);

		ozvBiz = appContext.getBean(OzvBiz.class);
		kodBiz = appContext.getBean(KodBiz.class);
		timBiz = appContext.getBean(TimBiz.class);
		kartBiz = appContext.getBean(KartBiz.class);
		goroohhayeKarbarBiz = appContext.getBean(GoroohhayeKarbarBiz.class);
		reshteyeVarzeshiBiz = appContext.getBean(ReshteyeVarzeshiBiz.class);
		gorooheKarbariBiz = appContext.getBean(GorooheKarbariBiz.class);
		darkhastKartBiz = appContext.getBean(DarkhastKartBiz.class);
		madreseBiz = appContext.getBean(MadreseBiz.class);
		ozveMadreseBiz = appContext.getBean(OzveMadreseBiz.class);
		ozveTimBiz = appContext.getBean(OzveTimBiz.class);

		fillPageObjects();
		System.out.println("Fill Berfore Filter");
		filter();
		System.out.println("Fill After Filter");
		selectItemChanged();

		checkAuthentication();
		System.out.println("Fill After Check");
	}

	private void checkAuthentication() {
		PermissionUtil.CheckButton(btnNew, PermissionEnum.USER_New);
		PermissionUtil.CheckButton(btnAks, PermissionEnum.USER_Approve);
		PermissionUtil.CheckButton(btnDelete, PermissionEnum.USER_Delete);
		PermissionUtil.CheckButton(btnActivation, PermissionEnum.USER_Activation);
		PermissionUtil.CheckButton(btnTakhsiseGorooh, PermissionEnum.USER_AddGroup);
		PermissionUtil.CheckButton(btnGorooh, PermissionEnum.USER_Group_Package);
		PermissionUtil.CheckButton(btnChangePassword, PermissionEnum.USER_NewPass);
		PermissionUtil.CheckButton(btnEditUser, PermissionEnum.USER_EditAdmin);
		PermissionUtil.CheckButton(btnDarkhasteKart, PermissionEnum.USER_DarkhasteKartGorohi);
		PermissionUtil.CheckButton(btnPayOzviat, PermissionEnum.USER_PardakhtHagheOzviatGorohi);
		PermissionUtil.CheckButton(btnDarkhasteKartRaygan, PermissionEnum.USER_DarkhasteKartGorohiRaygan);
	}

	private void fillPageObjects() {
		String userName = (String) Sessions.getCurrent().getAttribute(pageName + "txtUsername");
		String tarikhShoro = (String) Sessions.getCurrent().getAttribute(pageName + "txtTarikhShoro");
		String tarikhPayan = (String) Sessions.getCurrent().getAttribute(pageName + "txtTarikhPayan");
		String tarikhShoroTaghir = (String) Sessions.getCurrent().getAttribute(pageName + "txtChangeDateStart");
		String tarikhPayanTaghir = (String) Sessions.getCurrent().getAttribute(pageName + "txtChangeDateEnd");
		String name = (String) Sessions.getCurrent().getAttribute(pageName + "txtName");
		String family = (String) Sessions.getCurrent().getAttribute(pageName + "txtFamily");
		String nationalCode = (String) Sessions.getCurrent().getAttribute(pageName + "txtNationalCode");
		Integer userType = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbUsertype");
		Integer reshte = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbReshte");
		Integer tim = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbTim");
		Integer grooh = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbGorooh");
		Integer active = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbActivation");
		Integer aks = (Integer) Sessions.getCurrent().getAttribute(pageName + "cmbAks");

		if (userName != null)
			txtUsername.setValue(userName);
		if (name != null)
			txtName.setValue(name);
		if (family != null)
			txtFamily.setValue(family);
		if (nationalCode != null)
			txtNationalCode.setValue(nationalCode);
		if (userType != null)
			cmbUsertype.setSelectedIndex(userType);
		if (tarikhShoro != null)
			txtDateStart.setValue(tarikhShoro);
		if (tarikhPayan != null)
			txtDateEnd.setValue(tarikhPayan);
		if (tarikhShoroTaghir != null)
			txtChangeDateStart.setValue(tarikhShoroTaghir);
		if (tarikhPayanTaghir != null)
			txtChangeDateEnd.setValue(tarikhPayanTaghir);
		System.out.println("Fill Berfore Reshte");
		fillReshteyeVarzeshi();
		fillGrooh();
		System.out.println("Fill After Reshte");

		if (reshte != null) {
			cmbReshte.setSelectedIndex(reshte);
			fillTim();
		}
		if (tim != null)
			cmbTim.setSelectedIndex(tim);
		if (grooh != null)
			cmbGorooh.setSelectedIndex(grooh);
		if (active != null)
			cmbActivation.setSelectedIndex(active);
		else
			cmbActivation.setSelectedIndex(0);
		if (aks != null)
			cmbAks.setSelectedIndex(aks);
		else
			cmbAks.setSelectedIndex(3);
		pageNumber = (Integer) Sessions.getCurrent().getAttribute(pageName + "paging");
		if (pageNumber == null)
			pageNumber = 0;
	}

	private Popup setToolTip(OzvEntity ozv, Image img) {
		Popup popup = new Popup();
		popup.setWidth("210px");
		Vlayout vlayout = new Vlayout();
		Image imgAks = new Image();
		// imgAks.setSrc(img.getSrc());
		// System.out.println(img.getSrc());
		try {
			AImage aks = new AImage(ozv.getAks().getNameFileAks());
			imgAks.setContent(aks);
		} catch (Exception e) {
			imgAks.setSrc(Labels.getLabel("notFoundUserPictureAddress"));
		}
		imgAks.setWidth("200px");
		vlayout.appendChild(imgAks);
		Label namelbl = new Label("نام: " + ozv.getName());
		vlayout.appendChild(namelbl);
		Label famillbl = new Label("فامیل: " + ozv.getFamil());
		vlayout.appendChild(famillbl);
		Label userlbl = new Label("نام کاربری: " + ozv.getNameKarbari());
		vlayout.appendChild(userlbl);
		Label genderlbl = new Label("جنسیت: " + ozv.getJensiat().getMeghdar());
		vlayout.appendChild(genderlbl);
		Label kodeMelilbl = new Label("کد ملی: " + ozv.getKodeMeli());
		vlayout.appendChild(kodeMelilbl);
		Label hamrahlbl = new Label("شماره همراه: " + ozv.getShomareyeHamrah());
		vlayout.appendChild(hamrahlbl);
		Label tellbl = new Label("شماره ثابت: " + ozv.getShomareyeSabet());
		vlayout.appendChild(tellbl);
		if (ozv.getNahiye()== null)
		{
			Label ostanlbl = new Label("استان: " + " - ");
			vlayout.appendChild(ostanlbl);
		}
		else
		{
			Label ostanlbl = new Label("استان: " + ozv.getNahiye().getValed().getValed().getNameNahiye());
			vlayout.appendChild(ostanlbl);
		}
		
		Label shahrestanlbl = new Label("شهرستان: " + (ozv.getNahiye() != null ? ozv.getNahiye().getValed().getNameNahiye() : ""));
		vlayout.appendChild(shahrestanlbl);
		Label shahrlbl = new Label("شهر: " + (ozv.getNahiye() != null ? ozv.getNahiye().getNameNahiye() : ""));
		vlayout.appendChild(shahrlbl);
		Label kodePostilbl = new Label("کد پستی: " + ozv.getKodePosti());
		vlayout.appendChild(kodePostilbl);
		Label adreslbl = new Label("آدرس: " + ozv.getAdres());
		vlayout.appendChild(adreslbl);
		Label maillbl = new Label("پست الکترونیکی: " + ozv.getPosteElectronik());
		vlayout.appendChild(maillbl);
		Label tavalodlbl;
		try {
			tavalodlbl = new Label("تاریخ تولد: " + (ozv.getTarikheTavallod() != null ? ShamsiDate.get_Fa_Date(ozv.getTarikheTavallod()) : ""));
		} catch (ParseException e) {
			tavalodlbl = new Label("تاریخ تولد: " + "");
		}
		vlayout.appendChild(tavalodlbl);
		Label taeedeAkslbl = new Label("تایید کاربر: " + (ozv.getTaeedeAks() != null ? ozv.getTaeedeAks().getMeghdar() : ""));
		vlayout.appendChild(taeedeAkslbl);
		if (ozv.getTaeedeAks() != null && ozv.getDalileRadkardan() != null) {
			if (ozv.getTaeedeAks().getMeghdar().equals(Constants.VaziateKarbar_RadShode)) {
				Label rad = new Label("دلیل رد: " + ozv.getDalileRadkardan());
				vlayout.appendChild(rad);
			}
		}
		Label portallbl = new Label("پورتال: " + ozv.getPortal().getNameSazman());
		vlayout.appendChild(portallbl);
		popup.appendChild(vlayout);
		winMain.appendChild(popup);
		return popup;
	}

	private void addRowToListbox(Listitem item, OzvEntity ozv) {
		Listcell idCell = new Listcell();
		Label idlbl = new Label();
		idlbl.setValue(ozv.getId().toString());
		idlbl.setVisible(false);
		idCell.appendChild(idlbl);
		item.appendChild(idCell);
		// ////////set aks
		System.out.println("set aks");
		Listcell imgCell = new Listcell();
		Image imgAks = new Image();
		try {
			AImage aks = new AImage(ozv.getAks().getNameFileAks());
			imgAks.setContent(aks);
		} catch (IOException | NullPointerException e) {
			imgAks.setSrc(Labels.getLabel("notFoundUserPictureAddress"));
		}
		imgAks.setHeight("30px");
		imgCell.appendChild(imgAks);
		item.appendChild(imgCell);
		// ///////set Name
		System.out.println("set Name");
		Listcell nameCell = new Listcell();
		nameCell.setLabel(ozv.getName());
		item.appendChild(nameCell);
		// ///////set Famil
		Listcell familCell = new Listcell();
		familCell.setLabel(ozv.getFamil());
		item.appendChild(familCell);
		// ///////set Username
		Listcell usernameCell = new Listcell();
		usernameCell.setLabel(ozv.getNameKarbari());
		item.appendChild(usernameCell);
		// ///////set Jensiat
		Listcell jensiatCell = new Listcell();
		jensiatCell.setLabel(ozv.getJensiat().getMeghdar());
		item.appendChild(jensiatCell);
		// ///////set Noe karbar
		Listcell noekarbarCell = new Listcell();
		noekarbarCell.setLabel(ozv.getNoeOzv().getMeghdar());
		item.appendChild(noekarbarCell);
		// ///////set Ostan
		Listcell ostanCell = new Listcell();
		ostanCell.setLabel(ozv.getNahiye() == null ? " - " : 
					ozv.getNahiye().getValed().getValed().getNameNahiye());
		item.appendChild(ostanCell);
		 ///////set Shahrestan
		Listcell shahrestanCell = new Listcell();
		shahrestanCell.setLabel(ozv.getNahiye() == null ? " - " :
			ozv.getNahiye().getValed().getNameNahiye());
		item.appendChild(shahrestanCell);
		 ///////set shahr
		Listcell shahrCell = new Listcell();
		shahrCell.setLabel(ozv.getNahiye() == null ? " - " :
			ozv.getNahiye().getNameNahiye());
		item.appendChild(shahrCell);
		// ///////set reshte
		Listcell reshteCell = new Listcell();
		reshteCell.setLabel(ozv.getReshteyeVarzeshiyeMoredeAlaghe() != null ? ozv.getReshteyeVarzeshiyeMoredeAlaghe().getNameReshteyeVarzeshi() : "");
		item.appendChild(reshteCell);
		// ///////set tim
		Listcell timCell = new Listcell();
		timCell.setLabel(ozv.getTimeMoredeAlaghe() != null ? ozv.getTimeMoredeAlaghe().getNameTim() : "");
		item.appendChild(timCell);
		// ///////set shomare
		Listcell shomareCell = new Listcell();
		if (ozv.getShomareyeHamrah() != "")
			shomareCell.setLabel(FormatUtil.convertToPersianNumbersWithoutChangeOtherText(ozv.getShomareyeHamrah()));
		else
			shomareCell.setLabel("");
		item.appendChild(shomareCell);
		// ///////set mail
		Listcell mailCell = new Listcell();
		mailCell.setLabel(ozv.getPosteElectronik());
		item.appendChild(mailCell);
		// ///////set taeede aks
		Listcell aksCell = new Listcell();
		aksCell.setLabel(ozv.getTaeedeAks() != null ? ozv.getTaeedeAks().getMeghdar() : "");
		item.appendChild(aksCell);
		// ///////set Activation
		if (ozv.getGheyreFaal())
			item.setStyle("background-color:rgb(150,150,150)");
		item.setTooltip(setToolTip(ozv, imgAks));
		System.out.println("ROW " + item.toString());
	}

	private void setUsers(List<OzvEntity> list) throws ParseException {
		listbox.getItems().clear();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("USER " + i + " " + list.size());
			OzvEntity ozv = list.get(i);
			System.out.println(ozv.getFamil());
			Listitem item = new Listitem();
			addRowToListbox(item, ozv);
			listbox.appendChild(item);
		}
	}

	@Listen("onSelect = #listbox")
	public void selectItemChanged() {
		int size = listbox.getSelectedItems().size();
		if (size == 0) {
			btnAks.setDisabled(true);
			btnDelete.setDisabled(true);
			btnActivation.setDisabled(true);
			btnTakhsiseGorooh.setDisabled(true);
			btnDarkhasteKart.setDisabled(true);
			btnDarkhasteKartRaygan.setDisabled(true);
			btnPayOzviat.setDisabled(true);
			btnChangePassword.setDisabled(true);
			btnEditUser.setDisabled(true);
		} else if (size == 1) {
			btnAks.setDisabled(false);
			btnDelete.setDisabled(false);
			btnActivation.setDisabled(false);
			btnTakhsiseGorooh.setDisabled(false);
			btnDarkhasteKart.setDisabled(false);
			btnDarkhasteKartRaygan.setDisabled(false);
			btnPayOzviat.setDisabled(false);
			btnChangePassword.setDisabled(false);
			btnEditUser.setDisabled(false);
		} else {
			btnAks.setDisabled(true);
			btnDelete.setDisabled(false);
			btnActivation.setDisabled(false);
			btnTakhsiseGorooh.setDisabled(false);
			btnDarkhasteKart.setDisabled(false);
			btnDarkhasteKartRaygan.setDisabled(false);
			btnPayOzviat.setDisabled(false);
			btnChangePassword.setDisabled(true);
			btnEditUser.setDisabled(true);
		}

	}

	@Listen("onClick = #btnNew")
	public void newUser() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_New)) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("returnUrl", "UsersPage");
			NavigationUtil.goTo(RegisterPage.class, args);
		}
	}

	@Listen("onClick = #btnDelete")
	public void delete() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Delete)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				Messagebox.show(Labels.getLabel("deleteConfirmation"), Labels.getLabel("questionTitle"), Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener<Event>() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getName().equals("onYes")) {
									boolean flag = true;
									for (Listitem item : listbox.getSelectedItems()) {
										Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
										try {
											ozvBiz.delete(id);
										} catch (DataIntegrityViolationException e) {
											flag = false;
											Messagebox.show(ozvBiz.retrieveById(id).getNameKarbari() + " : " + Labels.getLabel("canNotDelete"),
													Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
										}
									}
									if (flag) {
										Map<String, Object> args = new HashMap<String, Object>();
										NavigationUtil.goTo(UsersPage.class, args);
									}
								}
							}
						});
			}
		}
	}

	@Listen("onClick = #btnActivation")
	public void activation() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Activation)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				for (Listitem item : listbox.getSelectedItems()) {
					Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
					OzvEntity ozv = ozvBiz.retrieveById(id);
					ozv.setGheyreFaal(!ozv.getGheyreFaal());
					ozvBiz.update(ozv);
				}
				Map<String, Object> args = new HashMap<String, Object>();
				NavigationUtil.goTo(UsersPage.class, args);
			}
		}
	}

	private DetachedCriteria getDefaultCriteria() {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		// criteria.add(Restrictions.eq("portal",
		// PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.in("portal", PermissionUtil.getCurrentUsersPortals()));
		criteria.add(Restrictions.not(Restrictions.like("kodeMeli", "-")));
		if (PermissionUtil.Check(PermissionEnum.USER_Portal_Access)) {
			return criteria;
		} else if (PermissionUtil.Check(PermissionEnum.USER_Ostan_Access)) {
			criteria.createAlias("nahiye", "shahr", Criteria.LEFT_JOIN);
			criteria.createAlias("shahr.valed", "shahrestan", Criteria.LEFT_JOIN);
			criteria.createAlias("shahrestan.valed", "ostan", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("ostan.id", PermissionUtil.getCurrentUser().getNahiye().getValed().getValed().getId()));
		} else if (PermissionUtil.Check(PermissionEnum.USER_Madrese_Access)) {
			List<Integer> listOzv = new ArrayList<Integer>();
			List<MadreseEntity> listMadares = madreseBiz.getAllMadreseByOzveMasool(PermissionUtil.getCurrentUser());
			if (listMadares != null && listMadares.size() > 0) {
				for (MadreseEntity madrese : listMadares) {
					List<OzveMadreseEntity> listAzayeMadrese = ozveMadreseBiz.getAllOzvHayeHazerByMadrese(madrese);
					if (listAzayeMadrese != null && listAzayeMadrese.size() > 0) {
						for (OzveMadreseEntity ozveMadrese : listAzayeMadrese) {
							listOzv.add(ozveMadrese.getOzveMadrese().getId());
						}
					}
				}
			}
			if (listOzv != null && listOzv.size() > 0)
				criteria.add(Restrictions.in("id", listOzv));
			else
				criteria.add(Restrictions.isNull("id"));
		} else {
			criteria.add(Restrictions.isNull("id"));
		}

		return criteria;
	}

	public void filter() throws ParseException {
		System.out.println("FILTER START");
		String username = txtUsername.getValue();
		String name = txtName.getValue();
		String family = txtFamily.getValue();
		String nationalCode = txtNationalCode.getValue();
		String type = cmbUsertype.getValue();
		String reshte = cmbReshte.getValue();
		String tim = cmbTim.getValue();
		GorooheKarbariEntity gorooh = null;

		String tarikhShoro = txtDateStart.getValue();
		String tarikhPayan = txtDateEnd.getValue();
		String tarikhShoroTaghir = txtChangeDateStart.getValue();
		String tarikhPayanTaghir = txtChangeDateEnd.getValue();

		Sessions.getCurrent().setAttribute(pageName + "txtUsername", username);
		Sessions.getCurrent().setAttribute(pageName + "txtName", name);
		Sessions.getCurrent().setAttribute(pageName + "txtFamily", family);
		Sessions.getCurrent().setAttribute(pageName + "txtNationalCode", nationalCode);
		Sessions.getCurrent().setAttribute(pageName + "cmbUsertype", cmbUsertype.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbReshte", cmbReshte.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbTim", cmbTim.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbGorooh", cmbGorooh.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbActivation", cmbActivation.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbAks", cmbAks.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "cmbGooroheKarbari", cmbGorooh.getSelectedIndex());
		Sessions.getCurrent().setAttribute(pageName + "txtTarikhShoro", tarikhShoro);
		Sessions.getCurrent().setAttribute(pageName + "txtTarikhPayan", tarikhPayan);
		Sessions.getCurrent().setAttribute(pageName + "txtChangeDateStart", tarikhShoroTaghir);
		Sessions.getCurrent().setAttribute(pageName + "txtChangeDateEnd", tarikhPayanTaghir);

		System.out.println("criteria START");
		DetachedCriteria criteria = getDefaultCriteria();

		if (!username.isEmpty())
			criteria.add(Restrictions.ilike("nameKarbari", username));
		if (!name.isEmpty())
			criteria.add(Restrictions.ilike("name", "%" + name + "%"));
		if (!family.isEmpty())
			criteria.add(Restrictions.ilike("famil", "%" + family + "%"));
		if (!nationalCode.isEmpty())
			criteria.add(Restrictions.like("kodeMeli", nationalCode));

		if (!tarikhShoro.isEmpty())
			criteria.add(Restrictions.ge("tarikheTavallod", ShamsiDate.get_En_Date(tarikhShoro)));
		if (!tarikhPayan.isEmpty())
			criteria.add(Restrictions.le("tarikheTavallod", ShamsiDate.get_En_Date(tarikhPayan)));

		if (!tarikhShoroTaghir.isEmpty())
			criteria.add(Restrictions.ge("zamaneAkharinTaghir", ShamsiDate.get_En_Date(tarikhShoroTaghir)));
		if (!tarikhPayanTaghir.isEmpty())
			criteria.add(Restrictions.le("zamaneAkharinTaghir", ShamsiDate.get_En_Date(tarikhPayanTaghir)));

		if (cmbActivation.getSelectedIndex() == 0)
			criteria.add(Restrictions.eq("gheyreFaal", false));
		else if (cmbActivation.getSelectedIndex() == 1)
			criteria.add(Restrictions.eq("gheyreFaal", true));

		if (cmbAks.getSelectedIndex() != 3)
			criteria.add(Restrictions.eq("taeedeAks", kodBiz.getKodEntity(Constants.VaziateKarbar, cmbAks.getValue())));

		if (!type.isEmpty())
			criteria.add(Restrictions.eq("noeOzv", kodBiz.getKodEntity(Constants.NoeOzv, type)));

		if (!reshte.isEmpty())
			criteria.add(Restrictions.eq("reshteyeVarzeshiyeMoredeAlaghe",
					reshteyeVarzeshiBiz.getReshtehById((Short) cmbReshte.getSelectedItem().getValue())));

		if (!tim.isEmpty())
			criteria.add(Restrictions.eq("timeMoredeAlaghe", timBiz.getTimById((Short) cmbTim.getSelectedItem().getValue())));

		if (cmbGorooh.getSelectedItem() != null) {
			gorooh = gorooheKarbariBiz.retrieveById((short) cmbGorooh.getSelectedItem().getValue());
			List<GoroohhayeKarbarEntity> listOzvHayeGorooh = goroohhayeKarbarBiz.getAllByGoroohKarbari(gorooh);
			if (listOzvHayeGorooh != null && listOzvHayeGorooh.size() > 0) {
				List<Integer> listOzv = new ArrayList<Integer>();
				for (GoroohhayeKarbarEntity goroohhayeKarbar : listOzvHayeGorooh) {
					listOzv.add(goroohhayeKarbar.getKarbar().getId());
				}
				criteria.add(Restrictions.in("id", listOzv));
			} else {
				criteria.add(Restrictions.isNull("id"));
			}
		}

		criteria.addOrder(Order.desc("zamaneAkharinTaghir"));

		int firstResult = pageNumber * paging.getPageSize();
		int maxResults = paging.getPageSize();

		System.out.println("criteria END");
		System.out.println("list START");
		List<OzvEntity> list = ozvBiz.retrieveByCriteria(criteria, firstResult, maxResults);
		System.out.println("list END");

		int totalSize = ozvBiz.countByCriteria(criteria);
		paging.setTotalSize(totalSize);
		if (pageNumber < paging.getPageCount())
			paging.setActivePage(pageNumber);
		else {
			pageNumber = 0;
		}
		System.out.println("list Set");
		setUsers(list);
		System.out.println("FILTER END");
	}

	@Listen("onPaging = #paging")
	public void paging() throws ParseException {
		pageNumber = paging.getActivePage();
		filter();
		Sessions.getCurrent().setAttribute(pageName + "paging", paging.getActivePage());
	}

	private void beforeFilter() {
		Sessions.getCurrent().setAttribute(pageName + "paging", 0);
		pageNumber = 0;
	}

	@Listen("onClick = #btnFilter; onOK = #txtUsername, #txtName, #txtFamily, #txtNationalCode, #cmbUsertype, #cmbReshte, #cmbTim, #cmbActivation, #cmbAks")
	public void filterBtn() throws ParseException {
		beforeFilter();
		filter();
	}

	@Listen("onClick = #btnLaghv")
	public void laghveFilter() throws ParseException {
		txtUsername.setValue(null);
		txtName.setValue(null);
		txtFamily.setValue(null);
		txtNationalCode.setValue(null);
		cmbUsertype.setValue(null);
		cmbGorooh.setValue(null);
		cmbReshte.setValue(null);
		cmbTim.setValue(null);
		cmbActivation.setSelectedIndex(0);
		cmbAks.setSelectedIndex(3);
		txtDateStart.setValue("");
		txtDateEnd.setValue("");
		txtChangeDateStart.setValue("");
		txtChangeDateEnd.setValue("");
		beforeFilter();
		filter();
	}

	@Listen("onClick = #btnAks")
	public void taeedeAks() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Approve)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else if (listbox.getSelectedItems().size() > 1)
				Messagebox.show(Labels.getLabel("pleaseSelectJustOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				Integer id = Integer.valueOf(((Label) listbox.getSelectedItem().getFirstChild().getFirstChild()).getValue());
				OzvEntity ozv = ozvBiz.retrieveById(id);
				KodEntity taeedeAks = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_TaeedShode);
				if (ozv.getTaeedeAks() != null && ozv.getTaeedeAks().equals(taeedeAks))
					Messagebox.show(Labels.getLabel("userIsValid"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
				else {
					Map<String, Object> args = new HashMap<String, Object>();
					args.put("ozv", ozv);
					NavigationUtil.goTo(TaeedeAksPage.class, args);
				}
			}
		}
	}

	@Listen("onClick = #btnGorooh")
	public void groups() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Package)) {
			Map<String, Object> args = new HashMap<String, Object>();
			NavigationUtil.goTo(GoroheKarbariPage.class, args);
		}
	}

	@Listen("onClick = #btnPayOzviat")
	public void payHagheOzviat() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_PardakhtHagheOzviatGorohi)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				Messagebox.show("آیا برای پرداخت حق عضویت کاربران انتخاب شده اطمینان دارید؟", Labels.getLabel("questionTitle"), Messagebox.YES
						| Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onYes")) {
							List<OzvEntity> karbaranEntekhabShode = new ArrayList<OzvEntity>();
							for (Listitem item : listbox.getSelectedItems()) {
								Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
								OzvEntity ozv = ozvBiz.retrieveById(id);
								karbaranEntekhabShode.add(ozv);
							}
							Map<String, Object> args = new HashMap<String, Object>();
							args.put("karbaranEntekhabShode", karbaranEntekhabShode);
							NavigationUtil.goTo(PardakhtGorohiPage.class, args);
						}
					}
				});
			}
		}
	}

	@Listen("onClick = #btnDarkhasteKart")
	public void sendDarkhasteKart() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_DarkhasteKartGorohi)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				Messagebox.show("آیا برای ثبت درخواست کارت کاربران انتخاب شده اطمینان دارید؟", Labels.getLabel("questionTitle"), Messagebox.YES
						| Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onYes")) {
							List<OzvEntity> azayeEntekhabShode = new ArrayList<OzvEntity>();
							Boolean flag = true;
							for (Listitem item : listbox.getSelectedItems()) {
								Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());

								OzvEntity ozv = ozvBiz.retrieveById(id);
								if (!ozv.getTaeedeAks().getMeghdar().equals(Constants.VaziateKarbar_TaeedShode)) {
									Messagebox.show("وضعیت کاربر " + ozv.getName() + " " + ozv.getFamil() + " تایید نشده است.");
									flag = false;
									break;
								}

								KartEntity lastKart = kartBiz.getLastKart(ozv, PermissionUtil.getCurrentPortal());
								if (lastKart != null) {
									Messagebox.show("امکان صدور کارت المثنی برای  " + ozv.getName() + " " + ozv.getFamil()
											+ " وجود ندارد. برای درخواست کارت المثتی هر عضو باید شخصا اقدام کند.");
									flag = false;
									break;
								}

								azayeEntekhabShode.add(ozv);
							}
							if (flag) {
								Map<String, Object> args = new HashMap<String, Object>();
								args.put("azayeEntekhabShode", azayeEntekhabShode);
								NavigationUtil.goTo(DarkhasteKartGorohiPage.class, args);
							} else {

							}
						}
					}
				});
			}
		}
	}

	@Listen("onClick = #btnDarkhasteKartRaygan")
	public void sendDarkhasteKartRaygan() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_DarkhasteKartGorohiRaygan)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {
				Messagebox.show("آیا برای ثبت درخواست کارت کاربران انتخاب شده اطمینان دارید؟", Labels.getLabel("questionTitle"), Messagebox.YES
						| Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onYes")) {

							boolean flag = true;
							for (Listitem item : listbox.getSelectedItems()) {
								Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
								try {
									final OzvEntity ozv = ozvBiz.retrieveById(id);
									if (!ozv.getTaeedeAks().getMeghdar().equals(Constants.VaziateKarbar_TaeedShode)) {
										Messagebox.show("وضعیت کاربر " + ozv.getName() + " " + ozv.getFamil()
												+ " تایید شده نمی باشد. پس از تایید وضعیت این کاربر دوباره برای درخواست کارت آن اقدام نمایید");
									} else {
										PortalEntity portal = PermissionUtil.getCurrentPortal();
										KodEntity nahveyeTahvil = kodBiz.getKodEntity(Constants.NahveyeTahvil, Constants.Pishkhan);
										MadreseEntity madreseJari = null;
										TimEntity timeJari = null;
										if (portal.getId().toString().equals(Constants.FootballSchoolPortalID)) {
											madreseJari = ozveMadreseBiz.getMadreseyeHazerByOzv(ozv);

										} else if (portal.getId().toString().equals(Constants.MembershipPortalID)) {
											timeJari = ozveTimBiz.getTimHazerByOzv(ozv);

										}
										final PardakhteDarkhasteKartEntity pardakhtKart = new PardakhteDarkhasteKartEntity(nahveyeTahvil, null, ozv,
												null, portal, madreseJari, timeJari);
										if (kartBiz.getLastKart(ozv, PermissionUtil.getCurrentPortal()) != null) {
											Messagebox.show("کاربر " + ozv.getName() + " " + ozv.getFamil()
													+ " دارای کارت می باشد. آیا مایل به صدور کارت المثنی برای وی هستید؟",
													Labels.getLabel("questionTitle"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
													new EventListener<Event>() {

														@Override
														public void onEvent(Event event) throws Exception {
															if (event.getName().equals("onYes")) {
																KartEntity kart = kartBiz.creatNewKartOrAlmosana(pardakhtKart);
																darkhastKartBiz.create(pardakhtKart, kart);
															}
														}
													});
										} else {
											KartEntity kart = kartBiz.creatNewKartOrAlmosana(pardakhtKart);
											darkhastKartBiz.create(pardakhtKart, kart);
										}
									}

								} catch (DataIntegrityViolationException e) {
									flag = false;
									Messagebox.show(ozvBiz.retrieveById(id).getNameKarbari() + " : " + "خطا در ثبت درخواست کارت برای کاربر ",
											Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);

								}
							}

							if (flag) {
								Messagebox.show("ثبت درخواست کارت برای کاربران مورد نظر با موفقیت انجام شد", "ثبت درخواست کارت کاربران",
										Messagebox.OK, Messagebox.INFORMATION);
							}
						}
					}
				});
			}
		}
	}

	@Listen("onClick = #btnTakhsiseGorooh")
	public void takhsiseGorooh() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_AddGroup)) {
			if (listbox.getSelectedItems().size() == 0)
				Messagebox.show(Labels.getLabel("pleaseSelectOne"), Labels.getLabel("errorTitle"), Messagebox.OK, Messagebox.ERROR);
			else {

				List<OzvEntity> list = new ArrayList<OzvEntity>();
				for (Listitem item : listbox.getSelectedItems()) {
					Integer id = Integer.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
					list.add(ozvBiz.retrieveById(id));
				}
				Map<String, Object> args = new HashMap<String, Object>();
				if (list.size() == 1) {
					args.put("ozv", list.get(0));
					args.put("ozvList", null);
				} else {
					args.put("ozv", null);
					args.put("ozvList", list);
				}
				NavigationUtil.goTo(TakhsisGorohPage.class, args);

			}
		}
	}

	private void fillGrooh() {
		cmbGorooh.getItems().clear();
		List<GorooheKarbariEntity> allGorooh = gorooheKarbariBiz.retrieveAllFaalByPortal();
		if (allGorooh == null)
			return;
		for (GorooheKarbariEntity g : allGorooh) {
			Comboitem item = new Comboitem();
			item.setValue(g.getId());
			item.setLabel(g.getNam());
			item.setSclass("rtl");
			item.setParent(cmbGorooh);
		}
	}

	private void fillReshteyeVarzeshi() {
		cmbReshte.getItems().clear();

		List<ReshteyeVarzeshiEntity> allReshteh = reshteyeVarzeshiBiz.getAllReshteh();
		if (allReshteh == null)
			return;
		for (ReshteyeVarzeshiEntity reshteh : allReshteh) {
			Comboitem item = new Comboitem();
			item.setValue(reshteh.getId());
			item.setLabel(reshteh.getNameReshteyeVarzeshi());
			item.setSclass("rtl");
			item.setParent(cmbReshte);
		}
	}

	@Listen("onChange = #cmbReshte")
	public void fillTim() {
		cmbTim.setText("");
		cmbTim.getItems().clear();
		List<TimEntity> allTim = timBiz.getAllTimByReshteh(reshteyeVarzeshiBiz.getReshteh(cmbReshte.getText()));
		if (allTim == null)
			return;
		for (TimEntity tim : allTim) {
			Comboitem item = new Comboitem();
			item.setValue(tim.getId());
			item.setLabel(tim.getNameTim());
			item.setSclass("rtl");
			item.setParent(cmbTim);
		}
	}

	@Listen("onClick = #btnChangePassword")
	public void gotoChangePasswordPage() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_NewPass)) {
			Integer id = Integer.valueOf(((Label) listbox.getSelectedItem().getFirstChild().getFirstChild()).getValue());
			OzvEntity ozv = ozvBiz.retrieveById(id);
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("ozv", ozv);
			NavigationUtil.goTo(ChangePasswordPage.class, args);
		}
	}

	@Listen("onClick = #btnEditUser")
	public void gotoEditUserPage() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_EditAdmin)) {
			Integer id = Integer.valueOf(((Label) listbox.getSelectedItem().getFirstChild().getFirstChild()).getValue());
			OzvEntity ozv = ozvBiz.retrieveById(id);
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("ozv", ozv);
			NavigationUtil.goTo(EditUserPage.class, args);
		}
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/users.zul";
	}

}
