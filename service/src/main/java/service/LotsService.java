package service;



import domain.entity.Lot;

import java.util.List;
import java.util.Map;

public interface LotsService {
    List<Lot> getAllLots();
    List<Lot> getAllActiveLots();
    boolean deleteLotByID(int id);
    boolean saveLot(Map<String,String> lotMap);
    boolean changeLotPriceByID(int id, int price);
    boolean changeLotStatusByID(int id, String status);
    boolean changeLotTitleByID(int id, String title);


}
