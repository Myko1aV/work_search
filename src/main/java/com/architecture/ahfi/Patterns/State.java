package com.architecture.ahfi.Patterns;

import com.architecture.ahfi.entities.Vacancy;

public abstract class State {
   public Vacancy vacancy;

    public State(Vacancy temp) {
        this.vacancy = temp;
    }

    public abstract void setState();
}

