package com.alexander.dao.database;


import com.alexander.dao.database.exeptions.DAOExeption;
import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;

import java.util.List;

public interface LotsDAO {
    boolean saveLot(Lot item) throws DAOExeption;
    boolean deleteLotById(int id) throws DAOExeption;
    boolean addLotToUserBookmark(int userID, int lotID) throws DAOExeption;
    Page<Lot> getAll(int offset, int recordPerPage) throws DAOExeption;
    Page<Lot> getActiveLots(int offset, int recordPerPage) throws DAOExeption;
    Page<Lot> getLotsByTag(int offset, int recordPerPage, String tag) throws DAOExeption;
    Page<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws DAOExeption;
    Page<Lot> getAllUserBookmarkLots(int offset, int recordsPerPage, int userID) throws DAOExeption;
    Page<Lot> getLotPageByID(int id) throws DAOExeption;
    int getNumberOfUserLots(int userID) throws DAOExeption;
    int getNumberOfActiveLotsByTag(String tag) throws DAOExeption;
    int getNumberOfActiveLots() throws DAOExeption;
    boolean changeLotPriceById(int id, int price) throws DAOExeption;
    boolean changeLotTitleById(int id, String title) throws DAOExeption;
    boolean changeLotStatusById(int id, String status) throws DAOExeption;
    boolean changeLotDescriptionByID(int id,String description) throws DAOExeption;
    boolean changeLotImageByID(int id, String encodedImage) throws DAOExeption;



}
