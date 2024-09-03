package my.com.etiqa.maybank.product;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import my.com.etiqa.maybank.common.ResponseConstants;
import my.com.etiqa.maybank.exception.DuplicateEntryException;
import my.com.etiqa.maybank.exception.NotNullException;
import my.com.etiqa.maybank.exception.ResultNotFoundException;
import my.com.etiqa.maybank.mappers.ProductListMapper;
import my.com.etiqa.maybank.model.Product;
import my.com.etoqa.maybank.pojo.PageableResponse;
import my.com.etoqa.maybank.pojo.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository productRepository;

	public Page<Product> get(Integer page, Integer per_page) {

		logger.info("Product service  - get() called");
		Pageable pageable = Pageable.unpaged();

		if (page != null && per_page != null)
			pageable = PageRequest.of(page - 1, per_page);

		logger.info("Product service  get() - findAll() method called");

		return productRepository.findAll(pageable);
	}

	@Override
	public PageableResponse getAllProduct(Integer page, Integer per_page) {

		logger.info("Product service  - getAllProduct() called");

		try {

			Page<Product> products = get(page, per_page);

			List<ProductDto> productResponseList = ProductListMapper.mapAllProduct(products.get().toList());

			logger.info("Product service  - mapAllProduct() returnig success as productResponseList",
					productResponseList.size());

			return new PageableResponse(page, per_page, (int) products.getTotalElements(), products.getTotalPages(),
					productResponseList, ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);

		} catch (Exception e) {

			logger.error("Product service  - getAllProduct()  exception occured :", e);

			return new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					ResponseConstants.ERROR_MESSAGE);

		}

	}

	@Override
	public PageableResponse deleteById(Long id) {

		logger.info("Product delete service  - deleteById() called, product id : ", id);

		try {
			productRepository.deleteById(id);

			logger.info("Product delete service  - succesfully delete the product", id);

			PageableResponse response = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.SUCCESS_CODE,
					ResponseConstants.SUCCESS_MESSAGE);
			return response;
		} catch (Exception e) {

			logger.error("Product delete service got exception : ", e);

			return new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					ResponseConstants.ERROR_MESSAGE);

		}
	}

	@Override
	public PageableResponse addProduct(ProductDto productDetailDto) {

		logger.info("Product add service - addProduct method called {} ", productDetailDto);
		try {
			Optional<Product> products = productRepository.findByProductName(productDetailDto.getProductName());

			if (products.isPresent()) {
				throw new DuplicateEntryException(ResponseConstants.DUPLICATE_PRODUCT_KEY);
			}
			logger.info("Product add service - addProduct method called {} ", productDetailDto);

			Product product = ProductListMapper.convertRequestModelToDbModel(productDetailDto);
			Product productObj = productRepository.save(product);

			logger.info("Product add service - addProduct execution done {} ", productObj);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, productObj,
					ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;

		} catch (Exception e) {

			logger.error("Product add service got exception : ", e);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					e.getMessage());
			return pageableResponse;
		}
	}

	@Override
	public PageableResponse updateProduct(ProductDto productDetailDto) {

		logger.info("Product update service - updateProduct method called {} ", productDetailDto);

		try {

			if (productDetailDto.getProductId() == null) {
				throw new NotNullException("Product Id " + ResponseConstants.REQUIRED_FIELD);
			}

			logger.info("Product update service - findById() query calling {} ");

			Optional<Product> productPersist = productRepository.findById(productDetailDto.getProductId());
			if (productPersist.isEmpty()) {
				throw new ResultNotFoundException("Product " + ResponseConstants.RESULT_NOT_FOUND);
			}

			Product product = productPersist.get();

			product.setModifiedDate(new Timestamp(0));
			product.setProductName(productDetailDto.getProductName());
			product.setProductPrice(productDetailDto.getProductPrice());
			product.setProductQuantity(productDetailDto.getProductQuantity());
			product.setProductTitle(productDetailDto.getProductTitle());
			product.setStatus(productDetailDto.getStatus());

			logger.info("Product update service - save() query calling {} ");

			Product productObj = productRepository.save(product);

			logger.info("Product update service excution done :", productObj);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, productObj,
					ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;

		} catch (Exception e) {

			logger.error("Product update service got exception :", e);

			e.printStackTrace();
			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					e.getMessage());
			return pageableResponse;
		}
	}

}
