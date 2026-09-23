package com.vdev.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockRequestDTO {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be > 0")
    private Integer amount;
    public Integer getAmount(){
        return amount;
    }
    public void setAmount(Integer amount){
        this.amount = amount;
    }
}
