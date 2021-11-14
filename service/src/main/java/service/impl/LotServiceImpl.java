package service.impl;

import dao.database.impl.DAOFactory;
import domain.entity.Lot;
import service.LotsService;
import service.validator.ServiceValidator;


import java.util.List;
import java.util.Map;

public class LotServiceImpl implements LotsService {

    @Override
    public List<Lot> getAllLots() {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = daoFactory.getLotsDAO().getAll();
        return lots;
    }

    @Override
    public List<Lot> getAllActiveLots() {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = daoFactory.getLotsDAO().getActiveLots();
        return lots;
    }

    @Override
    public boolean deleteLotByID(int id) {

        DAOFactory daoFactory = new DAOFactory();
        if (daoFactory.getLotsDAO().deleteLotById(id))
        {
            return true;
        }

        return false;


    }

    @Override
    public boolean saveLot(Map<String, String> lotMap) {
        Lot lot = new Lot.LotBuilder()
                .setPrice(Integer.parseInt(lotMap.get("price")))
                .setTitle(lotMap.get("title"))
                .setUserOwnerID(Integer.parseInt(lotMap.get("user_owner_id")))
                .setDescription("description")
                .setTagList("tagList")
                .create();

        if (!ServiceValidator.validate(lot))
        {
            return false;
        }
        DAOFactory daoFactory = new DAOFactory();

        if (daoFactory.getLotsDAO().saveLot(lot))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean changeLotPriceByID(int id, int price) {
        DAOFactory daoFactory = new DAOFactory();
        if (daoFactory.getLotsDAO().changeLotPriceById(id,price))
        {
            return true;
        }
        return false;

    }

    @Override
    public boolean changeLotStatusByID(int id, String status) {
        DAOFactory daoFactory = new DAOFactory();
        if (daoFactory.getLotsDAO().changeLotStatusById(id,status))
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean changeLotTitleByID(int id, String title) {
        DAOFactory daoFactory = new DAOFactory();
        if (daoFactory.getLotsDAO().changeLotTitleById(id,title))
        {
            return true;
        }
        return false;
    }


}
