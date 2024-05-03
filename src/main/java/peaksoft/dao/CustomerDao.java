package peaksoft.dao;

import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerDao{
                                        //CRUD

    //save customer
    String saveCustomer(Customer customer);


    //save customer with rent_info
    String saveCustomerWithRentInfo(Customer customer , Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);


    //assign rent_info to customer with customer id, house id, agency id и check in check out
    String assignRentInfoToCustomer(RentInfo rentInfo, Long customerId, Long houseId, Long agencyId);


    //get all customers
    List<Customer> getAllCustomers();


    //get customer by id
    Optional<Customer> getCustomerById(Long customerId);


    //update customer
    String updateCustomer(Long customerId, Customer newCustomer);


    //delete customer with rent_info if rent_info is null, or if rent_info is true and rent_info < date_now.
    String deleteCustomer(Long customerId);

}
