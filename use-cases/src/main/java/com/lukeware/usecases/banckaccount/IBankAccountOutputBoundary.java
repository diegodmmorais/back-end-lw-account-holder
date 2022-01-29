package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.IOutputBoundary;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

/**
 * @author diegomorais
 */
public interface IBankAccountOutputBoundary extends IOutputBoundary<IBankAccountMapper, BankAccountDsResponse> {
}
