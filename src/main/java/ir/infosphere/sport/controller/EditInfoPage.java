package ir.infosphere.sport.controller;

import ir.infosphere.sport.biz.*;
import ir.infosphere.sport.constraint.ZipCodeConstraint;
import ir.infosphere.sport.enm.ErrorTypeEnum;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/editInfoPage")
public class EditInfoPage {

    @Autowired
    private NahiyeBiz nahiyeBiz;
    @Autowired
    private OzvBiz ozvBiz;
    @Autowired
    private KodBiz kodBiz;
    @Autowired
    private ReshteyeVarzeshiBiz reshteyeVarzeshiBiz;
    @Autowired
    private TimBiz timBiz;
    @Autowired
    private SoaleAmniatiBiz soaleAmniatiBiz;
    @Autowired
    private OzvEntity ozv;



    @RequestMapping("/register")
    public String register(Integer shahrId, String address, String postalCode, String email, String mobilePish,
                           String mobilePas, String phonePish, String phonePas, Short favoriteTeamId,
                           Short favoriteSportFieldId, Short soaleamniatiID, String pasokheAmniati, String fileName,
                           byte[] picture) {


        if (!ZipCodeConstraint.postalCodeIsValid(postalCode)) {
            return ErrorTypeEnum.InvalidValue.getFaName();

        }
        AksEntity aksEntity = ozv.getAks();
        if (fileName != null) {
            aksEntity = AksBiz.changeUserAks(aksEntity, fileName, picture);
        }

        ozv.setSoaleAmniati(soaleAmniatiBiz.getSecurityQuestionById(soaleamniatiID));
        ozv.setHashePasokheSoaleAmniati(ozvBiz.getHash(pasokheAmniati));

        ozv.setNahiye(nahiyeBiz.getNahiyeById(shahrId));
        ozv.setAks(aksEntity);
        ozv.setShomareyeHamrah(mobilePish + mobilePas);

        ozv.setShomareyeSabet(phonePish + "-" + phonePas);
        ozv.setKodePosti(postalCode);
        ozv.setAdres(address);
        ozv.setPosteElectronik(email);

        if (favoriteTeamId != null)
            ozv.setTimeMoredeAlaghe(timBiz.getTimById(favoriteTeamId));

        if (favoriteSportFieldId != null)
            ozv.setReshteyeVarzeshiyeMoredeAlaghe(reshteyeVarzeshiBiz.getReshtehById(favoriteSportFieldId));

        KodEntity vaziateKarbar;
        if (PermissionUtil.getCurrentPortal().getId().toString().equals(Constants.TicketPortalID)) {
            vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_TaeedShode);

        } else {
            vaziateKarbar = kodBiz.getKodEntity(Constants.VaziateKarbar, Constants.VaziateKarbar_BarresiNashode);

        }
        ozv.setTaeedeAks(vaziateKarbar);
        ozvBiz.update(ozv);

        //cmbShahr.setValue(ozv.getNahiye().getNameNahiye());
        return "";
    }
}

