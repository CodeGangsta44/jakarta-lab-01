package edu.kpi.service;

import edu.kpi.parameters.RegisterParameter;
import jakarta.ejb.Local;

@Local
public interface UserServiceLocal {

    void registerUser(final RegisterParameter parameter);
}
