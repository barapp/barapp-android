package com.develmagic.quellio.service.dto;

import android.support.annotation.NonNull;
import android.text.Html;

/**
 * Created by mejmo on 15.5.2017.
 */

public class MemberDTO {
    private String name;
    private String team;
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName()+"   -   "+team;
    }

}
