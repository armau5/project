package ir.infosphere.sport.controller;

import ir.infosphere.sport.biz.OzvBiz;

import ir.infosphere.sport.entity.OzvEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


//@RestController
@RequestMapping("/newPass")
public class NewPass {

    @Autowired
    private OzvBiz ozvBiz;
    @Autowired
    private OzvEntity ozv;

    @RequestMapping("/save")
    public void save(String newPass, String newPassRepea) {

        ozv.setHasheRamzeOboor(ozvBiz.getHash(newPass));
        ozvBiz.update(ozv);

    }

}
