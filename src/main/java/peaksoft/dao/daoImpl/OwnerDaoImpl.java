package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.OwnerDao;
import peaksoft.entity.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerDaoImpl implements OwnerDao {    //1
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public String saveOwner(Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            Period period = Period.between(owner.getDateOfBirth(), LocalDate.now());
            entityManager.getTransaction().begin();
            if (period.getYears() < 0) {
                return "age can no be negative";
            }
            else if(period.getYears() > 18) {
                entityManager.persist(owner);
                entityManager.getTransaction().commit();
                return "successfully saved";
            }
            else {
               return "fail, age < 18";
            }
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {
        if (owner == null || house == null) {
            return "Owner or house cannot be null";
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            owner.addHouses(house);
            house.setOwner(owner);

            Period period = Period.between(owner.getDateOfBirth(), LocalDate.now());;
            if (period.getYears() < 0) {
                return "Age cannot be negative";
            } else if (period.getYears() >= 18) {
                entityManager.persist(owner);
                entityManager.persist(house);
                entityManager.getTransaction().commit();
                return "Successfully saved";
            } else {
                return "Failed to save: age < 18";
            }
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }


    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class,ownerId);
            Agency agency = entityManager.find(Agency.class, agencyId);
            owner.getAgencies().add(agency);
            agency.getOwners().add(owner);
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
    public String deleteOwner(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class,ownerId);
            List<RentInfo> rentInfos = owner.getRentInfos();
            if (rentInfos.isEmpty()){
                for (RentInfo rentInfo : rentInfos){
                    if (rentInfo.getCheckOut().isAfter(LocalDate.now())){
                        return "owner has active rent info";
                    }
                    Agency agency = rentInfo.getAgency();
                    agency.getRentInfos().remove(rentInfo);
                    Customer customer = rentInfo.getCustomer();
                    customer.getRentInfo().remove(rentInfo);
                    entityManager.remove(rentInfo);
                }
            }
            List<Agency> agencies = owner.getAgencies();
            for (Agency agency : agencies){
                agency.getOwners().remove(owner);
            }
            entityManager.remove(owner);
            entityManager.getTransaction().commit();
            return "Successfully deleted";
        }catch (Exception e){
            return e.getMessage();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Owner> getOwnerByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        try{
            entityManager.getTransaction().begin();
            owner = entityManager.createQuery("select o from Owner o inner join Agency ag where ag.id = :agencyId",Owner.class)
                            .setParameter("agencyId",agencyId).getSingleResult();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }

        return Optional.ofNullable(owner);
    }

    @Override
    public List<Owner> getAllOwners() {
        List<Owner>owners = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            owners = entityManager.createQuery("select o from Owner o",Owner.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return owners;
    }

    @Override
    public String updateOwner(Long ownerId, Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Owner o set o.firstName = :firstName, o.lastName = :lastName, o.email = :email, o.dateOfBirth = :dateOfBirth, o.gender = :gender where o.id = :ownerId")
                            .setParameter("firstName",newOwner.getFirstName())
                            .setParameter("lastName",newOwner.getLastName())
                            .setParameter("email",newOwner.getEmail())
                            .setParameter("dateOfBirth",newOwner.getDateOfBirth())
                            .setParameter("gender",newOwner.getGender())
                            .setParameter("ownerId",ownerId).executeUpdate();
            entityManager.getTransaction().commit();
            return "successfully updated";
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }
}
