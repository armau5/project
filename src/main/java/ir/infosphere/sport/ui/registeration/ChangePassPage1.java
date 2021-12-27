package ir.infosphere.sport.ui.registeration;


import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.entity.OzvEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.zk.ui.Sessions;

@RestController
@RequestMapping("/ChangePass")
public class ChangePassPage1 {

    @Autowired
    private OzvBiz ozvBiz;
    @Autowired
    private OzvEntity ozv;


    @RequestMapping("/save")
    public String save1(String currentPass, String newPass, String repeatPass) {
        ozv = (OzvEntity) Sessions.getCurrent().getAttribute("user");
        if (!newPass.equals(repeatPass)) {
            return "اشتباه است";
        }

        if (ozvBiz.authenticate(ozv.getNameKarbari(), currentPass) == null) {
            return ExceptionWrapper.showMessage(new NameException());
        } else {

            ozv.setHasheRamzeOboor(ozvBiz.getHash(newPass));
            ozvBiz.update(ozv);
            return "ba movafaghiat anjam shud";

        }
    }
}
