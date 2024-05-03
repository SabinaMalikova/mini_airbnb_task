package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {  //1
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return "successfully saved";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            House house = entityManager.find(House.class, houseId);
            if (!checkHouseRent(entityManager, houseId, checkIn, checkOut)) {
                return "reserved";
            }
            RentInfo rentInfo = new RentInfo();
            rentInfo.setCustomer(customer);
            rentInfo.setHouse(house);
            rentInfo.setAgency(agency);
            rentInfo.setCheckIn(checkIn);
            rentInfo.setCheckOut(checkOut);

            house.setRentInfo(rentInfo);
            agency.getRentInfos().add(rentInfo);
            entityManager.persist(customer);
            entityManager.persist(rentInfo);
            entityManager.getTransaction().commit();
            return "successfully saved";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String assignRentInfoToCustomer(RentInfo rentInfo, Long customerId, Long houseId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();


            entityManager.getTransaction().commit();
            return "successfully assigned";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, customerId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setGender(newCustomer.getGender());
            customer.setNationality(newCustomer.getNationality());
            customer.setFamilyStatus(newCustomer.getFamilyStatus());
            entityManager.getTransaction().commit();
            return "successfully updated";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteCustomer(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            List<RentInfo> rentInfos = customer.getRentInfo();
            if (rentInfos.isEmpty()) {
                for (RentInfo rentInfo : rentInfos){
                    if (rentInfo.getCheckOut().isAfter(LocalDate.now())){
                        return "customer has active rentInfo ";
                    }
                    House house = rentInfo.getHouse();
                    house.setRentInfo(null);
                    Owner owner = rentInfo.getOwner();
                    owner.getRentInfos().remove(rentInfo);
                    Agency agency = rentInfo.getAgency();
                    agency.getRentInfos().remove(rentInfo);
                    entityManager.remove(rentInfo);
                }
                entityManager.remove(customer);
                entityManager.getTransaction().commit();
                return "successfully deleted";
            }
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
        return "";
    }

    private boolean checkHouseRent(EntityManager entityManager, Long houseId, LocalDate checkIn, LocalDate checkOut) {
        Long count = entityManager.createQuery("select count(distinct  r.house.id) from RentInfo r where r.house.id = :houseId " +
                        "and (:checkIn between r.checkIn and r.checkIn or :checkOut  between  r.checkOut and r.checkOut)", Long.class)
                .setParameter("houseId", houseId)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut).getSingleResult();
        return count == 0;
    }
}
