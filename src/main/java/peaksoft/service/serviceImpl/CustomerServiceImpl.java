package peaksoft.service.serviceImpl;

import peaksoft.dao.CustomerDao;
import peaksoft.dao.daoImpl.CustomerDaoImpl;
import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;
import peaksoft.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public String saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer, RentInfo rentInfo) {
        return customerDao.saveCustomerWithRentInfo(customer,rentInfo);
    }

    @Override
    public String assignRentInfoToCustomer(RentInfo rentInfo, Long customerId, Long houseId, Long agencyId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomer) {
        return customerDao.updateCustomer(customerId,newCustomer);
    }

    @Override
    public String deleteCustomer(Long customerId) {
        return customerDao.deleteCustomer(customerId);
    }
}
