package com.softrunapp.data.datasource.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<T1, T2> {
    public abstract T2 map(T1 value);

    public abstract T1 reversMap(T2 value);

    public List<T2> map(List<T1> values) {
        List<T2> result = new ArrayList<>(values.size());
        for (T1 value : values) {
            result.add(map(value));
        }
        return result;
    }

    public List<T1> reversMap(List<T2> values) {
        List<T1> result = new ArrayList<>(values.size());
        for (T2 value : values) {
            result.add(reversMap(value));
        }
        return result;
    }
}
