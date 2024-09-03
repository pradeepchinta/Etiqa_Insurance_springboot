package my.com.etiqa.maybank.product;

import my.com.etoqa.maybank.pojo.PageableResponse;
import my.com.etoqa.maybank.pojo.ProductDto;

public interface ProductService {
	
	public PageableResponse getAllProduct(Integer page, Integer per_page);
	
	public PageableResponse deleteById(Long id);
	
	public PageableResponse addProduct(ProductDto roleDetailDto);
	
	public PageableResponse updateProduct(ProductDto roleDetailDto);	
	
}

