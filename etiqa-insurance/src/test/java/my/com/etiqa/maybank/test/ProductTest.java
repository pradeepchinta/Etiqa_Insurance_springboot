package my.com.etiqa.maybank.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import my.com.etiqa.maybank.model.Product;
import my.com.etiqa.maybank.product.ProductController;
import my.com.etiqa.maybank.product.ProductRepository;
import my.com.etiqa.maybank.product.ProductService;
import my.com.etoqa.maybank.pojo.PageableResponse;
import my.com.etoqa.maybank.pojo.ProductDto;

@SpringBootTest
public class ProductTest {

	@Autowired
	ProductController productController;

	@Autowired
	ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@Test
	public void add() {

		ProductDto dto = new ProductDto();

		dto.setProductName("test product");
		dto.setProductPrice(200l);
		dto.setProductQuantity("1200");
		dto.setProductTitle("test title");
		dto.setStatus(Boolean.TRUE);

		Product product = new Product();
		product.setProductId(1l);

		Optional<Product> emptyProduct = Optional.empty();

		Mockito.when(productRepository.findByProductName(any())).thenReturn(emptyProduct);

		Mockito.when(productRepository.save(any())).thenReturn(product);

		PageableResponse pageableResponse = productController.create(dto);

		assertThat(pageableResponse.resultCode()).isEqualTo(1);

		Optional<Product> productObj = Optional.of(product);

		Mockito.when(productRepository.findByProductName(any())).thenReturn(productObj);

		PageableResponse failResponse = productController.create(dto);

		assertThat(failResponse.resultCode()).isEqualTo(0);

	}

	@Test
	public void update() {

		ProductDto dto = new ProductDto();

		dto.setProductId(1l);
		dto.setProductName("test product");
		dto.setProductPrice(200l);
		dto.setProductQuantity("1200");
		dto.setProductTitle("test title");
		dto.setStatus(Boolean.TRUE);

		Product product = new Product();
		product.setProductId(1l);

		Optional<Product> optProduct = Optional.of(product);

		Mockito.when(productRepository.findById(any())).thenReturn(optProduct);

		Mockito.when(productRepository.save(any())).thenReturn(product);

		PageableResponse pageableResponse = productController.update(dto);

		assertThat(pageableResponse.resultCode()).isEqualTo(1);

		Optional<Product> emptyObj = Optional.empty();

		Mockito.when(productRepository.findById(any())).thenReturn(emptyObj);

		PageableResponse failResponse = productController.update(dto);

		assertThat(failResponse.resultCode()).isEqualTo(0);

	}

	@Test
	public void delete() {

		PageableResponse response = productService.deleteById(1l);

		assertThat(response.resultCode()).isEqualTo(1);

	}

	@Test
	public void get() {

		Pageable pageable = PageRequest.of(0, 5);

		List<Product> list = new ArrayList<Product>();

		Product product = new Product();

		product.setProductId(1l);
		product.setProductName("test product");
		product.setProductPrice(200l);
		product.setProductQuantity("1200");
		product.setProductTitle("test title");
		product.setStatus(Boolean.TRUE);

		list.add(product);

		Product product1 = new Product();

		product1.setProductId(2l);
		product1.setProductName("test product1");
		product1.setProductPrice(201l);
		product1.setProductQuantity("1201");
		product1.setProductTitle("test title1");
		product1.setStatus(Boolean.TRUE);

		list.add(product1);

		Page<Product> productsList = new PageImpl<Product>(list, pageable, 2);

		Mockito.when(productRepository.findAll(pageable)).thenReturn(productsList);

		PageableResponse response = productController.get(1, 5);

		assertThat(response.resultCode()).isEqualTo(1);
		
		
		PageableResponse errorResponse = productController.get(2, 5);

		assertThat(errorResponse.resultCode()).isEqualTo(0);
		

	}
}
