package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreOrderRequestDTO {
    private int id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
