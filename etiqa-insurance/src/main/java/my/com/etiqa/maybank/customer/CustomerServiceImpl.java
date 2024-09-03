package my.com.etiqa.maybank.customer;

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
import my.com.etiqa.maybank.exception.RequiredFieldException;
import my.com.etiqa.maybank.exception.ResultNotFoundException;
import my.com.etiqa.maybank.mappers.CustomerMappers;
import my.com.etiqa.maybank.model.Customer;
import my.com.etoqa.maybank.pojo.CustomerDto;
import my.com.etoqa.maybank.pojo.PageableResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public PageableResponse getAllCustomer(Integer page, Integer per_page) {

		try {

			logger.info("Customer get service  - getAllCustomer() called");

			Pageable pageable = Pageable.unpaged();

			if (page != null && per_page != null)
				pageable = PageRequest.of(page - 1, per_page);

			logger.info("Customer get service  - findAll query and mapper methods  called");

			Page<Customer> customersList = customerRepository.findAll(pageable);

			List<CustomerDto> customerDtos = CustomerMappers.mapAllCustomers(customersList.get().toList());

			logger.info("Customer get service excutiopn done", customerDtos);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, customerDtos,
					ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;

		} catch (Exception ex) {

			logger.error("Customer get service got exception", ex);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					ex.getMessage());
			return pageableResponse;
		}

	}

	@Override
	public PageableResponse deleteById(Long id) {

		logger.info("Customer delete service called {}", id);

		try {
			customerRepository.deleteById(id);

			logger.info("Customer delete service excution done {}", id);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, id, ResponseConstants.SUCCESS_CODE,
					ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;
		} catch (Exception e) {

			logger.error("Customer delete service got exception ", e);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					e.getMessage());
			return pageableResponse;
		}
	}

	@Override
	public PageableResponse addCustomer(CustomerDto custDto) {

		logger.info("Customer create service addCustomer() called {}", custDto);

		try {
			Optional<Customer> customer = customerRepository.findByEmail(custDto.getEmail());
			if (!customer.isEmpty()) {
				throw new DuplicateEntryException(ResponseConstants.DUPLICATE_PRODUCT_KEY);
			}
			Customer customerObj = CustomerMappers.convertRequestModelToDbModel(custDto, new Customer());
			Customer customerPersist = customerRepository.save(customerObj);

			logger.info("Customer create service excution done {}", customerPersist);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, customerPersist,
					ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;

		} catch (Exception e) {

			logger.error("Customer create service got  exception done {}", e);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					e.getMessage());
			return pageableResponse;
		}
	}

	@Override
	public PageableResponse updateCustomer(CustomerDto custDto) {
		// TODO Auto-generated method stub

		logger.info("Customer update service updateCustomer() called {}", custDto);

		try {

			if (custDto.getCustId() == null) {
				throw new RequiredFieldException(" Customer Id " + ResponseConstants.REQUIRED_FIELD);
			}

			Optional<Customer> customer = customerRepository.findById(custDto.getCustId());

			if (customer.isEmpty()) {
				throw new ResultNotFoundException("Customer Id " + ResponseConstants.RESULT_NOT_FOUND);
			}

			Customer customerObj = customer.get();

			customerObj = CustomerMappers.convertRequestModelToDbModel(custDto, customerObj);

			Customer CustomerPersist = customerRepository.save(customerObj);

			logger.info("Customer update service updateCustomer() excution done {}", custDto);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, CustomerPersist,
					ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MESSAGE);
			return pageableResponse;

		} catch (Exception e) {

			logger.error("Customer update service got  exception done {}", e);

			PageableResponse pageableResponse = new PageableResponse(0, 0, 0, 0, null, ResponseConstants.ERROR_CODE,
					e.getMessage());
			return pageableResponse;
		}
	}

}
