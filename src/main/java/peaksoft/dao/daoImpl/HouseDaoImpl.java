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
            if (house.getRentInfo()==null || house.getRentInfo().getCheckOut().isBefore(LocalDate.now())){
                entityManager.remove(house);
                entityManager.getTransaction().commit();
                return "successfully deleted";
            } else {
                return "fail! rent-info is active";
            }
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
            houses = entityManager.createQuery("select h from House h " +
                            "inner join Address ad on h.id = ad.house.id " +
                            "inner join Agency ag on ad.id = ag.address.id" +
                            "         where ag.id = :agencyId ",House.class)
                    .setParameter("agencyId",agencyId).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHousesByOwnerId(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House>houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h where h.owner.id = :ownerId",House.class)
                    .setParameter("ownerId",ownerId).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2) {
        List<House>houses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h inner join RentInfo r on h.rentInfo.id = r.id " +
                    "where r.checkIn between :checkIn1 and :checkIn2", House.class)
                    .setParameter("checkIn1",checkIn1)
                    .setParameter("checkIn2",checkIn2)
                            .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public Optional<House> getHouseById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try{
            entityManager.getTransaction().begin();
            house = entityManager.find(House.class,houseId);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return Optional.ofNullable(house);
    }

    @Override
    public String updateHouse(Long houseId,House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.createQuery("update House h set h.houseType = :houseType, h.price = :price, h.rating = :rating, h.description = :description, h.room = :room, h.furniture = :furniture where h.id = :houseId",House.class)
                            .setParameter("houseType",newHouse.getHouseType())
                            .setParameter("price",newHouse.getPrice())
                            .setParameter("rating",newHouse.getRating())
                            .setParameter("description",newHouse.getDescription())
                            .setParameter("room",newHouse.getRoom())
                            .setParameter("furniture",newHouse.isFurniture())
                            .setParameter("houseId",houseId).executeUpdate();
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
