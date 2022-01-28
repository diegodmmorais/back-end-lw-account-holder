package com.lukeware.usecases;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IGateway<S, T> {

  Set<T> findAll(S s);
}
