package miniMarketTests.product.createProduct;

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

@DisplayName("Json fields filling check")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveDataTest extends BaseTest {

    static Product product;
    static Long id;

    @Test
    @Description("Product without title")
    @Order(1)
    void productWithoutTitle() throws IOException {
        product = new Product()
                .withPrice(Params.PRODUCT_PRICE)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Response<Product> response = productService.createProduct(product).execute();

        id = response.body().getId();

        assertThat(response.code()).isEqualTo(201);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getPrice()).isEqualTo(Params.PRODUCT_PRICE);
        assertThat(response.body().getTitle()).isNull();
        assertThat(response.body().getCategoryTitle()).isEqualTo(CategoryType.FOOD.getTitle());
    }

    @Test
    @Description("Product without price")
    @Order(2)
    void productWithoutPrice() throws IOException {
        product = new Product()
                .withTitle(Params.PRODUCT_TITLE)
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle());

        Response<Product> response = productService.createProduct(product).execute();

        id = response.body().getId();

        assertThat(response.code()).isEqualTo(201);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getPrice()).isEqualTo(0);
        assertThat(response.body().getTitle()).isEqualTo(Params.PRODUCT_TITLE);
        assertThat(response.body().getCategoryTitle()).isEqualTo(CategoryType.ELECTRONIC.getTitle());
    }

    @Test
    @Description("Product with all data")
    @Order(3)
    void productWithAllData() throws IOException {
        product = new Product()
                .withTitle(Params.PRODUCT_TITLE)
                .withPrice(Params.PRODUCT_PRICE)
                .withCategoryTitle(CategoryType.FURNITURE.getTitle());

        Response<Product> response = productService.createProduct(product).execute();

        id = response.body().getId();

        assertThat(response.code()).isEqualTo(201);
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getTitle()).isEqualTo(Params.PRODUCT_TITLE);
        assertThat(response.body().getPrice()).isEqualTo(Params.PRODUCT_PRICE);
        assertThat(response.body().getCategoryTitle()).isEqualTo(CategoryType.FURNITURE.getTitle());

        System.out.println(response.code());
    }

    @AfterEach
    void deleteProduct() {
        Assertions.assertThrows(EOFException.class, () -> {
            productService.deleteProduct(id).execute();
        });
    }
}
