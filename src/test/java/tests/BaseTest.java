package tests;

import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import service.CategoryService;
import service.ProductService;
import utils.RetrofitUtils;

public class BaseTest {

    static Retrofit retrofit;
    static CategoryService categoryService;
    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        retrofit = RetrofitUtils.getRetrofit();
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);
    }
}
