package service;



import domain.entity.Lot;
import service.exeption.ServiceExeption;

import java.util.List;
import java.util.Map;

public interface LotsService {
    List<Lot> getAllLots() throws ServiceExeption;
    List<Lot> getAllActiveLots() throws ServiceExeption;
    boolean deleteLotByID(int id) throws ServiceExeption;
    boolean saveLot(Map<String,String> lotMap) throws ServiceExeption;
    boolean changeLotPriceByID(int id, int price) throws ServiceExeption;
    boolean changeLotStatusByID(int id, String status) throws ServiceExeption;
    boolean changeLotTitleByID(int id, String title) throws ServiceExeption;


}
