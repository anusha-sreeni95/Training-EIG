package com.endurance.products.teamtraining.crudapi.auth;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Employee, String> {

    public Employee findByName(String name);

}
