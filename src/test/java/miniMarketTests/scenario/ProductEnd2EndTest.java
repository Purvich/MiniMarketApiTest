package miniMarketTests.scenario;

import com.sun.org.glassfish.gmbal.Description;
import miniMarket.Params;
import miniMarket.dto.Product;
import miniMarket.enums.CategoryType;
import miniMarketTests.BaseTest;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.EOFException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Create, get, update and delete product")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductEnd2EndTest extends BaseTest {

    static Product product;
    static Long id;

    @Test
    @Description("Create product")
    @Order(1)
    void createProduct() throws IOException {
        product = new Product()
                .withTitle(Params.PRODUCT_TITLE)
                .withPrice(Params.PRODUCT_PRICE)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.createProduct(product).execute();

        assertThat(response.code()).isEqualTo(201);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getTitle()).isEqualTo(Params.PRODUCT_TITLE);
        assertThat(response.body().getPrice()).isEqualTo(Params.PRODUCT_PRICE);
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
        assertThat(response.body().getTitle()).isEqualTo(Params.PRODUCT_TITLE);
        assertThat(response.body().getPrice()).isEqualTo(Params.PRODUCT_PRICE);
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
    }

    @Test
    @Description("Update Product")
    @Order(3)
    void updateProduct() throws IOException {

        product = new Product()
                .withId(id)
                .withTitle(Params.NEW_PRODUCT_TITLE)
                .withPrice(Params.NEW_PRODUCT_PRICE)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.updateProduct(product).execute();

        assertThat(response.code()).isEqualTo(200);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getPrice()).isEqualTo(Params.NEW_PRODUCT_PRICE);
        assertThat(response.body().getTitle()).isEqualTo(Params.NEW_PRODUCT_TITLE);
        assertThat(response.body().getCategoryTitle()).isEqualTo(product.getCategoryTitle());
    }

    @Test
    @Description("Delete product")
    @Order(4)
    void deleteProduct() {
        Assertions.assertThrows(EOFException.class, () -> {
            productService.deleteProduct(id).execute();
        });
    }
}
