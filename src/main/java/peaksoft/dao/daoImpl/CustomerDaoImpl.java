package peaksoft.dao.daoImpl;

import peaksoft.dao.CustomerDao;
import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public String saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer) {
        return null;
    }

    @Override
    public String assignRentInfoToCustomer(Long customerId, Long houseId, Long agencyId, RentInfo rentInfo) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public String updateCustomer(Long customerId) {
        return null;
    }

    @Override
    public String deleteCustomerWithRentInfo(Long customerId) {
        return null;
    }
}
