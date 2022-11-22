package edu.kpi.service;

import jakarta.ejb.Local;

@Local
public interface ExecutionServiceLocal<T> {

    String execute(final T parameter);
}
