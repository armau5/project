package ir.infosphere.sport.controller;


import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.ui.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;

//@RestController
@RequestMapping("/changePassPage")
public class ChangePassPage extends BasePage {

    @Autowired
    private OzvBiz ozvBiz;
    @Autowired
    private OzvEntity ozv;

    @RequestMapping("/save")
    public void save(String currentPass, String newPass, String passRepeat) {
        ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
        ozv = (OzvEntity) Sessions.getCurrent().getAttribute("user");

        if (ozvBiz.authenticate(ozv.getNameKarbari(), currentPass) == null){
            ErrorTypeEnum.InvalidValue.getFaName();
        }

        ozv.setHasheRamzeOboor(ozvBiz.getHash(newPass));
        ozvBiz.update(ozv);

    }


    @Override
    public String getPageAddress() {
        return null;
    }
}
