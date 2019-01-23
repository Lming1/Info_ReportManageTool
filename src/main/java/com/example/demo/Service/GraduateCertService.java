package com.example.demo.Service;


import com.example.demo.Model.GraduateCert;
import com.example.demo.Model.Product;
import com.example.demo.Repository.GraduateCertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class GraduateCertService {
    @Autowired
    private GraduateCertRepository graduateCertRepository;

//    public List<Map<String, Object>> graduateCertFindById(Integer id) {
//        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//        for (GraduateCert graduateCert : graduateCertRepository.findById(id)) {
//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("ID", product.getId());
//            item.put("NAME", product.getName());
//            item.put("PRICE", product.getPrice());
//            item.put("QUANTITY", product.getQuantity());
//            item.put("CATEGORY_NAME", product.getCategoryName());
//            result.add(item);
//        }
//        return result;
//    }
}
