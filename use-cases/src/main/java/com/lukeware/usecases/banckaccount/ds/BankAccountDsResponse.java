package com.lukeware.usecases.banckaccount.ds;

import java.time.LocalDate;

/**
 * data structure
 * @author diegomorais
 */
public final record BankAccountDsResponse(String identifierCode,
                                          String customerId,
                                          boolean active,
                                          boolean externalMovement,
                                          String type,
                                          LocalDate openDate,
                                          LocalDate lastMoveDate) {
}
