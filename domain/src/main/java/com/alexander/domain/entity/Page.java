package com.alexander.domain.entity;

import java.util.List;

public class Page<T> {
    private int numberOfPages;
    private List<T> listOfItems;
    private Lot lot;
    private UserDTO userOwner;

    public Page(int numberOfPages, List<T> listOfItems, Lot lot, UserDTO userOwner) {
        this.numberOfPages = numberOfPages;
        this.listOfItems = listOfItems;
        this.lot = lot;
        this.userOwner = userOwner;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public UserDTO getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserDTO userOwner) {
        this.userOwner = userOwner;
    }


    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<T> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<T> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public static class PageBuilder<T> {
        private int numberOfPages;
        private List<T> listOfItems;
        private Lot lot;
        private UserDTO userOwner;

        public PageBuilder<T> setLot(Lot lot) {
            this.lot = lot;
            return this;
        }

        public PageBuilder<T> setUserOwner(UserDTO user) {
            this.userOwner = user;
            return this;
        }

        public PageBuilder<T> setNumberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }


        public PageBuilder<T> setListOfItems(List<T> listOfItems) {
            this.listOfItems = listOfItems;
            return this;
        }

        public Page<T> create() {
            return new Page<>(this.numberOfPages, this.listOfItems, this.lot, this.userOwner);
        }
    }


}
