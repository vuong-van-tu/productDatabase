package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    public static List<Product> listProducts;

    static {
        listProducts = new ArrayList<>();
        listProducts.add(new Product(1, "acer", 20, "maytinhbotui", "acer"));
        listProducts.add(new Product(2, "asus", 22, "maytinhban", "asus"));
        listProducts.add(new Product(3, "macbook", 50, "a", "apple"));
        listProducts.add(new Product(4, "hp", 14, "b", "hp"));
        listProducts.add(new Product(5, "msi", 200, "c", "msi"));
        listProducts.add(new Product(6, "dell", 18, "d", "dell"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(listProducts);
    }

    @Override
    public void save(Product product) {
        listProducts.add(product);
    }

    @Override
    public void update(int id, Product product) {
        listProducts.add(id, product);
    }

    @Override
    public void remove(int id) {
        listProducts.remove(id);
    }

    @Override
    public Product findByName(String name) {
        int index = listProducts.indexOf(name);
        return listProducts.get(index);
    }

}
