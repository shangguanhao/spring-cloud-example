package com.shangguan.product.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangguan.exception.ProductException;
import com.shangguan.product.dto.CartDTO;
import com.shangguan.product.entity.ProductInfo;
import com.shangguan.product.enums.ProductStatusEnum;
import com.shangguan.product.enums.ResultEnum;
import com.shangguan.product.repository.ProductInfoRepository;
import com.shangguan.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public List<ProductInfo> findList(List<String> productIdList) {
		return productInfoRepository.findByProductIdIn(productIdList);
	}

	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for(CartDTO cartDTO : cartDTOList) {
			Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
			//判断商品是否存在
			if (!productInfoOptional.isPresent()) {
				throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
			}
			
			ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
		}
		
	}

	
	
}
