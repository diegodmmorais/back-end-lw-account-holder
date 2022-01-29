package com.lukeware.usecases;

/**
 * @param <I>
 * @param <O>
 * @author diegomorais
 */
public interface IOutputBoundary<I, O> {

  O successView(I i);

  O failView(I i, String error);
}
