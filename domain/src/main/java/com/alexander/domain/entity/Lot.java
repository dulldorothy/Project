package com.alexander.domain.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lot implements Serializable {
    private int id;
    private int userOwnerID;
    private String title;
    private int price;
    private String type;
    private String status;
    private Timestamp time_of_expiration;
    private String tagList;
    private String description;
    private String image;

    public String getTagList() {
        return tagList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Lot() {

    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUserOwnerID(int userOwnerID) {
        this.userOwnerID = userOwnerID;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
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



    public static class LotBuilder {
        Lot lot;

        public LotBuilder() {
            lot = new Lot();
        }

        public LotBuilder setTitle(String title) {
            lot.setTitle(title);
            return this;
        }

        public LotBuilder setPrice(int price) {
            lot.setPrice(price);
            return this;
        }

        public LotBuilder setId(int id) {
            lot.setId(id);
            return this;
        }

        public LotBuilder setStatus(String status) {
            lot.setStatus(status);
            return this;
        }

        public LotBuilder setUserOwnerID(int id) {
            lot.setUserOwnerID(id);
            return this;
        }

        public LotBuilder setType(String type) {
            lot.setType(type);
            return this;
        }
        public LotBuilder setTimeOfExpiration(Timestamp timeOfExpiration)
        {
            lot.setTime_of_expiration(timeOfExpiration);
            return this;
        }
        public LotBuilder setTagList(String tags)
        {
            lot.setTagList(tags);
            return this;
        }
        public LotBuilder setDescription(String description)
        {
            lot.setDescription(description);
            return this;
        }
        public LotBuilder setImage(String image)
        {
            lot.setImage(image);
            return this;
        }
        public Lot create() {
            return lot;
        }


    }


}
