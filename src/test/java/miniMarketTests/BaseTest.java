package miniMarketTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import miniMarket.service.CategoryService;
import miniMarket.service.ProductService;
import miniMarket.utils.RetrofitUtils;

public class BaseTest {

    public static Retrofit retrofit;
    public static CategoryService categoryService;
    public static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        retrofit = RetrofitUtils.getRetrofit();
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);
    }
}
