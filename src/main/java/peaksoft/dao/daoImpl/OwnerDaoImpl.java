package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.OwnerDao;
import peaksoft.entity.Owner;

import java.util.List;
import java.util.Optional;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public String saveOwner(Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
        return "successfully saved";
    }

    @Override
    public String saveOwnerWithHouse(Owner owner) {
        return null;
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
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
    public String deleteOwnerWithHouseAndRentInfo(Long ownerId) {
        return null;
    }

    @Override
    public Optional<Owner> getOwnerByAgencyId(Long agencyId) {
        return Optional.empty();
    }

    @Override
    public List<Owner> getAllOwners() {
        return null;
    }

    @Override
    public String updateOwner(Long ownerId) {
        return null;
    }

    @Override
    public String deleteOwner(Long ownerId) {
        return null;
    }
}
