package com.architecture.ahfi.Patterns;

import com.architecture.ahfi.entities.Vacancy;

public class DeclinedState extends State {


    public DeclinedState(Vacancy temp) {
        super(temp);
    }

    @Override
    public void setState() {
        vacancy.setStatus(false);
    }
}
