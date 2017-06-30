package com.endurance.products.teamtraining.crudapi.crud;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by anusha on 30/6/17.
 */
public interface EmployeeRepository extends CrudRepository<Employee, String>{
    Employee findByName(String name);
    ArrayList<Employee> findAll();
}
