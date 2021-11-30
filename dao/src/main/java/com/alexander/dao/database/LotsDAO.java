package com.alexander.dao.database;


import com.alexander.dao.database.exeptions.DAOException;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;

public interface LotsDAO {
    boolean saveLot(Lot item) throws DAOException;
    boolean deleteLotById(int id) throws DAOException;
    boolean addLotToUserBookmark(int userID, int lotID) throws DAOException;
    Page<Lot> getAll(int offset, int recordPerPage) throws DAOException;
    Page<Lot> getActiveLots(int offset, int recordPerPage) throws DAOException;
    Page<Lot> getLotsByTag(int offset, int recordPerPage, String tag) throws DAOException;
    Page<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws DAOException;
    Page<Lot> getAllUserBookmarkLots(int offset, int recordsPerPage, int userID) throws DAOException;
    Page<Lot> getLotPageByID(int id) throws DAOException;
    int getNumberOfUserLots(int userID) throws DAOException;
    int getNumberOfActiveLotsByTag(String tag) throws DAOException;
    int getNumberOfActiveLots() throws DAOException;
    boolean changeLotPriceById(int id, int price) throws DAOException;
    boolean changeLotTitleById(int id, String title) throws DAOException;
    boolean changeLotStatusById(int id, String status) throws DAOException;
    boolean changeLotDescriptionByID(int id,String description) throws DAOException;
    boolean changeLotImageByID(int id, String encodedImage) throws DAOException;



}
