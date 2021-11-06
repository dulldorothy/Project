package service.impl;

import dao.database.impl.DAOFactory;
import domain.entity.Lot;
import service.LotsService;


import java.util.List;

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
    public boolean saveLot(List<Object> lotParameters, int userID) {
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
