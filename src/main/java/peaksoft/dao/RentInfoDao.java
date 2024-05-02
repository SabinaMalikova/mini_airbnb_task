package peaksoft.dao;

import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoDao {
    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);

    public Long housesByAgencyIdAndDate(Long agencyId);

}
