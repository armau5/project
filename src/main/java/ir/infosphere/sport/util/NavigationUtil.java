package ir.infosphere.sport.util;

import ir.infosphere.sport.ui.common.BasePage;

import java.util.HashMap;
import java.util.Map;

//import org.zkoss.pushState.PushState;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;

public class NavigationUtil {

	public static void goTo(Class<? extends BasePage> pageClass) {
		goTo(pageClass, null);
	}

	public static void goTo(String pageAddress) {
		goTo(pageAddress, null);
	}
	
	public static void goTo(Class<? extends BasePage> pageClass, Map<?, ?> args, String bookMark) {
		Executions.getCurrent().getDesktop().setBookmark(bookMark);
		goTo(pageClass, args);
	}

	public static void goTo(Class<? extends BasePage> pageClass, Map<?, ?> args) {
		String pageAddr = null;
		try {
			pageAddr = pageClass.newInstance().getPageAddress();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		createComponentsAndPutMap(pageAddr, args);
	}

	public static void goTo(String pageAddress, Map<?, ?> args) {
		createComponentsAndPutMap(pageAddress, args);
	}

	private static void createComponentsAndPutMap(String pageAddress,
			Map<?, ?> args) {
		if (pageAddress == null || pageAddress.equals(""))
			throw new IllegalArgumentException("Invalid Page Address");

		for (Page page : Executions.getCurrent().getDesktop().getPages())
			for (Component root : page.getRoots())
				root.detach();

		Executions.createComponents(pageAddress, null, args);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addr", pageAddress);
		map.put("args", args);
	}
}
