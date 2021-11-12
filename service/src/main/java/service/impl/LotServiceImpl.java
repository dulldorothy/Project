package service.impl;

import dao.database.impl.DAOFactory;
import domain.entity.Lot;
import service.LotsService;


import java.util.List;
import java.util.Map;

public class LotServiceImpl implements LotsService {

    @Override
    public List<Lot> getAllLots() {
        return null;
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
        return false;
    }

    @Override
    public boolean saveLot(Map<String, String> lotMap) {
        Lot lot = new Lot.LotBuilder()
                .setPrice(Integer.parseInt(lotMap.get("price")))
                .setTitle(lotMap.get("title"))
                .setUserOwnerID(Integer.parseInt(lotMap.get("user_owner_id")))
                .setDescription("description")
                .create();

        return false;
    }

    @Override
    public boolean changeLotPriceByID(int id, int price) {
        return false;
    }

    @Override
    public boolean changeLotStatusByID(int id, String status) {
        return false;
    }

    @Override
    public boolean changeLotTitleByID(int id, String title) {
        return false;
    }


}
