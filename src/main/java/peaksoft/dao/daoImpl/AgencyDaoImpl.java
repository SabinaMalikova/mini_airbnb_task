package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.AgencyDao;
import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveAgency(Agency agency, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            agency.setAddress(address);
            address.setAgency(agency);
            entityManager.persist(agency);
            entityManager.persist(address);
            entityManager.getTransaction().commit();
            return "successfully saved";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Agency> getAllAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            agencies = entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return agencies;
    }

    @Override
    public Optional<Agency> getAgencyById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();
            agency = entityManager.find(Agency.class, agencyId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(agency);
    }

    @Override
    public String updateAgency(Long oldAgencyId, Agency newAgency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Agency ag set ag.agency_name = :agency_name, ag.phone_number = :phone_number where ag.id = :oldAgencyId")
                    .setParameter("agency_name", newAgency.getAgency_name())
                    .setParameter("phone_number", newAgency.getPhone_number())
                    .setParameter("oldAgencyId", oldAgencyId).executeUpdate();
            entityManager.getTransaction().commit();
            return "successfully updated";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
            return "successfully deleted";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }
}
