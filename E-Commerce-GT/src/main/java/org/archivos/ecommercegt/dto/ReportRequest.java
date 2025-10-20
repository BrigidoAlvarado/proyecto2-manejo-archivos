package org.archivos.ecommercegt.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class ReportRequest {

    private final Timestamp startDate;
    private final Timestamp endDate;

    public ReportRequest(Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
