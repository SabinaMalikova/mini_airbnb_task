package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.AddressDao;
import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public Optional<Address> getAddressById(Long addressId) {
        Address address = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(address);
    }

    @Override
    public Map<Address, Agency> getAllAddressWithAgency() {
        Map<Address, Agency> getAll = new HashMap<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Address> addresses = entityManager.createQuery("select s from Address s inner join Agency a on s.id = a.id", Address.class)
                    .getResultList();
            for (Address address : addresses) {
                getAll.put(address, address.getAgency());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return getAll;
    }

    @Override
    public int getCountAgenciesByCity(String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int count = 0;
        try {
            entityManager.getTransaction().begin();
            count = entityManager.createQuery("select count(ag.id) from Agency ag inner join Address ad on ag.id = ad.id where ad.city = :city", Long.class)
                    .setParameter("city", city).getSingleResult().intValue();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return count;
    }

    @Override
    public Map<String, List<Agency>> getAllRegionWithAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, List<Agency>> allRegionWithAgency = new HashMap<>();
        try {
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("select ag from Agency ag ", Agency.class).getResultList();
            List<Address> addresses = entityManager.createQuery("select ad from Agency ag inner join Address ad on ag.id = ag.id", Address.class)
                    .getResultList();
            for (Address address : addresses) {
                allRegionWithAgency.put(address.getRegion(), agencies);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return allRegionWithAgency;
    }

    @Override
    public String updateAddress(Long oldAddressId, Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("update Address a set a.city = :city,  a.region = :region, a.street = :street where a.id = :oldAddressId")
                    .setParameter("city", newAddress.getCity())
                    .setParameter("region", newAddress.getRegion())
                    .setParameter("street", newAddress.getStreet())
                    .setParameter("oldAddressId", oldAddressId).executeUpdate();
            entityManager.getTransaction().commit();
            return "successfully updated";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }
}
