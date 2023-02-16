package com.itprofix.investments.quarkus;

public class Error {
    private String id;
    private String fault;


    public Error(String id, String fault) {
        this.id = id;
        this.fault = fault;
    }

    public Error() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    @Override
    public String toString() {
        return "Error{" +
                "fault='" + fault + '\'' +
                '}';
    }
}
