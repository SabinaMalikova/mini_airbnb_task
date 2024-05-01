package peaksoft.dao;

import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoDao {
    //get all rent_infos where checkOut date between 2 dates
    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);


    //
}
