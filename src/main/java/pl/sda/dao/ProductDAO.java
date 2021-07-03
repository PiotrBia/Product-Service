package pl.sda.dao;

import org.springframework.stereotype.Component;
import pl.sda.model.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {

    private List<Product> products = new ArrayList<>();

    public ProductDAO() {
        Product tShirt = new Product(1, "T-shirt", "Cool T-shirt", 29.99, "tshirt.jpg");
        Product longPants = new Product(2, "Long Pants", "Regular long classic pants", 129.99, "longpants.jpg");
        Product cap = new Product(3, "cap", "Baseball cap", 9.99, "cap.jpg");
        products.add(tShirt);
        products.add(longPants);
        products.add(cap);
    }

    public Product getById(long id) {
        Optional<Product> any = products.stream().filter(product -> id == product.getId()).findAny();
        return any.get();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(product -> product.getId() == id);

    }
}
