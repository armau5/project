package ir.infosphere.sport.controller;

import ir.infosphere.sport.biz.OzvBiz;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.ui.common.BasePage;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/retrievePassPage")
public class RetrievePassPage extends BasePage {

    @Autowired
    private OzvBiz ozvBiz;

    @RequestMapping("/")
    public void validateUsername(String username){
        ozvBiz = (OzvBiz) appContext.getBean("ozvBiz");
        if(!ozvBiz.IsUsernameValid(username)) {
            ErrorTypeEnum.InvalidValue.getFaName();
        }

        DetachedCriteria criteria = DetachedCriteria.forClass(OzvBiz.class);
        criteria.add(Restrictions.like("nameKarbari", username));
        ozvBiz.retrieveByCriteria(criteria).get(0);

    }

    @Override
    public String getPageAddress() {
        return null;
    }
}
