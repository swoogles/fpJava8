package com.billding;

import java.util.List;
import java.util.Set;

public interface CriminalHistory {
    Set<CrimeType> of(Person person);
}
