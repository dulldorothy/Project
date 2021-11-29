package com.alexander.domain.entity;

public class Message {
    private String email;
    private String lotName;
    private String buyerName;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Message() {
    }

    public static class MessageBuilder{
        Message message;

        public MessageBuilder() {
            this.message = new Message();
        }

        public MessageBuilder setLotName(String lotName)
        {
            message.setLotName(lotName);
            return this;
        }


        public MessageBuilder setEmail(String email)
        {
            message.setEmail(email);
            return this;
        }
        public MessageBuilder setBuyerName(String buyerName)
        {
            message.setBuyerName(buyerName);
            return this;
        }
        public MessageBuilder setId(int id)
        {
            message.setId(id);
            return this;
        }

        public Message create()
        {
            return message;
        }

    }
}
