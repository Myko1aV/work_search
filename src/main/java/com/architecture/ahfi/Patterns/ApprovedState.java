package com.architecture.ahfi.Patterns;

import com.architecture.ahfi.entities.Vacancy;

public class ApprovedState extends State {
    public ApprovedState(Vacancy temp) {
        super(temp);
    }

    @Override
    public void setState() {
        vacancy.setStatus(true);
    }
}
