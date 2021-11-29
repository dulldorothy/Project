package com.alexander.service;



import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.Page;
import com.alexander.service.exeption.ServiceException;

import java.util.List;
import java.util.Map;

public interface LotsService {
    Page<Lot> getAllLots() throws ServiceException;
    Page<Lot> getAllActiveLots(int offset, int recordsPerPage) throws ServiceException;
    Page<Lot> getTagActiveLots(int offset, int recordsPerPage,String tag) throws ServiceException;
    Page<Lot> getAllUserLots(int offset, int recordsPerPage, int userID) throws ServiceException;
    Page<Lot> getUserBookmarkLots(int off, int recordsPerPage, int userID) throws ServiceException;
    int getNumberOfTagPages(int recordPerPage, String tag) throws ServiceException;
    int getNUmberOfUserLotsPages(int recordsPerPage, int userID) throws ServiceException;
    int getNumberOfPages(int recordPerPage) throws ServiceException;
    Page<Lot> getLotByID(int id) throws ServiceException;
    boolean deleteLotByID(int id) throws ServiceException;
    boolean saveLot(Map<String,String> lotMap) throws ServiceException;
    boolean addLotToUserBookmarks(int userID, int lotID) throws ServiceException;
    boolean changeLotPriceByID(int id, int price) throws ServiceException;
    boolean changeLotStatusByID(int id, String status) throws ServiceException;
    boolean changeLotTitleByID(int id, String title) throws ServiceException;
    boolean changeLotDescriptionByID(int id, String title) throws ServiceException;
    boolean changeLotImageByID(int id, String encodedImage) throws ServiceException;


}
