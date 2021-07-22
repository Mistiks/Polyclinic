package view;

import model.Disease;
import storage.api.IDiseaseRepository;
import view.api.IDiseaseService;

public class DiseaseService implements IDiseaseService {
    private final IDiseaseRepository repository;

    public DiseaseService(IDiseaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String diseaseName, String diseaseCode) {
        Disease disease = new Disease(diseaseName, diseaseCode);
        repository.save(disease);
    }
}
