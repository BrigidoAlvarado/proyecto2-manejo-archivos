package org.archivos.ecommercegt.dto;

import lombok.Data;

import java.sql.Timestamp;


/**
 * The type Report request.
 */
@Data
public class ReportRequest {

    private final Timestamp startDate;
    private final Timestamp endDate;

    /**
     * Instantiates a new Report request.
     *
     * @param startDate the start date
     * @param endDate   the end date
     */
    public ReportRequest(Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
