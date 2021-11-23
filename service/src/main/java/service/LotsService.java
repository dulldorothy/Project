package service;



import domain.entity.Lot;
import service.exeption.ServiceExeption;

import java.util.List;
import java.util.Map;

public interface LotsService {
    List<Lot> getAllLots() throws ServiceExeption;
    List<Lot> getAllActiveLots(int offset, int recordsPerPage) throws ServiceExeption;
    List<Lot> getTagActiveLots(int offset, int recordsPerPage,String tag) throws ServiceExeption;
    int getNumberOfTagPages(int recordPerPage, String tag) throws ServiceExeption;
    int getNumberOfPages(int recordPerPage) throws ServiceExeption;
    Lot getLotByID(int id) throws ServiceExeption;
    boolean deleteLotByID(int id) throws ServiceExeption;
    boolean saveLot(Map<String,String> lotMap) throws ServiceExeption;
    boolean changeLotPriceByID(int id, int price) throws ServiceExeption;
    boolean changeLotStatusByID(int id, String status) throws ServiceExeption;
    boolean changeLotTitleByID(int id, String title) throws ServiceExeption;


}
