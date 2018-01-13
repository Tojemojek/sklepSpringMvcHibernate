package pl.sda.javawwa.dao;

import pl.sda.javawwa.entity.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductDao {

    List<Product> getTopProducts(int count);

    Product getProductById(int productId);

    List<String> getAllProductCategories();

    List<Product> getProductsByCategory(String category);

    Map<Integer, Product> getProductsByIdIn(Collection<Integer> productsId);
}
