package com.innotec.bats.general;

import java.io.Serializable;

/**
 * Created by phoenix on 7/20/16.
 */
public abstract class Action implements Serializable {
    String sourceId;
    public Action() {
        sourceId=null;
    }
    public Action(String sourceId) {
        this.sourceId=sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
