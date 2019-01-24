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

    //TODO 이거 바꿀 방법만 찾으면 끝날 듯.. 모델을 사용자가 임의로 잡는 방법은 없나..

    public List<Map<String, Object>> productFindAll() {
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

    public Collection<Product> productTest() {
        return null;
    }


}
