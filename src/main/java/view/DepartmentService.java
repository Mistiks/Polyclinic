package view;

import model.Department;
import storage.api.IDepartmentRepository;
import view.api.IDepartmentService;

public class DepartmentService implements IDepartmentService {
    private final IDepartmentRepository repository;

    public DepartmentService(IDepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String name, String info) {
        Department department = new Department(name, info);
        repository.save(department);
    }
}
