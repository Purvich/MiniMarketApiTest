package dto;

import lombok.*;

import java.util.*;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String title;
    private List<Product> products = new ArrayList<>();
}
