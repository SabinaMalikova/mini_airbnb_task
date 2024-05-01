package peaksoft.dao;

import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;

import java.util.List;
import java.util.Optional;

public interface CustomerDao{
                                           //CRUD

    //save customer
    String saveCustomer(Customer customer);


    //save customer with rent_info
    String saveCustomerWithRentInfo(Customer customer);


    //assign rent_info to customer with customer id, house id, agency id и check in check out
    String assignRentInfoToCustomer(Long customerId, Long houseId, Long agencyId, RentInfo rentInfo);


    //get all customers
    List<Customer> getAllCustomers();


    //get customer by id
    Optional<Customer> getCustomerById(Long customerId);


    //update customer
    String updateCustomer(Long customerId);


    //delete customer with rent_info if rent_info is null, or if rent_info is true and rent_info < date_now.
    String deleteCustomerWithRentInfo(Long customerId);

}
