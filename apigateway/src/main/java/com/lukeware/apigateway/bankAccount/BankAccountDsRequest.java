package com.lukeware.apigateway.bankAccount;

import java.time.LocalDate;

/**
 * @author Diego Silva Morais
 */
public final record BankAccountDsRequest(String identifierCode,
                                         boolean active,
                                         boolean externalMovement,
                                         String type,
                                         LocalDate openDate,
                                         LocalDate lastMoveDate) {
}
