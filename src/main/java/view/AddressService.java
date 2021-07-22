package view;

import model.Address;
import storage.api.IAddressRepository;
import view.api.IAddressService;

public class AddressService implements IAddressService {

    private final IAddressRepository repository;

    public AddressService(IAddressRepository repository) {
        this.repository = repository;
    }


    @Override
    public void add(int id, String city, String street, String house, String flat, String country) {
        Address address = new Address(id, city, street, house, flat, country);
        repository.save(address);
    }
}
