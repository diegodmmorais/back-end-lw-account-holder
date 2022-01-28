package com.lukeware.controllers.bankaccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

/**
 * @author diegomorais
 */
public final record BankAccountResquest(String identifierCode,
                                        boolean active,
                                        boolean externalMovement,
                                        String type,
                                        @JsonDeserialize(using = LocalDateDeserializer.class)
                                        @JsonSerialize(using = LocalDateSerializer.class)
                                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                        LocalDate openDate,
                                        @JsonDeserialize(using = LocalDateDeserializer.class)
                                        @JsonSerialize(using = LocalDateSerializer.class)
                                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                        LocalDate lastMoveDate) {
}
