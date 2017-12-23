package pl.sda.javawwa.dao;

import pl.sda.javawwa.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getTopProducts(int count);
    Product getProductById(int productId);
    List<String> getAllProductCategories();
    List<Product> getProductsByCategory(String category);

}
