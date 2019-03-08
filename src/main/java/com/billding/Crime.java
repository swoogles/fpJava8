package com.billding;

public interface Crime {
    Level level();
    enum Level {
        MISDEMEANOR, FELONY
    }
}
