package com.billding;

import java.util.Collections;
import java.util.function.Predicate;

public class Classifications {
    private final CriminalHistory criminalHistory;

    public Classifications(CriminalHistory criminalHistory) {
        this.criminalHistory = criminalHistory;
    }


    public Lesson predicateExamples() {
        Predicate<Person> isAnAdult = (person) -> person.age() >= 18;
        Predicate<Person> isAFelon = (person) -> criminalHistory.of(person).stream().anyMatch(crime -> crime.level().equals(Crime.Level.FELONY));

        Predicate < Person > legallyAbleToVote = isAnAdult.and(isAFelon.negate());
        Predicate < Person > legallyAbleToBuyAGun = isAnAdult.and(isAFelon.negate());


        Predicate<Person> isASuperAdult = (person) -> person.age() >= 21;
        Predicate <Person> legallyAbleToBuyBeer = isASuperAdult;

        Predicate<Person> ableToRentACar  = (person) -> person.age() >= 25;

        return new Lesson(Collections.singleton(Topic.Predicate));
    }

}
