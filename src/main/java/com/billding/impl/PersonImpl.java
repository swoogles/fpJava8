package com.billding.impl;

import com.billding.Country;
import com.billding.Person;

public class PersonImpl implements Person {
    private final int _age;
    private final Country _countryOfBirth;
    private final boolean _felon;

    public PersonImpl(int age, Country countryOfBirth, boolean felon) {
        _age = age;
        _countryOfBirth = countryOfBirth;
        _felon = felon;
    }


    @Override
    public int age() {
        return _age;
    }

    @Override
    public Country countryOfBirth() {
        return _countryOfBirth;
    }

    @Override
    public boolean felon() {
        return _felon;
    }
}
