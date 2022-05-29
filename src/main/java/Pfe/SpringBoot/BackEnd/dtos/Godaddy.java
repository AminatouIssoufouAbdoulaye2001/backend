package Pfe.SpringBoot.BackEnd.dtos;

import lombok.Data;
@Data
public class Godaddy {
    private boolean available;
    private String currency;
    private boolean definitive;
    private String domain;
    private int period;
    private long price;
}
