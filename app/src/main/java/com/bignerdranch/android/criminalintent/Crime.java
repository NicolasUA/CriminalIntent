package com.bignerdranch.android.criminalintent;

import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTiltle;

    public Crime() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTiltle() {
        return mTiltle;
    }

    public void setTiltle(String tiltle) {
        mTiltle = tiltle;
    }
}
