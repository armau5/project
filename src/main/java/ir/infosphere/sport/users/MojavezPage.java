package ir.infosphere.sport.users;

import ir.infosphere.sport.biz.MojavezBiz;
import ir.infosphere.sport.biz.MojavezeGoroohBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.MojavezEntity;
import ir.infosphere.sport.entity.MojavezeGoroohEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.NavigationUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public class MojavezPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private MojavezBiz mojavezBiz;
	private MojavezeGoroohBiz mojavezeGoroohBiz;
	private GorooheKarbariEntity gorooh;
	private List<MojavezEntity> hameyeMojavezha;
	private List<MojavezEntity> mojavezhayeGorooh;

	@Wire
	private Tree tree;
	@Wire
	private Treechildren treechildren;
	@Wire
	private Label lblName;

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		PermissionUtil.CheckPage(PermissionEnum.USER_Group_Permission);
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("mojavezPageName");
		super.doAfterCompose(comp);
		mojavezBiz = (MojavezBiz) appContext.getBean("mojavezBiz");
		mojavezeGoroohBiz = (MojavezeGoroohBiz) appContext.getBean("mojavezeGoroohBiz");
		final Execution execution = Executions.getCurrent();
		gorooh = (GorooheKarbariEntity) execution.getArg().get("gorooh");
		lblName.setValue(gorooh.getNam());
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.eq("gorooh", gorooh));
		List<MojavezeGoroohEntity> list = mojavezeGoroohBiz.retrieveByCriteria(criteria);
		mojavezhayeGorooh = new ArrayList<MojavezEntity>();
		for (int i = 0; i < list.size(); i++)
			mojavezhayeGorooh.add(list.get(i).getMojavez());

		hameyeMojavezha = mojavezBiz.retrieveAll();
		setMojaveha(hameyeMojavezha);

	}

	private void setMojaveha(List<MojavezEntity> mojavezha) {
		treechildren.getItems().clear();

		for (int i = 0; i < mojavezha.size(); i++)
			if (mojavezha.get(i).getValed() == null)
				treechildren.appendChild(addChildToTree(mojavezha.get(i)));

	}

	public Treeitem addChildToTree(MojavezEntity mojavez) {
		Treeitem item = new Treeitem();
		item.setOpen(false);
		Treerow row = new Treerow();
		// ///////set Id
		Treecell idCell = new Treecell();
		Label idlbl = new Label();
		idlbl.setValue(mojavez.getId().toString());
		idlbl.setVisible(false);
		idCell.appendChild(idlbl);
		row.appendChild(idCell);
		// ///////set Name
		Treecell nameCell = new Treecell();
		nameCell.setLabel(mojavez.getName());
		row.appendChild(nameCell);

		if (mojavezhayeGorooh.contains(mojavez))
		{
			boolean select = true;
			for (int i = 0; i < hameyeMojavezha.size(); i++) {
				MojavezEntity mojavezTemp_1 = hameyeMojavezha.get(i);
				if (mojavezTemp_1.getValed() != null && mojavezTemp_1.getValed().equals(mojavez))
					if (mojavezhayeGorooh.contains(mojavezTemp_1) == false)
					{
						select = false;
						break;
					}
			}
			item.setSelected(select);
		}

		item.appendChild(row);
		List<MojavezEntity> list = new ArrayList<MojavezEntity>();
		for (int i = 0; i < hameyeMojavezha.size(); i++) {
			if (hameyeMojavezha.get(i).getValed() != null && hameyeMojavezha.get(i).getValed().equals(mojavez))
				list.add(hameyeMojavezha.get(i));
		}
		if (list.size() > 0) {
			Treechildren tc = new Treechildren();
			for (int i = 0; i < list.size(); i++) {
				Treeitem child = addChildToTree(list.get(i));
				tc.appendChild(child);
				if ((child.isSelected() || child.isOpen()) && !item.isSelected()) {
					row.setStyle("background:#cccccc");
					item.setOpen(true);
				}
			}
			item.appendChild(tc);
		}
		return item;
	}

	@Listen("onClick = #btnBack")
	public void navigate() {
		Map<String, Object> args = new HashMap<String, Object>();
		NavigationUtil.goTo(GoroheKarbariPage.class, args);
	}

	@Listen("onClick = #btnSave")
	public void save() {
		if (PermissionUtil.CheckFunction(PermissionEnum.USER_Group_Permission))
		{
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.eq("gorooh", gorooh));
		List<MojavezeGoroohEntity> list = mojavezeGoroohBiz
				.retrieveByCriteria(criteria);
		for (int i = 0; i < list.size(); i++) {
			mojavezeGoroohBiz.delete(list.get(i).getId());
		}
		
		for (Treeitem item : tree.getSelectedItems()) {
			Integer id = Integer.valueOf(((Label) item.getFirstChild()
					.getFirstChild().getFirstChild()).getValue());
			
			MojavezeGoroohEntity entity = new MojavezeGoroohEntity();
			
			entity.setMojavez(mojavezBiz.retrieveById(id));
			entity.setGorooh(gorooh);
			mojavezeGoroohBiz.create(entity);
			
			MojavezEntity mojavez = mojavezBiz.retrieveById(id);
			while (mojavez.getValed() != null)
			{
				MojavezeGoroohEntity temp = new MojavezeGoroohEntity();
				mojavez = mojavez.getValed();
				temp.setMojavez(mojavez);
				temp.setGorooh(gorooh);
				if (mojavezeGoroohBiz.checkRepeat(mojavez, gorooh) == false)
					mojavezeGoroohBiz.create(temp);
				else
					break;
			}
		}
		
		Messagebox.show(Labels.getLabel("changeSaved"),
				Labels.getLabel("doneTitle"), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onSelect = #tree")
	public void onSelect(SelectEvent<Treeitem, String> event) {
		Treeitem ref = event.getReference();
		if (ref.isSelected()) {
			selectItem(ref, true);
		} else {
			selectItem(ref, false);
		}
	}

	public void selectItem(Treeitem item, boolean selection) {
		item.setSelected(selection);

		if (item.getChildren().size() > 1) {
			List<Component> items = item.getChildren().get(1).getChildren();
			for (int i = 0; i < items.size(); i++)
				selectItem((Treeitem) items.get(i), selection);
		}
		Treeitem parent = item.getParentItem();
		if (selection) {
			if (((Treerow) item.getFirstChild()).getStyle() != null
					&& ((Treerow) item.getFirstChild()).getStyle().contains(
							"background:#cccccc"))
				((Treerow) item.getFirstChild()).setStyle(((Treerow) item.getFirstChild()).getStyle()
						.replace("background:#cccccc", ""));

			while (parent != null) {
				((Treerow) parent.getFirstChild())
						.setStyle("background:#cccccc");
				parent = parent.getParentItem();
			}
		} else {
			while (parent != null) {
				parent.setSelected(false);
				if (getSelectedChildCount(parent) > 0)
					((Treerow) parent.getFirstChild())
							.setStyle("background:#cccccc");
				else if (((Treerow) parent.getFirstChild()).getStyle() != null)
					((Treerow) parent.getFirstChild()).setStyle(((Treerow) parent.getFirstChild()).getStyle().replace("background:#cccccc",""));
				parent = parent.getParentItem();
			}
		}
	}

	public int getSelectedChildCount(Treeitem item) {
		int count = 0;
		if (item.getChildren().size() > 1) {
			List<Component> items = item.getChildren().get(1).getChildren();
			for (int i = 0; i < items.size(); i++)
				if (((Treeitem) items.get(i)).isSelected()
						|| getSelectedChildCount((Treeitem) items.get(i)) > 0)
					count++;
		}
		return count;
	}

	@Override
	public String getPageAddress() {
		return "/WEB-INF/inner-pages/users/mojavez.zul";
	}

}
