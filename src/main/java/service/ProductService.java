package service;

import dto.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Product> updateProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") Long id);
}
