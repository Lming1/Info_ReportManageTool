package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> get() {
        return productRepository.findById(1);
    }


    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Product product : productRepository.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("ID", product.getId());
            item.put("NAME", product.getName());
            item.put("PRICE", product.getPrice());
            item.put("QUANTITY", product.getQuantity());
            item.put("CATEGORY_NAME", product.getCategoryName());
            result.add(item);
        }
        return result;
    }


    public List<Product> getByCategory() {
        return productRepository.findAllByCategoryCode(1);
    }

}
