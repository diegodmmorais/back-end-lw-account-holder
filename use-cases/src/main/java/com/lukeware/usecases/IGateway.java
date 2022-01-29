package com.lukeware.usecases;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IGateway<I, O> {

  Set<O> findAll(I s);
}
