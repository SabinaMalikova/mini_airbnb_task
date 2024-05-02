package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.RentInfoDao;
import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RentInfo> rentInfos = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            rentInfos = entityManager.createQuery("select r from RentInfo r where r.checkOut between :checkOut1 and :checkOut2", RentInfo.class)
                    .setParameter("checkOut1", checkOut1)
                    .setParameter("checkOut2", checkOut2).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return rentInfos;
    }

    public Long housesByAgencyIdAndDate(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Long countHouse = 0L;
        try {
            entityManager.getTransaction().begin();
            countHouse = entityManager.createQuery("""
                                             select count(r) from RentInfo r
                                             where r.agency.id =:agencyId and r.checkIn <=:currentDate
                                             and r.checkOut >=:currentDate
                                             """, Long.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return countHouse;
    }
}
