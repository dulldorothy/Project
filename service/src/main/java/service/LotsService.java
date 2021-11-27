package service;



import domain.entity.Lot;
import service.exeption.ServiceException;

import java.util.List;
import java.util.Map;

public interface LotsService {
    List<Lot> getAllLots() throws ServiceException;
    List<Lot> getAllActiveLots(int offset, int recordsPerPage) throws ServiceException;
    List<Lot> getTagActiveLots(int offset, int recordsPerPage,String tag) throws ServiceException;
    List<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws ServiceException;
    List<Lot> getUserBookmarkLots(int off, int recordsPerPage, int userID) throws ServiceException;
    int getNumberOfTagPages(int recordPerPage, String tag) throws ServiceException;
    int getNUmberOfUserLotsPages(int recordsPerPage, int userID) throws ServiceException;
    int getNumberOfPages(int recordPerPage) throws ServiceException;
    int getNumberOfUserBookmarkLots(int recordsPerPage, int userID) throws ServiceException;
    Lot getLotByID(int id) throws ServiceException;
    boolean deleteLotByID(int id) throws ServiceException;
    boolean saveLot(Map<String,String> lotMap) throws ServiceException;
    boolean addLotToUserBookmarks(int userID, int lotID) throws ServiceException;
    boolean changeLotPriceByID(int id, int price) throws ServiceException;
    boolean changeLotStatusByID(int id, String status) throws ServiceException;
    boolean changeLotTitleByID(int id, String title) throws ServiceException;


}
