package com.lukeware.usecases.banckaccount;

import java.time.LocalDate;

/**
 * @author diegomorais
 */
public final record BankAccountResponse(String identifierCode,
                                        boolean active,
                                        boolean externalMovement,
                                        String type,
                                        LocalDate openDate,
                                        LocalDate lastMoveDate) {
}
