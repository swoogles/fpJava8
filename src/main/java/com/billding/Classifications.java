package com.billding;

import java.util.Collections;
import java.util.EnumSet;
import java.util.function.Predicate;

public class Classifications {
    private final CriminalHistory criminalHistory;
    private final AcademicHistory academicHistory;
    private final Finances finances;

    public Classifications(CriminalHistory criminalHistory, AcademicHistory academicHistory, Finances finances) {
        this.criminalHistory = criminalHistory;
        this.academicHistory = academicHistory;
        this.finances = finances;
    }

    // Compare this against using the predicate
    boolean isAnAdult(Person person) { // The types explain this function behavior, but they indicate a more general pattern
        return person.age() > 18;
    }

    public Lesson predicateExamples() {
        Predicate<Person> isAnAdult = (person) -> person.age() >= 18;
        Predicate<Person> isNotAFelon = (person) -> !criminalHistory.of(person).contains(CrimeType.FELONY);

        Predicate<Person> legallyAbleToVote = isAnAdult.and(isNotAFelon);
        Predicate<Person> legallyAbleToBuyAGun = isAnAdult.and(isNotAFelon);
        Predicate<Person> legallyAbleToBuyBeer = (person) -> person.age() >= 21;

        Predicate<Person> hasAnActiveCreditCard = (person) -> true; // TODO Real implementation
        Predicate<Person> doesNotHaveADui = (person) -> !criminalHistory.of(person).contains(CrimeType.DUI);

        Predicate<Person> clearedABackGroundCheck = (person) -> true; // TODO Real implementation
        Predicate<Person> canPassAPolygraph = (person) -> true; // TODO Real implementation
        Predicate<Person> hasNotUsedIllegalDrugsInTheLastDecade  = (person) -> true; // TODO Real implementation
        Predicate<Person> marriedToThePresidentsDaughter = (person) -> true; // TODO Real implementation

        Predicate<Person> ableToRentACar  =
                hasAnActiveCreditCard
                .and(doesNotHaveADui)
                .and((person) -> person.age() >= 25);

        Predicate<Person> hasABachelorsDegree = (person) -> true;

        Predicate<Person> completedOfficerTrainingSchool = (person) -> true;

        Predicate<Person> completedPilotTraining = (person) -> true;


        Predicate<Person> ableToBeAnAirForcePilot =
            completedPilotTraining
            .and(completedOfficerTrainingSchool)
            .and(hasABachelorsDegree)
            .and(clearedABackGroundCheck)
            .and(minimumAge(18))
            .and(maximumAge(19));

        Predicate<Person> canGetTopSecretSecurityClearance =
            clearedABackGroundCheck
                .and(isNotAFelon)
                .and(hasNotUsedIllegalDrugsInTheLastDecade)
                .and(canPassAPolygraph)
                .or(marriedToThePresidentsDaughter);

        Predicate<Person> hasBeenSteadilyEmployed = (person) -> true;

        Predicate<Person> approvedForAMortgage =
            minimumAge(18)
            .and(person-> finances.creditScoreFor(person) > 500)
            .and(person-> finances.savings(person) > 500)
            .and(hasBeenSteadilyEmployed)
            ;

        return new Lesson(EnumSet.of(Topic.Predicate, Topic.Composition));
    }

    // Use this to show how age restrictions can be DRYed out.
    Predicate<Person> minimumAge(int age) {
        return (person) -> person.age() > age;
    }
    Predicate<Person> maximumAge(int age) {
        return (person) -> person.age() > age;
    }

}
