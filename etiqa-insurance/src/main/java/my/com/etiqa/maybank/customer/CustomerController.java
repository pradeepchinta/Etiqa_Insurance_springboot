package my.com.etiqa.maybank.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.com.etoqa.maybank.pojo.CustomerDto;
import my.com.etoqa.maybank.pojo.PageableResponse;

@RestController
@RequestMapping("/customer")
@CacheConfig(cacheNames = "customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService productService;

	@PostMapping("/create")
	public PageableResponse create(@RequestBody CustomerDto custDto) {
		
		logger.info("CustomerController create API called {} ", custDto);
		
		return productService.addCustomer(custDto);
	}

	@PutMapping("/update")
	public PageableResponse update(@RequestBody CustomerDto custDto) {
		
		logger.info("CustomerController update API called {} ", custDto);

		return productService.updateCustomer(custDto);
	}

	@DeleteMapping("/delete/{id}")
	public PageableResponse delete(@PathVariable Long id) {
		
		logger.info("CustomerController delete API called {} ", id);

		return productService.deleteById(id);
	}

	@GetMapping("/get")
	public PageableResponse get(@RequestParam(required = false) Integer page, Integer per_page) {

		logger.info("CustomerController get API called {} to fetch customer record, requested pageNumber is:"+ page + "per_page is : "+per_page);

		return productService.getAllCustomer(page, per_page);
	}

}
