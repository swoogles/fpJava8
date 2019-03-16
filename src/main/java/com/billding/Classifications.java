package com.billding;

import java.util.Collections;
import java.util.EnumSet;
import java.util.function.Predicate;

public class Classifications {
    private final CriminalHistory criminalHistory;
    private final AcademicHistory academicHistory;
    private final Finances finances;

    public Classifications(
        CriminalHistory criminalHistory,
        AcademicHistory academicHistory,
        Finances finances
    ) {
        this.criminalHistory = criminalHistory;
        this.academicHistory = academicHistory;
        this.finances = finances;
    }

    // Compare this against using the predicate
    boolean isAnAdult(Person person) { // The types explain this function behavior, but they indicate a more general pattern
        return person.age() > 18;
    }

    public Lesson predicateExamples(
        Predicate<Person> hasAnActiveCreditCard,
        Predicate<Person> livesInTexas,
        Predicate<Person> hasNotUsedIllegalDrugsInTheLastDecade,
        Predicate<Person> clearedABackGroundCheck,
        Predicate<Person> canPassAPolygraph,
        Predicate<Person> completedPilotTraining,
        Predicate<Person> hasABachelorsDegree,
        Predicate<Person> hasDefendedAThesis,
        Predicate<Person> hasAnHonoraryDoctorate,
        Predicate<Person> hasNotableNonAcademicAchievements,
        Predicate<Person> completedOfficerTrainingSchool,
        Predicate<Person> hasBeenSteadilyEmployed,
        Predicate<Person> marriedToThePresidentsDaughter,
        Predicate<Person> publishedInAPeerReviewedJournal
    ) {
        Predicate<Person> isAnAdult = (person) -> person.age() >= 18;
        Predicate<Person> isNotAFelon = (person) -> !criminalHistory.of(person).contains(CrimeType.FELONY);

        Predicate<Person> legallyAbleToVote = isAnAdult.and(isNotAFelon);
        Predicate<Person> legallyAbleToBuyAGun = isAnAdult.and(isNotAFelon).or(livesInTexas);
        Predicate<Person> legallyAbleToBuyBeer = (person) -> person.age() >= 21;


        Predicate<Person> hasAPhd =
            hasABachelorsDegree
            .and(publishedInAPeerReviewedJournal)
            .and(hasDefendedAThesis);



        Predicate<Person> ableToBeAProfessor =
            hasAPhd
                .or(
                    hasAnHonoraryDoctorate
                        .and(hasNotableNonAcademicAchievements));


        Predicate<Person> doesNotHaveADui =
            (person) -> !criminalHistory.of(person).contains(CrimeType.DUI);

        Predicate<Person> ableToRentACar  =
                hasAnActiveCreditCard
                .and(doesNotHaveADui)
                .and((person) -> person.age() >= 25);

        Predicate<Person> ableToBeAnAirForcePilot =
            completedPilotTraining
            .and(completedOfficerTrainingSchool)
            .and(hasABachelorsDegree)
            .and(clearedABackGroundCheck)
            .and(minimumAge(18))
            .and(maximumAge(30));

        Predicate<Person> canGetTopSecretSecurityClearance =
            clearedABackGroundCheck
                .and(isNotAFelon)
                .and(hasNotUsedIllegalDrugsInTheLastDecade)
                .and(canPassAPolygraph)
                .or(marriedToThePresidentsDaughter);



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
        return (person) -> person.age() >= age;
    }
    Predicate<Person> maximumAge(int age) {
        return (person) -> person.age() <= age;
    }

}
