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
        int index =-1;
        for (int i=0; i<listProducts.size(); i++) {
            if (listProducts.get(i).getId() == id) {
                index =i;
            }
        }
        listProducts.set(index,product);

    }

    @Override
    public void remove(int id) {
        int index =-1;
        for (int i=0; i<listProducts.size(); i++) {
            if (listProducts.get(i).getId() == id) {
                index =i;
            }
        }
        listProducts.remove(index);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> newList = new ArrayList<>();
        for (int i=0; i<listProducts.size(); i++) {
            if (listProducts.get(i).getName().equals(name)) {
                newList.add(listProducts.get(i));
            }
        }
        return newList;
    }

    @Override
    public Product findById(int id) {
        for (int i=0; i<listProducts.size(); i++) {
            if (listProducts.get(i).getId() == id) {
                return listProducts.get(i);
            }
        }
        return null;
    }


}
