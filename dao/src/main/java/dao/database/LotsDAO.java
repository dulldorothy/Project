package dao.database;


import domain.entity.Lot;

import java.util.List;

public interface LotsDAO {
    boolean saveLot(Lot item);
    boolean deleteLotById(int id);
    List<Lot> getAll();
    List<Lot> getActiveLots();
    boolean changeLotPriceById(int id, int price);
    boolean changeLotTitleById(int id, String title);
    boolean changeLotStatusById(int id, String status);




}
