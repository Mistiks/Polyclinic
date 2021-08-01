package view;

import model.Country;
import storage.api.ICountryRepository;
import view.api.ICountryService;

import java.util.List;

public class CountryService implements ICountryService {
    private final ICountryRepository countryRepository;

    public CountryService(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}
