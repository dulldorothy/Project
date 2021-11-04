package service.entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lot {
    private final int id;
    private final int userOwnerID;
    private String title;
    private int price;
    private String type;
    private String status;
    private Timestamp time_of_expiration;
    private String tagList;

    public Lot(int id, int user_owner_id, String title, int price, String type, String status,
               Timestamp time_of_expiration, String tagList) {
        this.id = id;
        this.userOwnerID = user_owner_id;
        this.title = title;
        this.price = price;
        this.type = type;
        this.status = status;
        this.time_of_expiration = time_of_expiration;
        this.tagList = tagList;
    }


    public Lot(int id, int user_owner_id) {
        this.id = id;
        this.userOwnerID = user_owner_id;
        this.title = "Untitled";
    }

    public int getId() {
        return id;
    }

    public int getUserOwnerID() {
        return userOwnerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTime_of_expiration() {
        return time_of_expiration;
    }

    public void setTime_of_expiration(Timestamp time_of_expiration) {
        this.time_of_expiration = time_of_expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return id == lot.id && userOwnerID == lot.userOwnerID
                && price == lot.price
                && Objects.equals(title, lot.title)
                && Objects.equals(type, lot.type) && Objects.equals(status, lot.status)
                && Objects.equals(time_of_expiration, lot.time_of_expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userOwnerID, title, price, type, status, time_of_expiration);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", user_owner_id=" + userOwnerID +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", time_of_expiration=" + time_of_expiration +
                '}';
    }
    public List<Object> LotToListOfParameters()
    {
        List<Object> resultList = new ArrayList<>();
        resultList.add(userOwnerID);
        resultList.add(title);
        resultList.add(price);
        resultList.add(type);
        resultList.add(time_of_expiration);
        resultList.add(tagList);
        return resultList;
    }

}
