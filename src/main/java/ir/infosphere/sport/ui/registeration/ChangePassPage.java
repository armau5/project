package ir.infosphere.sport.ui.registeration;

import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import ir.infosphere.sport.ui.common.WelcomePage;
import ir.infosphere.sport.util.NavigationUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.mesg.MessageConst;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class ChangePassPage extends BasePage {
    private static final long serialVersionUID = 1L;

    private OzvBiz ozvBiz;
    private OzvEntity ozv;

    @Wire
    private Textbox txtCurrentPass;
    @Wire
    private Textbox txtNewPass;
    @Wire
    private Textbox txtNewPassRepeat;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        pageName = Labels.getLabel("changePassPageName");
        super.doAfterCompose(comp);
        ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
        ozv = (OzvEntity) Sessions.getCurrent().getAttribute("user");
    }
    
    @Listen("onClick = #btnSave; onOK = textbox")
    public void save() {
        String currentPass = txtCurrentPass.getValue();
        String newPass = txtNewPass.getValue();
        String newPassRepeat = txtNewPassRepeat.getValue();
        if (currentPass.isEmpty())
            throw new WrongValueException(txtCurrentPass, ErrorTypeEnum.RequiredField.getFaName());
        if (newPass.isEmpty())
            throw new WrongValueException(txtNewPass, ErrorTypeEnum.RequiredField.getFaName());
        if (newPassRepeat.isEmpty())
            throw new WrongValueException(txtNewPassRepeat, ErrorTypeEnum.RequiredField.getFaName());
        if (ozvBiz.authenticate(ozv.getNameKarbari(), currentPass) == null)
            throw new WrongValueException(txtCurrentPass, ErrorTypeEnum.InvalidValue.getFaName());
        if (!newPass.equals(newPassRepeat))
            throw new WrongValueException(txtNewPassRepeat, ErrorTypeEnum.InvalidRepeatPassword.getFaName());
        ozv.setHasheRamzeOboor(ozvBiz.getHash(newPass));
        ozvBiz.update(ozv);
        Messagebox.show("کلمه عبور شما با موفقیت تغییر کرد.", "اتمام تغییر کلمه عبور", 1, "",
                new EventListener<Event>() {

                    @Override
                    public void onEvent(Event arg0) throws Exception {
                        navigate();
                    }
                });
    }

    @Listen("onClick = #btnBack")
    public void navigate() {
        Map<String, Object> args = new HashMap<String, Object>();
        NavigationUtil.goTo(WelcomePage.class, args);
    }

    @Override
    public String getPageAddress() {
        return "/WEB-INF/inner-pages/registeration/changePass.zul";
    }

}
