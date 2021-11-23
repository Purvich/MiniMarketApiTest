package miniMarket.enums;

import lombok.Getter;

public enum CategoryType {
    FOOD(1, "Food"),
    ELECTRONIC(2, "Electronic"),
    FURNITURE(3, "Furniture");

    @Getter
    private Integer id;
    @Getter
    private String title;

    CategoryType(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
