package peaksoft.service.serviceImpl;

import peaksoft.dao.RentInfoDao;
import peaksoft.dao.daoImpl.RentInfoDaoImpl;
import peaksoft.entity.RentInfo;
import peaksoft.service.Rent_info_Service;

import java.time.LocalDate;
import java.util.List;

public class Rent_info_ServiceImpl implements Rent_info_Service {
    RentInfoDao rentInfoDao = new RentInfoDaoImpl();

    @Override
    public List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2) {
        return rentInfoDao.getAllRentInfoBetweenDates(checkOut1,checkOut2);
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        return rentInfoDao.housesByAgencyIdAndDate(agencyId);
    }
}
