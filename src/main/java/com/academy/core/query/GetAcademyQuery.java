package com.academy.core.query;

import com.academy.core.query.result.GetAcademiesResult;

public class GetAcademyQuery implements Query<GetAcademiesResult> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
