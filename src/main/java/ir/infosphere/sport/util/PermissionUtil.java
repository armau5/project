package ir.infosphere.sport.util;

import ir.infosphere.sport.bean.OzviyatBean;
import ir.infosphere.sport.bean.PardakhtBean;
import ir.infosphere.sport.biz.GoroohhayeKarbarBiz;
import ir.infosphere.sport.biz.KarbaranePortalBiz;
import ir.infosphere.sport.biz.MojavezBiz;
import ir.infosphere.sport.biz.MojavezeGoroohBiz;
import ir.infosphere.sport.biz.PortalBiz;
import ir.infosphere.sport.biz.RahgiriBiz;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.MojavezEntity;
import ir.infosphere.sport.entity.MojavezeGoroohEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.ui.common.AccessDeniedPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;

public class PermissionUtil {

	public static boolean Check(PermissionEnum permission) {
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		MojavezBiz mojavezBiz = appContext.getBean(MojavezBiz.class);

		MojavezEntity mojavez = mojavezBiz.retrieveById(permission.getPermissionCode());

		List<MojavezEntity> mojavezHayeKarbar = getMojavezHayeKarbar();
		if (mojavezHayeKarbar != null) {
			if (mojavezHayeKarbar.size() > 0) {
				if (mojavezHayeKarbar.contains(mojavez))
					return true;
				}
			}
		return false;
	}
	
	public static void putEmptyMojavezHayeKarbar() {
		List<MojavezEntity> mojavezHayeKarbar = new ArrayList<>();
		mojavezHayeKarbar.add(new MojavezEntity());
		Sessions.getCurrent().setAttribute("mojavezhayekarbar", mojavezHayeKarbar);
	}

	public static void refreshMojavezHayeKarbar() {
		Sessions.getCurrent().setAttribute("mojavezhayekarbar", null);
		getMojavezHayeKarbar();
	}

	@SuppressWarnings({ "unchecked" })
	private static List<MojavezEntity> getMojavezHayeKarbar() {
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		OzvEntity ozv = getCurrentUser();
		
		GoroohhayeKarbarBiz goroohhayeKarbarBiz = (GoroohhayeKarbarBiz) appContext.getBean("goroohhayeKarbarBiz");
		MojavezeGoroohBiz mojavezeGoroohBiz = (MojavezeGoroohBiz) appContext.getBean("mojavezeGoroohBiz");
		List<MojavezEntity> mojavezHayeKarbar = new ArrayList<>();
		if (ozv != null) {
			mojavezHayeKarbar = (List<MojavezEntity>) Sessions.getCurrent().getAttribute("mojavezhayekarbar");
			if (mojavezHayeKarbar == null) {
				mojavezHayeKarbar = new ArrayList<>();
				List<GoroohhayeKarbarEntity> temp = goroohhayeKarbarBiz.getAllByOzvAndPortal(ozv, null);
				for (GoroohhayeKarbarEntity entity : temp) {
					List<MojavezeGoroohEntity> mojavezNahayee = mojavezeGoroohBiz.retriveByGorooh(entity.getGorooh());
					if (mojavezNahayee != null)
						for (MojavezeGoroohEntity mojavezeGoroohEntity : mojavezNahayee) {
							mojavezHayeKarbar.add(mojavezeGoroohEntity.getMojavez());
						}
				}
				Sessions.getCurrent().setAttribute("mojavezhayekarbar", null);
				Sessions.getCurrent().setAttribute("mojavezhayekarbar", mojavezHayeKarbar);
			}
		}
		return mojavezHayeKarbar;
	}

	public static boolean CheckFunction(PermissionEnum permission) {
		if (Check(permission) == false) {
			Messagebox.show(Labels.getLabel("accessDenied"),
					Labels.getLabel("errorTitle"), Messagebox.OK,
					Messagebox.ERROR);
			return false;
		} else
			return true;
	}

	public static OzvEntity getCurrentUser() {
		OzvEntity ozv = (OzvEntity) Sessions.getCurrent().getAttribute("user");
		return ozv;
	}

	public static Date getLastRahgiriCheck() {
		Date lastRahgiriCheck = (Date) Sessions.getCurrent().getAttribute(
				"lastRahgiriCheck");
		if (lastRahgiriCheck == null)
			return new Date(0);
		return lastRahgiriCheck;
	}

	public static void setLastRahgiriCheck() {
		Sessions.getCurrent().setAttribute("lastRahgiriCheck", new Date());
	}

	public static Integer getLastNadide() {
		Integer lastNadide = (Integer) Sessions.getCurrent().getAttribute(
				"lastNadide");
		if (lastNadide == null)
			return 0;
		return lastNadide;
	}

	public static void setLastNadide(Integer count) {
		Sessions.getCurrent().setAttribute("lastNadide", count);
	}

	public static Integer getNadide() {
		Integer nadideCount = 0;
		Date someMinutesBefore = new Date((new Date()).getTime()
				- PermissionUtil.getCurrentPortal()
						.getBazeyeBeroozresanieRahgiri() * 60 * 1000);
		if (PermissionUtil.getLastRahgiriCheck().before(someMinutesBefore)) {
			ServletContext servletContext = Executions.getCurrent()
					.getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			RahgiriBiz rahgiriBiz = appContext.getBean(RahgiriBiz.class);
			nadideCount = rahgiriBiz.getAllNadideCount(PermissionUtil
					.getCurrentUser());
			PermissionUtil.setLastNadide(nadideCount);
			PermissionUtil.setLastRahgiriCheck();
		} else {
			nadideCount = PermissionUtil.getLastNadide();
		}
		return nadideCount;
	}

	public static PortalEntity getCurrentPortal() {
		PortalEntity portal = (PortalEntity) Sessions.getCurrent()
				.getAttribute("portal");
		return portal;
	}

	@SuppressWarnings("unchecked")
	public static List<PortalEntity> getCurrentUsersPortals() {
		List<PortalEntity> portals = (List<PortalEntity>) Sessions.getCurrent()
				.getAttribute("usersPortalsList");
		return portals;
	}

	public static void logOut() {
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("/");
	}

	public static PortalEntity getPortalById(String portalId) {
		ServletContext servletContext = Executions.getCurrent().getDesktop()
				.getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		PortalBiz portalBiz = appContext.getBean(PortalBiz.class);
		PortalEntity portal = portalBiz.retrievePortalById(Long
				.parseLong(portalId));
		return portal;
	}
	
	public static void setCurrentServiceBasket(List<OzviyatBean> listOzviat) {
		Sessions.getCurrent().setAttribute("basket", null);
		Sessions.getCurrent().setAttribute("basket", listOzviat);
	}
	
	public static void setSharjRelatedFactor(PardakhtBean pardakht) {
		Sessions.getCurrent().setAttribute("sharjFactor", null);
		Sessions.getCurrent().setAttribute("sharjFactor", pardakht);
	}
	
	public static void setEmptySharjRelatedFactor() {
		Sessions.getCurrent().setAttribute("sharjFactor", null);
	}
	
	public static PardakhtBean getSharjRelatedFactor() {
		return (PardakhtBean) Sessions.getCurrent().getAttribute("sharjFactor");
	}
	
	@SuppressWarnings("unchecked")
	public static List<OzviyatBean> getCurrentServiceBasket() {
		return (List<OzviyatBean>) Sessions.getCurrent().getAttribute("basket");
	}

	public static void setCurrentPortal(PortalEntity portal) {
		Sessions.getCurrent().setAttribute("portal", portal);
		
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		KarbaranePortalBiz karbaranePortalBiz = appContext.getBean(KarbaranePortalBiz.class);
		List<PortalEntity> usersPortalsList = karbaranePortalBiz.retrieveByPortal(portal);

		Sessions.getCurrent().setAttribute("usersPortalsList", usersPortalsList);
	}

	public static void setCurrentUser(OzvEntity ozv) {
		Sessions.getCurrent().setAttribute("user", ozv);
		Sessions.getCurrent().setAttribute("userID", ozv.getId());
	}

	public static String getCurrentDomain() {
		String port = (Executions.getCurrent().getServerPort() == 80) ? ""
				: (":" + Executions.getCurrent().getServerPort());
		String serverAddress = Executions.getCurrent().getScheme() + "://"
				+ Executions.getCurrent().getServerName() + port
				+ Executions.getCurrent().getContextPath();
		return serverAddress;
	}

	public static void CheckButton(Button button, PermissionEnum permission) {
		button.setVisible(Check(permission));
	}

	public static void CheckButtonWith2Permission(Button button,
			PermissionEnum permission1, PermissionEnum permission2) {
		button.setVisible(Check(permission1) && Check(permission2));
	}

	public static void CheckPage(PermissionEnum permission) {
		if (Check(permission) == false) {
			NavigationUtil.goTo(AccessDeniedPage.class, null);
		}
	}

	public static void Check2Page(PermissionEnum perm1, PermissionEnum perm2) {
		if (!(Check(perm1) == true || Check(perm2) == true)) {
			Map<String, Object> args = new HashMap<String, Object>();
			NavigationUtil.goTo(AccessDeniedPage.class, args);
		}
	}

	public static void increaseWrongUserOrPassCounter() {
		Integer count = (Integer) Sessions.getCurrent().getAttribute(
				"wrongUserOrPassCounter");
		if (count == null)
			count = 1;
		else
			count++;
		Sessions.getCurrent().setAttribute("wrongUserOrPassCounter", count);
	}

	public static Integer getWrongUserOrPassCounter() {
		Integer count = (Integer) Sessions.getCurrent().getAttribute("wrongUserOrPassCounter");
		if (count == null)
			return 0;
		else
			return count;
	}

	public static void removeWrongUserOrPassCounter() {
		Sessions.getCurrent().removeAttribute("wrongUserOrPassCounter");
	}
}
