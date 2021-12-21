package com.alexander.service.impl;

import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.dao.database.impl.DAOFactory;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.service.LotsService;
import com.alexander.service.exeption.ServiceException;
import com.alexander.service.validator.ServiceValidator;

import java.util.Map;

import static com.alexander.domain.fields.UserFields.*;

public class LotServiceImpl implements LotsService {
    private DAOFactory daoFactory;

    public LotServiceImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Page<Lot> getAllLots() throws ServiceException {
        try {
            return daoFactory.getLotsDAO().getAll(1, 1);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all lots from database", e);
        }
    }

    @Override
    public Page<Lot> getAllActiveLots(int offset, int recordsPerPage) throws ServiceException {
        try {
            return daoFactory.getLotsDAO().getActiveLots(offset, recordsPerPage);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all active lots", e);
        }
    }

    @Override
    public Page<Lot> getTagActiveLots(int offset, int recordsPerPage, String tag) throws ServiceException {
        ServiceValidator.validate(tag);
        try {
            return daoFactory.getLotsDAO().getLotsByTag(offset, recordsPerPage, tag);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all active lots", e);
        }
    }

    @Override
    public Page<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws ServiceException {
        ServiceValidator.validate(userID);
        try {
            return daoFactory.getLotsDAO().getAllUserLots(offset, recordsPerPage, userID);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to get user lots");
        }
    }

    @Override
    public Page<Lot> getUserBookmarkLots(int off, int recordsPerPage, int userID) throws ServiceException {
        ServiceValidator.validate(userID);
        try {
            return daoFactory.getLotsDAO().getAllUserBookmarkLots(off, recordsPerPage, userID);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to get user bookmark lots");
        }
    }

    @Override
    public int getNumberOfTagPages(int recordPerPage, String tag) throws ServiceException {
        ServiceValidator.validate(tag);
        try {
            int result = daoFactory.getLotsDAO().getNumberOfActiveLotsByTag(tag);
            return (int) Math.ceil(result / (double) recordPerPage);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to get number of records", daoException);
        }
    }

    @Override
    public int getNUmberOfUserLotsPages(int recordPerPage, int userID) throws ServiceException {
        ServiceValidator.validate(userID);
        try {
            int result = daoFactory.getLotsDAO().getNumberOfUserLots(userID);
            return (int) Math.ceil(result / (double) recordPerPage);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to get number of records", daoException);
        }
    }

    @Override
    public int getNumberOfPages(int recordPerPage) throws ServiceException {
        try {
            int result = daoFactory.getLotsDAO().getNumberOfActiveLots();
            return (int) Math.ceil(result / (double) recordPerPage);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to get number of records", daoException);
        }
    }


    @Override
    public Page<Lot> getLotByID(int id) throws ServiceException {
        ServiceValidator.validate(id);
        try {
            return daoFactory.getLotsDAO().getLotPageByID(id);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to select lot", daoException);
        }
    }

    @Override
    public boolean deleteLotByID(int id) throws ServiceException {
        ServiceValidator.validate(id);
        try {
            return daoFactory.getLotsDAO().deleteLotById(id);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to delete lot!", daoException);
        }
    }

    @Override
    public boolean saveLot(Map<String, String> lotMap) throws ServiceException {
        Lot lot = new Lot.LotBuilder()
                .setPrice((int) Math.floor(Double.parseDouble(lotMap.get(PRICE)) * 100))
                .setTitle(lotMap.get(TITLE))
                .setUserOwnerID(Integer.parseInt(lotMap.get(OWNER_ID)))
                .setDescription(lotMap.get(DESCRIPTION))
                .setStatus(ACTIVE)
                .setTagList(lotMap.get(TAG_LIST))
                .setImage(lotMap.get(ENCODED_IMAGE))
                .create();
        ServiceValidator.validate(lot);

        try {
            return daoFactory.getLotsDAO().saveLot(lot);
        } catch (DAOException daoException) {
            throw new ServiceException("Failed to save lot!", daoException);
        }
    }

    @Override
    public boolean addLotToUserBookmarks(int userID, int lotID) throws ServiceException {
        ServiceValidator.validate(userID, lotID);
        try {
            return daoFactory.getLotsDAO().addLotToUserBookmark(userID, lotID);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot status", e);
        }
    }

    @Override
    public boolean changeLotPriceByID(int id, int price) throws ServiceException {
        ServiceValidator.validate(id, price);
        try {
            return daoFactory.getLotsDAO().changeLotPriceById(id, price);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot price", e);
        }
    }

    @Override
    public boolean changeLotStatusByID(int id, String status) throws ServiceException {
        ServiceValidator.validate(id);
        ServiceValidator.validate(status);
        try {
            return daoFactory.getLotsDAO().changeLotStatusById(id, status);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot status", e);
        }
    }

    @Override
    public boolean changeLotTitleByID(int id, String title) throws ServiceException {
        ServiceValidator.validate(id);
        ServiceValidator.validate(title);
        try {
            daoFactory.getLotsDAO().changeLotTitleById(id, title);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot title", e);
        }
        return false;
    }

    @Override
    public boolean changeLotDescriptionByID(int id, String description) throws ServiceException {
        ServiceValidator.validate(id);
        ServiceValidator.validate(description);
        try {
            daoFactory.getLotsDAO().changeLotDescriptionByID(id, description);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot description", e);
        }
        return false;
    }

    @Override
    public boolean changeLotImageByID(int id, String encodedImage) throws ServiceException {
        ServiceValidator.validate(id);
        ServiceValidator.validate(encodedImage);
        try {
            daoFactory.getLotsDAO().changeLotImageByID(id, encodedImage);
        } catch (DAOException e) {
            throw new ServiceException("Failed to change lot description", e);
        }
        return false;
    }


}
