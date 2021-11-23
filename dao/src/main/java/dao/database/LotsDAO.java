package dao.database;


import dao.database.exeptions.DAOExeption;
import domain.entity.Lot;

import java.util.List;

public interface LotsDAO {
    boolean saveLot(Lot item) throws DAOExeption;
    boolean deleteLotById(int id) throws DAOExeption;
    boolean addLotToUserBookmark(int userID, int lotID) throws DAOExeption;
    List<Lot> getAll(int offset, int recordPerPage) throws DAOExeption;
    List<Lot> getActiveLots(int offset, int recordPerPage) throws DAOExeption;
    List<Lot> getLotsByTag(int offset, int recordPerPage, String tag) throws DAOExeption;
    List<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws DAOExeption;
    List<Lot> getAllUserBookmarkLots(int offset, int recordsPerPage, int userID) throws DAOExeption;
    Lot getLotByID(int id) throws DAOExeption;
    int getNumberOfUserLots(int userID) throws DAOExeption;

    String getIDOfUserBookmarkLots(int userID) throws DAOExeption;
    int getNumberOfActiveLotsByTag(String tag) throws DAOExeption;
    int getNumberOfActiveLots() throws DAOExeption;
    boolean changeLotPriceById(int id, int price) throws DAOExeption;
    boolean changeLotTitleById(int id, String title) throws DAOExeption;
    boolean changeLotStatusById(int id, String status) throws DAOExeption;




}
