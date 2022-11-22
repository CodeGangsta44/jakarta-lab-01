package edu.kpi.converter;

public interface Converter<S, T> {

    T convert(final S source);
}
