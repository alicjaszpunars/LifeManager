package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySumDto {
    private String category;
    private Double total;

    public CategorySumDto(String category, Double total) {
        this.category = category;
        this.total = total;
    }
}
