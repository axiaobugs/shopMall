package com.axiaobug.service.impl;

import com.axiaobug.pojo.pms.PmsSkuStock;
import com.axiaobug.repository.pms.PmsSkuStockRepository;
import com.axiaobug.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockRepository skuStockRepository;

    @Override
    public List<PmsSkuStock> getList(Integer productId, String keyword) {
        return skuStockRepository.findByProductIdAndSpDataContaining(productId,keyword);

    }

    @Override
    public Integer update(Integer pid, List<PmsSkuStock> skuStockList) {
        AtomicInteger atomicInteger = new AtomicInteger();
        List<PmsSkuStock> pmsSkuStocks = skuStockRepository.findByProductId(pid);

        pmsSkuStocks.forEach(stock->{
            skuStockList.forEach(attStock->{
                System.out.println("attStock.getSkuCode()!=null: "+(attStock.getSkuCode()!=null));
                System.out.println( stock.getSkuCode().equals(attStock.getSkuCode()));
                if (attStock.getSkuCode()!=null && stock.getSkuCode().equals(attStock.getSkuCode())){

                    stock.setProductId(attStock.getProductId());
                    stock.setPrice(attStock.getPrice());
                    stock.setStock(attStock.getStock());
                    stock.setLowStock(attStock.getLowStock());
                    stock.setPic(attStock.getPic());
                    stock.setSale(attStock.getSale());
                    stock.setPromotionPrice(attStock.getPromotionPrice());
                    stock.setLowStock(attStock.getLockStock());
                    stock.setSpData(attStock.getSpData());
                }
            });
            try {
                skuStockRepository.save(stock);
                atomicInteger.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return (atomicInteger.get());

    }
}
