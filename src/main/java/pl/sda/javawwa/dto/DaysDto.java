package pl.sda.javawwa.dto;

import java.time.LocalDateTime;

public class DaysDto {

    private Integer daysAfter;
    private LocalDateTime dateAfter;

    public Integer getDaysAfter() {
        return daysAfter;
    }

    public void setDaysAfter(Integer daysAfter) {
        this.daysAfter = daysAfter;
    }

    public LocalDateTime getDateAfter() {
        return dateAfter;
    }

    public void setDateAfter(LocalDateTime dateAfter) {
        this.dateAfter = dateAfter;
    }
}
