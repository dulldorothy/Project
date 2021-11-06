package service;



import domain.entity.Lot;

import java.util.List;

public interface LotsService {
    List<Lot> getAllLots();
    List<Lot> getAllActiveLots();
    boolean deleteLotByID(int id);
    boolean saveLot(List<Object> lotParameters, int userID);
    boolean changeLotPriceByID(int id, int price);
    boolean changeLotStatusByID(int id, String status);
    boolean changeLotTitleByID(int id, String title);


}
