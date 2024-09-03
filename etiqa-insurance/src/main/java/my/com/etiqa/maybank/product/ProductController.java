package my.com.etiqa.maybank.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.com.etoqa.maybank.pojo.PageableResponse;
import my.com.etoqa.maybank.pojo.ProductDto;

@RestController
@RequestMapping("/product")
@CacheConfig(cacheNames = "product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public PageableResponse create(@RequestBody ProductDto prodDto) {

		logger.info("/product/create api called {} ", prodDto);
		
		return productService.addProduct(prodDto);
	}

	@PutMapping("/update")
	public PageableResponse update(@RequestBody ProductDto prodDto) {

		logger.info("/product/update api called {} ", prodDto);

		return productService.updateProduct(prodDto);
	}

	@DeleteMapping("delete/{id}")
   @CacheEvict(cacheNames = "product", allEntries = true)
	public PageableResponse delete(@PathVariable Long id) {

		logger.info("/product/delete api called {} ", id);

		return productService.deleteById(id);
	}

	@GetMapping("/get")
	public PageableResponse get(@RequestParam(required = false) Integer page, Integer per_page) {

		logger.info("/product/create api called {} ");

		return productService.getAllProduct(page, per_page);
	}

}
