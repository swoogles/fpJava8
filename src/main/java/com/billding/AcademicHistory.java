package com.billding;

public interface AcademicHistory {
    Level highestCompleted(Person person);

    enum Level {
        GED,
        HIGH_SCHOOL,
        UNDERGRADUATE,
        MASTERS,
        PHD
    }
}
