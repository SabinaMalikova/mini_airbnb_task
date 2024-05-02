package peaksoft.service.serviceImpl;

import peaksoft.dao.AddressDao;
import peaksoft.dao.daoImpl.AddressDaoImpl;
import peaksoft.entity.Address;
import peaksoft.entity.Agency;
import peaksoft.service.AddressService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();

    @Override
    public Optional<Address> getAddressById(Long addressId) {
        return addressDao.getAddressById(addressId);
    }

    @Override
    public Map<Address, Agency> getAllAddressWithAgency() {
        return addressDao.getAllAddressWithAgency();
    }

    @Override
    public int getCountAgenciesByCity(String city) {
        return addressDao.getCountAgenciesByCity(city);
    }

    @Override
    public Map<String, List<Agency>> getAllRegionWithAgency() {
        return addressDao.getAllRegionWithAgency();
    }

    @Override
    public String updateAddress(Long oldAddressId, Address newAddress) {
        return addressDao.updateAddress(oldAddressId,newAddress);
    }
}
