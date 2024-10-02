package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private int id;
    private String name;
    private Category category;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category{
        public int id;
        public String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag{
        public int id;
        public String name;
    }
}
