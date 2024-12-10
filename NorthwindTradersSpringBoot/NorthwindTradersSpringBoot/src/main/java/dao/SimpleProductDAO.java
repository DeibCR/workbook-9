package dao;

import model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SimpleProductDAO implements ProductDAO {

    private List<Product> products;

    public SimpleProductDAO(List<Product> products) {
        this.products = products;

        this.products.add(new Product(001,"PS5","Tech",600.00));
        this.products.add(new Product(002,"Xbox","Tech",800.00));
        this.products.add(new Product(003,"PC","Tech",1000.00));
    }



    @Override
    public void add(Product product) {
        products.add(product);

    }

    @Override
    public List<Product> getAll() {
        return products;

    }
}
