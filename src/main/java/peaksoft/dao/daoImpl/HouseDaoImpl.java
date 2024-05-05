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
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveHouseWithAssignToOwner(House house, Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.createQuery("select o from Owner o where o.id = :ownerId", Owner.class)
                    .setParameter("ownerId", ownerId)
                    .getSingleResult();
            owner.addHouses(house);
            house.setOwner(owner);
            entityManager.merge(house);
            entityManager.getTransaction().commit();
            return "successfully saved";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteHouse(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);
            if (house.getRentInfo() == null || house.getRentInfo().getCheckOut().isBefore(LocalDate.now())) {
                entityManager.remove(house);
                entityManager.getTransaction().commit();
                return "successfully deleted";
            } else {
                return "fail! rent-info is active";
            }
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<House> getAllHousesByRegion(String region) {
        List<House> houses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h  where h.address.region =:region", House.class)
                    .setParameter("region", region)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            " where h.address.agency.id =:agencyId", House.class)
                    .setParameter("agencyId", agencyId).getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHousesByOwnerId(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            " where h.owner.id =:ownerId", House.class)
                    .setParameter("ownerId", ownerId).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2) {
        List<House> houses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h inner join RentInfo r on h.rentInfo.id = r.id " +
                            "where r.checkIn between :checkIn1 and :checkIn2", House.class)
                    .setParameter("checkIn1", checkIn1)
                    .setParameter("checkIn2", checkIn2)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public Optional<House> getHouseById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try {
            entityManager.getTransaction().begin();
            house = entityManager.find(House.class, houseId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(house);
    }

    @Override
    public String updateHouse(Long houseId, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House findHouse = entityManager.find(House.class, houseId);
            findHouse.setHouseType(newHouse.getHouseType());
            findHouse.setPrice(newHouse.getPrice());
            findHouse.setRating(newHouse.getRating());
            findHouse.setDescription(newHouse.getDescription());
            findHouse.setRoom(newHouse.getRoom());
            findHouse.setFurniture(newHouse.isFurniture());
            entityManager.getTransaction().commit();
            return " Successfully updated!!!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }
}
