package peaksoft.dao.daoImpl;

import peaksoft.dao.RentInfoDao;
import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDao {
    @Override
    public List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2) {
        return null;
    }
}
