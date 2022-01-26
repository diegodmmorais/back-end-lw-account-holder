package com.lukeware.usecases.customer;

/**
 * @author Diego Morais
 */
public final record CustomerResponse(String identifierCode,
                                     String identifierDocument,
                                     TypeCustomer type) {}