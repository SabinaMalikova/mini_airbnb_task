package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entity.Customer;
import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {  //1
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
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
    public String saveCustomerWithRentInfo(Customer customer , RentInfo rentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            customer.getRentInfo().add(rentInfo);
            rentInfo.setCustomer(customer);
            entityManager.persist(customer);
            entityManager.persist(rentInfo);
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
    public String assignRentInfoToCustomer(RentInfo rentInfo, Long customerId, Long houseId, Long agencyId) {
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
            RentInfo rentInfo = entityManager.createQuery("select r from RentInfo r where r.customer.id = :customerId",RentInfo.class)
                    .setParameter("customerId",customerId).getSingleResult();
            if (customer.getRentInfo() == null || rentInfo.getCheckOut().isBefore(LocalDate.now())){
                entityManager.remove(customer);
                entityManager.getTransaction().commit();
                return "successfully deleted";
            } else {
                return "fail! rent-info is active";
            }
        }catch (Exception e){
            return e.getMessage();
        }finally {
            entityManager.close();
        }
    }
}
