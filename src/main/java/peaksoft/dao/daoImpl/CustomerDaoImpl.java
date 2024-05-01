package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {     //2
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public String saveCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return "successfully saved";
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer) {

        return null;
    }

    @Override
    public String assignRentInfoToCustomer(Long customerId, Long houseId, Long agencyId, RentInfo rentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();


            entityManager.getTransaction().commit();
            return "successfully assigned";
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try{
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class,customerId);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Customer c set c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.dateOfBirth = :dateOfBirth, " +
                    "c.gender = :gender, c.nationality = :nationality, c.familyStatus = :familyStatus where c.id = :customerId")
                            .setParameter("firstName",newCustomer.getFirstName())
                            .setParameter("lastName",newCustomer.getLastName())
                            .setParameter("email",newCustomer.getEmail())
                            .setParameter("dateOfBirth",newCustomer.getDateOfBirth())
                            .setParameter("gender",newCustomer.getGender())
                            .setParameter("nationality",newCustomer.getNationality())
                            .setParameter("familyStatus",newCustomer.getFamilyStatus())
                            .setParameter("customerId",customerId).executeUpdate();
            entityManager.getTransaction().commit();
            return "successfully updated";
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteCustomer(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            return "successfully deleted";
        }catch (Exception e){
            return e.getMessage();
        }finally {
            entityManager.close();
        }
    }
}
