package com.endurance.products.teamtraining.crudapi.auth;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by hemantv on 30/6/17.
 */
public interface AuthRepository extends CrudRepository<Employee, String> {

    public Employee findByName(String name);
}
