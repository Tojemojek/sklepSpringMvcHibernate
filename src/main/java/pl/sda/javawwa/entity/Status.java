package pl.sda.javawwa.entity;


import java.io.Serializable;

public enum Status implements Serializable {
    NEW("Nowe"),
    PAID("Opłacone"),
    SHIPPED("Wysłane"),
    DELIVERED("Dostarczone");

    private String displayedName;

    Status(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
