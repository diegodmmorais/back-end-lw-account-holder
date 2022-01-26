package com.lukeware.usecases.accountholder;

public final record AccountHolderResponse(String identifierCode,
                                          boolean owner,
                                          int sequence) {}
