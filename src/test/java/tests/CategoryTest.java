package tests;

import dto.Category;
import enums.CategoryType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(value = CategoryType.class)
    void getFoodCategory(CategoryType category) throws IOException {
        Response<Category> response = categoryService.getCategory(category.getId()).execute();

        response
                .body()
                .getProducts()
                .forEach(e -> assertThat(e.getCategoryTitle()).isEqualTo(category.getTitle()));

        assertThat(response.code()).isEqualTo(200);
        assertThat(response.body().getTitle()).isEqualTo(category.getTitle());
    }
}
