package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.HouseDao;
import peaksoft.entity.House;
import peaksoft.entity.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HouseDaoImpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    @Override
    public String saveHouseWithAssignToOwner(House house, Long ownerId ) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.createQuery("select o from Owner o where o.id = :ownerId",Owner.class)
                            .setParameter("ownerId",ownerId)
                            .getSingleResult();
            owner.addHouses(house);
            house.setOwner(owner);
            entityManager.merge(house);
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
    public String deleteHouseWithAddressAndRentInfo(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class,houseId);
            entityManager.remove(house);
            entityManager.getTransaction().commit();
            return "successfully deleted";
        }catch (Exception e){
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<House> getAllHousesByRegion(String region) {
        List<House>houses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h inner join Address ad on h.id = ad.house.id where ad.region = :region",House.class)
                    .setParameter("region",region)
                    .getResultList();
            entityManager.getTransaction().commit();
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
//            entityManager.createQuery("select h from House h  ") ///////////////////////////////////
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<House> getAllHousesByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<House> getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2) {
        return null;
    }

    @Override
    public Optional<House> getHouseById(Long houseId) {
        return Optional.empty();
    }

    @Override
    public String updateHouse(Long houseId) {
        return null;
    }

    @Override
    public String deleteHouse(Long houseId) {
        return null;
    }
}
