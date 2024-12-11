package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SimpleProductDAO implements ProductDAO {

    private List<Product> products = new ArrayList<>();

    public SimpleProductDAO() {



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
