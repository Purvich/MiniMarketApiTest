package miniMarketTests.product.getProduct;

import miniMarket.dto.Product;
import miniMarketTests.BaseTest;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetProductsTest extends BaseTest {

    @Test
    void getProducts() throws IOException {

        Response<List<Product>> response = productService.getProducts().execute();

        assertThat(response.code()).isEqualTo(200);
    }
}
