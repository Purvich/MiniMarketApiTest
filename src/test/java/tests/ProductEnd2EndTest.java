package tests;

import com.sun.org.glassfish.gmbal.Description;
import dto.Product;
import enums.CategoryType;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Create, get, update and delete product")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductEnd2EndTest extends BaseTest {

    static Product product;
    static Long id;

    private static final String firstProduct = "Bread";
    private static final String updateProduct = "Yesterday bread";
    private static final int firstPrice = 100;
    private static final int updatePrice = 50;

    @Test
    @Description("Create product")
    @Order(1)
    void createProduct() throws IOException {
        product = new Product()
                .withTitle(firstProduct)
                .withPrice(firstPrice)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.createProduct(product).execute();

        assertThat(response.code()).isEqualTo(201);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());

        id = response.body().getId();
    }

    @Test
    @Description("Get product")
    @Order(2)
    void getProduct() throws IOException {
        Response<Product> response = productService.getProduct(id).execute();

        assertThat(response.code()).isEqualTo(200);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getTitle()).isEqualTo(product.getTitle());
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
    }

    @Test
    @Description("Update Product")
    @Order(3)
    void updateProduct() throws IOException {

        product = new Product()
                .withId(id)
                .withTitle(updateProduct)
                .withPrice(updatePrice)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.updateProduct(product).execute();

        assertThat(response.code()).isEqualTo(200);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getPrice()).isEqualTo(updatePrice);
        assertThat(response.body().getTitle()).isEqualTo(updateProduct);
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
    }

    @Test
    @Description("Delete product")
    @Order(4)
    void deleteProduct() throws IOException {
        Response<Product> response = productService.deleteProduct(id).execute();
        assertThat(response.code()).isEqualTo(200);
    }
}
