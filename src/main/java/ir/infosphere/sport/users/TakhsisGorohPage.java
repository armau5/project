package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.GorooheKarbariBiz;
import ir.infosphere.sport.biz.GoroohhayeKarbarBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

public class TakhsisGorohPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private GorooheKarbariBiz gorooheKarbariBiz;
	private GoroohhayeKarbarBiz goroohhayeKarbarBiz;
	//private KodBiz kodBiz;
	private OzvEntity ozv;
	private List<OzvEntity> listAza = new ArrayList<OzvEntity>();
	private List<GorooheKarbariEntity> firstGoroohOzv=new ArrayList<GorooheKarbariEntity>();
	
	@Wire
	private Listbox Listbox;
	@Wire
	private Label lblName ,lblFamil ,lblUsername;
	@Wire
	private Div divName, divFamily, divUsername, divAlert;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_AddGroup);
		return super.doBeforeCompose(page, parent, compInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("takhsisGorohPageName");
		super.doAfterCompose(comp);
		gorooheKarbariBiz = (GorooheKarbariBiz) appContext
				.getBean("gorooheKarbariBiz");
		goroohhayeKarbarBiz = (GoroohhayeKarbarBiz) appContext
				.getBean("goroohhayeKarbarBiz");
		//kodBiz = (KodBiz) appContext.getBean("kodBiz");
		final Execution execution = Executions.getCurrent();
		ozv = (OzvEntity) execution.getArg().get("ozv");
		listAza = (List<OzvEntity>) execution.getArg().get("ozvList");
		
		if (ozv != null) {
			lblName.setValue(ozv.getName());
			lblFamil.setValue(ozv.getFamil());
			lblUsername.setValue(ozv.getNameKarbari());
			divAlert.setVisible(false);
			List<GoroohhayeKarbarEntity> takhsisha=goroohhayeKarbarBiz.getAllByOzvAndPortal(ozv, PermissionUtil.getCurrentPortal());
			for (GoroohhayeKarbarEntity goroohhayeKarbar : takhsisha) {
				firstGoroohOzv.add(goroohhayeKarbar.getGorooh());
			}
		}
		else {
			divName.setVisible(false);
			divFamily.setVisible(false);
			divUsername.setVisible(false);
		}
		List<GorooheKarbariEntity> gorooheKarbari = gorooheKarbariBiz.retrieveAllFaalByPortal();
		setGroups(gorooheKarbari);
	}

	private void setGroups(List<GorooheKarbariEntity> groups) {
		Listbox.getItems().clear();
		
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
			if(firstGoroohOzv.contains(groups.get(i)))
				item.setSelected(true);
			
			Listbox.appendChild(item);
		}
		
	}

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(UsersPage.class, args);
	}

	@Listen("onClick = #btnSave")
	public void save() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_AddGroup)) {
			if (ozv != null) {
				List<GorooheKarbariEntity> endGoroohOzv=new ArrayList<GorooheKarbariEntity>();
				for (Listitem item : Listbox.getSelectedItems()) {
					Short id = Short.valueOf(((Label) item.getFirstChild().getFirstChild()).getValue());
					GorooheKarbariEntity g=gorooheKarbariBiz.retrieveById(id);
					endGoroohOzv.add(g);
					if(!firstGoroohOzv.contains(g)){
						GoroohhayeKarbarEntity entity = new GoroohhayeKarbarEntity();
						entity.setGorooh(g);
						entity.setKarbar(ozv);
						entity.setShorooeTakhsis(new Date());
						entity.setPayaneTakhsis(null);
						goroohhayeKarbarBiz.create(entity);
					}
				}
				firstGoroohOzv.removeAll(endGoroohOzv);
				for (GorooheKarbariEntity gorooheKarbari : firstGoroohOzv) {
					goroohhayeKarbarBiz.setPayanTakhsis(ozv,gorooheKarbari);
				}
				
			}
			else if (listAza != null) {
				for (OzvEntity user : listAza) {
					for (Listitem item : Listbox.getSelectedItems()) {
						Short id = Short.valueOf(((Label) item.getFirstChild()
								.getFirstChild()).getValue());
						GoroohhayeKarbarEntity entity = new GoroohhayeKarbarEntity();
						entity.setGorooh(gorooheKarbariBiz.retrieveById(id));
						entity.setKarbar(user);
						entity.setShorooeTakhsis(new Date());
						entity.setPayaneTakhsis(null);
						goroohhayeKarbarBiz.createIfNotExists(entity);
					}
				}
			}
			Messagebox.show(Labels.getLabel("changeSaved"),
					Labels.getLabel("doneTitle"), 1, "",
					new EventListener<Event>() {

						@Override
						public void onEvent(Event arg0) throws Exception {
							navigate();
						}
					});
		}
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/takhsiseGorooh.zul";
	}

}
