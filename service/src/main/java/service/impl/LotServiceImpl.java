package service.impl;

import dao.database.exeptions.DAOExeption;
import dao.database.impl.DAOFactory;
import domain.entity.Lot;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.validator.ServiceValidator;


import java.util.List;
import java.util.Map;

public class LotServiceImpl implements LotsService {

    @Override
    public List<Lot> getAllLots() throws ServiceExeption {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = null;
        try {
            return daoFactory.getLotsDAO().getAll(1, 1);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get all lots from database", e);
        }
    }

    @Override
    public List<Lot> getAllActiveLots() throws ServiceExeption {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = null;
        try {
            return daoFactory.getLotsDAO().getActiveLots(1, 1);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get all active lots", e);
        }
    }

    @Override
    public boolean deleteLotByID(int id) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().deleteLotById(id);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to delete lot!", daoExeption);
        }
    }

    @Override
    public boolean saveLot(Map<String, String> lotMap) throws ServiceExeption {
        Lot lot = new Lot.LotBuilder()
                .setPrice(Integer.parseInt(lotMap.get("price")))
                .setTitle(lotMap.get("title"))
                .setUserOwnerID(Integer.parseInt(lotMap.get("user_owner_id")))
                .setDescription("description")
                .setTagList("tagList")
                .create();

        if (!ServiceValidator.validate(lot)) {
            throw new ServiceExeption("Lot validation failed");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return daoFactory.getLotsDAO().saveLot(lot);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to save lot!", daoExeption);
        }
    }

    @Override
    public boolean changeLotPriceByID(int id, int price) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().changeLotPriceById(id, price);

        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot price", e);
        }
    }

    @Override
    public boolean changeLotStatusByID(int id, String status) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().changeLotStatusById(id, status);

        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot status" ,e);
        }
    }

    @Override
    public boolean changeLotTitleByID(int id, String title) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            daoFactory.getLotsDAO().changeLotTitleById(id,title);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot title",e);
        }

        return false;
    }


}
