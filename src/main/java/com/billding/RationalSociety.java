package com.billding;

import java.util.function.Predicate;

class RationalSociety {

    RationalSociety(
        Person person,
        Predicate<Person> legallyAbleToBuyAgun,
        Predicate<Person> legallyAbleToBuyBeer,
        Predicate<Person> ableToJoinTheMilitary
    ) {
        assert legallyAbleToBuyAgun.test(person) == legallyAbleToBuyBeer.test(person);

    }
}
