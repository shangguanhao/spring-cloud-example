package com.shangguan.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shangguan.product.VO.ProductInfoVO;
import com.shangguan.product.VO.ProductVO;
import com.shangguan.product.VO.ResultVO;
import com.shangguan.product.dto.CartDTO;
import com.shangguan.product.entity.ProductCategory;
import com.shangguan.product.entity.ProductInfo;
import com.shangguan.product.service.CategoryService;
import com.shangguan.product.service.ProductService;
import com.shangguan.product.utils.ResultVOUtil;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 1.查询所有在家的商品
	 * 2.获取类目type列表
	 * 3.查询类目
	 * 4.构造数据
	 */
	@GetMapping("/list")
	public ResultVO<ProductVO> list() {
		
		//1.查询所有在家的商品
		List<ProductInfo> productInfos = productService.findUpAll();
		
		//2.获取类目type列表
		List<Integer> list = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
		//3.查询类目
		List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(list);
		System.out.println("categoryList=="+categoryList);
		
		//4.构造数据
		List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
		
	}
	
	
	/**
	 * 获取商品列表（给订单服务用的）
	 * @param productIdList
	 * @return
	 */
	@RequestMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.findList(productIdList);
		
	}
	
	@RequestMapping("/decreaseStock")
	public void decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
		productService.decreaseStock(cartDTOList);
	}
	
	
	
}
