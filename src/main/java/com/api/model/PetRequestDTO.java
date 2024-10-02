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
public class PetRequestDTO {
    public int id;
    public CategoryDTO category;
    public String name;
    public ArrayList<String> photoUrls;
    public ArrayList<TagDTO> tags;
    public String status;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDTO{
        public int id;
        public String name;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagDTO{
        public int id;
        public String name;
    }
}
