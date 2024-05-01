package peaksoft.dao.daoImpl;

import peaksoft.dao.AddressDao;
import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {
    @Override
    public Optional<Address> getAddressById(Long addressId) {
        return Optional.empty();
    }

    @Override
    public List<Address> getAllAddressWithAgency() {
        return null;
    }

    @Override
    public int getCountAgenciesByCity(String city) {
        return 0;
    }

    @Override
    public Map<String, List<Agency>> getAllRegionWithAgency() {
        return null;
    }

    @Override
    public String updateAddress(Long addressId) {
        return null;
    }
}
