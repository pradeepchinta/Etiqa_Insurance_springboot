package my.com.etiqa.maybank.customer;

import my.com.etoqa.maybank.pojo.PageableResponse;
import my.com.etoqa.maybank.pojo.CustomerDto;

public interface CustomerService {
	
	public PageableResponse getAllCustomer(Integer page, Integer per_page);
	
	public PageableResponse deleteById(Long id);
	
	public PageableResponse addCustomer(CustomerDto custDto);
	
	public PageableResponse updateCustomer(CustomerDto custDto);	
	
}