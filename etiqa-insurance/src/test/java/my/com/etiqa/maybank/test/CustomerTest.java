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

import my.com.etiqa.maybank.customer.CustomerController;
import my.com.etiqa.maybank.customer.CustomerRepository;
import my.com.etiqa.maybank.customer.CustomerService;
import my.com.etiqa.maybank.model.Customer;
import my.com.etoqa.maybank.pojo.CustomerDto;
import my.com.etoqa.maybank.pojo.PageableResponse;

@SpringBootTest
public class CustomerTest {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerController customerController;

	@MockBean
	CustomerRepository customerRepository;

	@Test
	public void add() {

		CustomerDto customerDto = new CustomerDto();

		customerDto.setEmail("test@astro.com.my");
		customerDto.setFatherName("ram");
		customerDto.setMotherName("sita");
		customerDto.setFirstName("pradeep");
		customerDto.setLastName("chinta");
		customerDto.setOfficeNo("0367897678");
		customerDto.setPhoneNo("01136020633");

		Optional<Customer> customer = Optional.empty();

		Mockito.when(customerRepository.findByEmail(any())).thenReturn(customer);

		Customer cust = new Customer();
		cust.setUserId(1l);

		Optional<Customer> customerObj = Optional.of(cust);

		Mockito.when(customerRepository.save(any())).thenReturn(cust);

		PageableResponse pagebale = customerController.create(customerDto);

		assertThat(pagebale.resultCode()).isEqualTo(1);

		Mockito.when(customerRepository.findByEmail(any())).thenReturn(customerObj);

		PageableResponse failResponse = customerController.create(customerDto);

		assertThat(failResponse.resultCode()).isEqualTo(0);

	}

	@Test
	public void update() {

		CustomerDto customerDto = new CustomerDto();

		customerDto.setCustId(1l);
		customerDto.setEmail("test@astro.com.my");
		customerDto.setFatherName("ram");
		customerDto.setMotherName("sita");
		customerDto.setFirstName("pradeep");
		customerDto.setLastName("chinta");
		customerDto.setOfficeNo("0367897678");
		customerDto.setPhoneNo("01136020633");

		Customer cust = new Customer();
		cust.setUserId(1l);

		Optional<Customer> customerObj = Optional.of(cust);

		Mockito.when(customerRepository.findById(any())).thenReturn(customerObj);

		Mockito.when(customerRepository.save(any())).thenReturn(cust);

		PageableResponse pagebale = customerController.update(customerDto);

		assertThat(pagebale.resultCode()).isEqualTo(1);
		
		Optional<Customer> customer = Optional.empty();

		Mockito.when(customerRepository.findById(any())).thenReturn(customer);

		PageableResponse failResponse = customerController.update(customerDto);

		assertThat(failResponse.resultCode()).isEqualTo(0);

	}

	@Test
	public void delete() {
		
		Customer cust = new Customer();
		cust.setUserId(1l);

		PageableResponse response = customerController.delete(1l);
		
		assertThat(response.resultCode()).isEqualTo(1);
		
	}

	@Test
	public void get() {

		Pageable pageable = PageRequest.of(0, 5);
		
		List<Customer> list = new ArrayList<>();
		
		Customer customer = new Customer();
		
		customer.setEmail("test@astro.com.my");
		customer.setFatherName("ram");
		customer.setMotherName("sita");
		customer.setFirstName("pradeep");
		customer.setLastName("chinta");
		customer.setOfficeNo("0367897678");
		customer.setPhoneNo("01136020633");
		
		list.add(customer);
		
		Customer customer1 = new Customer();
		
		customer1.setEmail("test1@astro.com.my");
		customer1.setFatherName("ram1");
		customer1.setMotherName("sita1");
		customer1.setFirstName("pradeep1");
		customer1.setLastName("chinta1");
		customer1.setOfficeNo("03678976781");
		customer1.setPhoneNo("011360206331");
		
		list.add(customer1);
		
		 Page<Customer> customersList = new PageImpl<Customer>(list, pageable, 2);
				
		Mockito.when(customerRepository.findAll(pageable)).thenReturn(customersList);
		
		PageableResponse response = customerController.get(1, 5);
		
		assertThat(response.resultCode()).isEqualTo(1);

	}

}
