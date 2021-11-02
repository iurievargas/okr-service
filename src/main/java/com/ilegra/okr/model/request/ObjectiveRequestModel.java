package com.ilegra.okr.model.request;

import lombok.Data;

@Data
public class ObjectiveRequestModel {

    private String title;
    private String annotations;
    private Integer cycleId;
    private Integer objectiveFatherId;
    private Integer teamId;
}
