package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "department_data", schema = "polyclinic")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department")
    private Integer department;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_data")
    private String departmentData;

    @OneToMany(mappedBy="departments")
    private Set<User> users;

    public Department() {}

    public Department(String departmentName, String departmentData) {
        this.departmentName = departmentName;
        this.departmentData = departmentData;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentData() {
        return departmentData;
    }

    public void setDepartmentData(String departmentData) {
        this.departmentData = departmentData;
    }
}
