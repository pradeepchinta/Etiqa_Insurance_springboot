package my.com.etiqa.maybank.mappers;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.com.etiqa.maybank.common.ResponseConstants;
import my.com.etiqa.maybank.exception.InvalidEntryException;
import my.com.etiqa.maybank.exception.NotNullException;
import my.com.etiqa.maybank.exception.RequiredFieldException;
import my.com.etiqa.maybank.exception.ResultNotFoundException;
import my.com.etiqa.maybank.model.Product;
import my.com.etoqa.maybank.pojo.ProductDto;

/**
 * 
 * @author Pradeep Chinta ProductList Mapper
 *
 */
public class ProductListMapper {

	private static final Logger logger = LoggerFactory.getLogger(ProductListMapper.class);

	/**
	 * 
	 * @param productList
	 * @return list of response dto for product list
	 * @throws ResultNotFoundException mapping fetched entities to response dto
	 * @throws RequiredFieldException
	 * @throws InvalidEntryException
	 */
	public static List<ProductDto> mapAllProduct(List<Product> productList)
			throws ResultNotFoundException, RequiredFieldException, InvalidEntryException {

		logger.info("ProductListMapper - mapAllProduct() called {}");

		List<ProductDto> productResponseList = new LinkedList<ProductDto>();
		for (Product product : productList) {
			ProductDto dto = new ProductDto();
			dto.setProductId(product.getProductId());
			dto.setProductName(product.getProductName());
			dto.setProductPrice(product.getProductPrice());
			dto.setProductQuantity(product.getProductQuantity());
			dto.setProductTitle(product.getProductTitle());
			dto.setStatus(product.getStatus());

			productResponseList.add(dto);
		}

		logger.info("ProductListMapper - mapAllProduct() execution done {}");

		return productResponseList;
	}

	/**
	 * 
	 * @param productDetails
	 * @return Product
	 * @throws NotNullException
	 * @throws InvalidEntryException
	 */
	public static Product convertRequestModelToDbModel(ProductDto productDetails)
			throws RequiredFieldException, NotNullException, InvalidEntryException {

		logger.info("ProductListMapper - convertRequestModelToDbModel() called {}");

		Product product = new Product();
		if (productDetails != null) {
			if (productDetails.getProductName() == null || productDetails.getProductName().equals("")) {
				throw new RequiredFieldException("Product Name" + ResponseConstants.REQUIRED_FIELD);
			} else {
				product.setProductName(productDetails.getProductName());
			}
			if (productDetails.getProductTitle() == null || productDetails.getProductTitle().equals("")) {
				throw new RequiredFieldException("Product Title" + ResponseConstants.REQUIRED_FIELD);
			} else {
				product.setProductTitle(productDetails.getProductTitle());
			}

			if (productDetails.getProductPrice() == null) {
				throw new RequiredFieldException("Product Price" + ResponseConstants.REQUIRED_FIELD);

			} else {
				product.setProductPrice(productDetails.getProductPrice());

			}

			product.setStatus(Boolean.TRUE);
			product.setProductQuantity(productDetails.getProductQuantity());
			product.setCreatedDate(new Timestamp(0));
			product.setModifiedDate(new Timestamp(0));

		} else {
			throw new NotNullException(" Product Details " + ResponseConstants.NULL_EXCEPTION);
		}
		logger.info("ProductListMapper - convertRequestModelToDbModel() execution done {}");

		return product;
	}

}
