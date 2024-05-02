package peaksoft.service.serviceImpl;

import peaksoft.dao.AgencyDao;
import peaksoft.dao.daoImpl.AgencyDaoImpl;
import peaksoft.entity.Address;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();

    @Override
    public String saveAgency(Agency agency, Address address) {
        return agencyDao.saveAgency(agency,address);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyDao.getAllAgency();
    }

    @Override
    public Optional<Agency> getAgencyById(Long agencyId) {
        return agencyDao.getAgencyById(agencyId);
    }

    @Override
    public String updateAgency(Long oldAgencyId, Agency newAgency) {
        return agencyDao.updateAgency(oldAgencyId,newAgency);
    }

    @Override
    public String deleteAgency(Long agencyId) {
        return agencyDao.deleteAgency(agencyId);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface PhoneNumberConstraint {
        String message() default "Invalid phone number";

        String regex() default "^\\+996\\d{13}$";
    }
}
