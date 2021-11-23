package dto;

import lombok.*;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private Integer price;
    private String title;
    private String categoryTitle;
}
