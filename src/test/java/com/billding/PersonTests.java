package com.billding;

import com.billding.Person;
import com.billding.impl.PersonImpl;
import org.junit.Assert;
import org.junit.Test;

public class PersonTests {
    @Test
    public void construct() {
        Person person = new PersonImpl(18, Country.USA, true);
        Assert.assertEquals(person.age(), 18);

    }
}
