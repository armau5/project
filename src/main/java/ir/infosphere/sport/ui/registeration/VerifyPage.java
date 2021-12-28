package ir.infosphere.sport.ui.registeration;

import ir.infosphere.sport.biz.BazyabiRamzBiz;
import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.entity.BazyabiRamzEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.util.MailUtil;

import java.util.Random;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;

public class VerifyPage extends BasePage {

	@Wire
	private Label lbl1, lbl2;
	
	private OzvEntity tempOzv;
	private OzvBiz ozvBiz;
	private BazyabiRamzBiz bazyabiRamzBiz;

	private static final long serialVersionUID = 1L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		pageName = Labels.getLabel("verifyPageName");
		super.doAfterCompose(comp);
		String uId = null;
		ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
		bazyabiRamzBiz = (BazyabiRamzBiz) appContext.getBean("bazyabiRamzBiz");
		if (Executions.getCurrent().getParameter("q") != null) {
			uId = Executions.getCurrent().getParameter("q");
		}
		if (uId != null)
		{
			BazyabiRamzEntity temp = bazyabiRamzBiz.retrieveByUID(uId);
			if (temp != null)
			{
				tempOzv = temp.getOzv();
		
				String newPassword = generateNewPassword();
				tempOzv.setHasheRamzeOboor(ozvBiz.getHash(newPassword));
				ozvBiz.update(tempOzv);
				try {
					MailUtil.sendRetrievePasswordMail(tempOzv.getPosteElectronik(), "رمز عبور جدید شما : " + newPassword);
					lbl1.setValue("گذر واژه شما بازیابی شد");
					lbl2.setValue("گذر واژه جدید به ایمیل شما ارسال شد");
					
					temp.setGheyreFaal(true);
					bazyabiRamzBiz.update(temp);
				} catch (Exception e) {
					Messagebox.show("ایمیل ارسال نشد!", "خطا در ارسال ایمیل", Messagebox.OK, Messagebox.ERROR);
					e.printStackTrace();
				}
			}
			else
			{
				lbl1.setValue("رمز عبور برای شما یکبار ایمیل شده است");
				lbl2.setValue("جهت بازیابی مجدد رمز عبور به سامانه مراجعه کنید");
			}
		}
	}

	private String generateNewPassword() {
		char[] symbols;
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();

		char[] buf = new char[8];
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[new Random().nextInt(symbols.length)];
		return new String(buf);
	}

	@Override
	public String getPageAddress() {
		return "/verify.zul";
	}
}
