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
        Predicate<Person> isNotAFelon = (person) -> !criminalHistory.of(person).contains(CrimeType.FELONY);

        Predicate<Person> legallyAbleToVote = isAnAdult.and(isNotAFelon);
        Predicate<Person> legallyAbleToBuyAGun = isAnAdult.and(isNotAFelon);


        Predicate<Person> isASuperAdult = (person) -> person.age() >= 21;
        Predicate<Person> legallyAbleToBuyBeer = isASuperAdult;

        Predicate<Person> hasAnActiveCreditCard = (person) -> true; // TODO Make an interface that retrieves this info.
        Predicate<Person> doesNotHaveADui = (person) -> !criminalHistory.of(person).contains(CrimeType.DUI);

        Predicate<Person> is25OrOlder  = (person) -> person.age() >= 25;

        Predicate<Person> clearedABackGroundCheck = (person) -> true; // TODO Make an interface that retrieves this info.
        Predicate<Person> canPassAPolygraph = (person) -> true; // TODO Make an interface that retrieves this info.
        Predicate<Person> hasNotUsedIllegalDrugsInTheLastDecade  = (person) -> true; // TODO Make an interface that retrieves this info.
        Predicate<Person> marriedToThePresidentsDaughter = (person) -> true; // TODO Make an interface that retrieves this info.

        Predicate<Person> ableToRentACar  =
            is25OrOlder
                .and(hasAnActiveCreditCard)
                .and(doesNotHaveADui);

        Predicate<Person> canGetTopSecretSecurityClearance =
            (
                clearedABackGroundCheck
                    .and(isNotAFelon)
                    .and(hasNotUsedIllegalDrugsInTheLastDecade)
                    .and(canPassAPolygraph)
            ).or(marriedToThePresidentsDaughter);

        return new Lesson(Collections.singleton(Topic.Predicate));
    }

}
