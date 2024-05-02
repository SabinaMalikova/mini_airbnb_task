package peaksoft.service;

import peaksoft.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface Rent_info_Service {
    List<RentInfo> getAllRentInfoBetweenDates(LocalDate checkOut1, LocalDate checkOut2);

    public Long housesByAgencyIdAndDate(Long agencyId);
}
