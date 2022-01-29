package com.lukeware.usecases.accountholder;

public final record AccountHolderDsResponse(String identifierCode,
                                            boolean owner,
                                            int sequence) {
}
