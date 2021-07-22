package view;

import model.Symptom;
import storage.api.ISymptomRepository;
import view.api.ISymptomService;

public class SymptomService implements ISymptomService {
    private final ISymptomRepository repository;

    public SymptomService(ISymptomRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String symptomName, String symptomData) {
        Symptom symptom = new Symptom(symptomName, symptomData);
        repository.save(symptom);
    }
}
