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
import my.com.etiqa.maybank.model.Customer;
import my.com.etoqa.maybank.pojo.CustomerDto;

/**
 * 
 * @author Pradeep Chinta CustomerMappers
 *
 */
public class CustomerMappers {

	private static final Logger logger = LoggerFactory.getLogger(CustomerMappers.class);

	/**
	 * 
	 * @param customerList
	 * @return list of response dto for customer list
	 * @throws ResultNotFoundException mapping fetched entities to response dto
	 * @throws RequiredFieldException
	 * @throws InvalidEntryException
	 */
	public static List<CustomerDto> mapAllCustomers(List<Customer> customerList)
			throws ResultNotFoundException, RequiredFieldException, InvalidEntryException {

		logger.info("CustomerMappers mapAllCustomers() called to map all customers {}");

		List<CustomerDto> customerResponseList = new LinkedList<CustomerDto>();
		for (Customer customer : customerList) {
			CustomerDto dto = new CustomerDto();

			dto.setCustId(customer.getUserId());
			dto.setFirstName(customer.getFirstName());
			dto.setLastName(customer.getLastName());
			dto.setEmail(customer.getEmail());
			dto.setFatherName(customer.getFatherName());
			dto.setStatus(customer.getStatus());
			dto.setMotherName(customer.getMotherName());
			dto.setPhoneNo(customer.getPhoneNo());
			dto.setOfficeNo(customer.getOfficeNo());

			customerResponseList.add(dto);
		}

		logger.info("CustomerMappers mapAllCustomers() execution done {}");

		return customerResponseList;
	}

	/**
	 * 
	 * @param customerDetails
	 * @return Customer
	 * @throws NotNullException
	 * @throws NotNullException
	 * @throws InvalidEntryException
	 */
	public static Customer convertRequestModelToDbModel(CustomerDto customerDetails, Customer customer)
			throws RequiredFieldException, NotNullException, InvalidEntryException {

		logger.info("CustomerMappers mapAllCustomers() called to map all customers {}");

		if (customerDetails != null) {

			if (customerDetails.getEmail() == null || customerDetails.getEmail().equals("")) {
				throw new RequiredFieldException("Customer Email" + ResponseConstants.REQUIRED_FIELD);
			} else {
				customer.setEmail(customerDetails.getEmail());
			}
			if (customerDetails.getFirstName() == null || customerDetails.getFatherName().equals("")) {
				throw new RequiredFieldException("Customer First Name" + ResponseConstants.REQUIRED_FIELD);
			} else {
				customer.setFirstName(customerDetails.getFirstName());
			}
			if (customerDetails.getLastName() == null || customerDetails.getLastName().equals("")) {
				throw new RequiredFieldException("Customer Last Name" + ResponseConstants.REQUIRED_FIELD);
			} else {
				customer.setLastName(customerDetails.getLastName());
			}

			customer.setFatherName(customerDetails.getFatherName());
			customer.setMotherName(customerDetails.getMotherName());
			customer.setOfficeNo(customerDetails.getOfficeNo());
			customer.setPhoneNo(customerDetails.getPhoneNo());
			customer.setStatus(customerDetails.getStatus());

			if (customerDetails.getCustId() != null) {
				customer.setModifiedDate(new Timestamp(0));
			} else {
				customer.setCreatedDate(new Timestamp(0));
			}

		} else {
			throw new NotNullException(" Customer Details " + ResponseConstants.NULL_EXCEPTION);
		}

		logger.info("CustomerMappers convertRequestModelToDbModel() execution done {}");

		return customer;
	}

}
