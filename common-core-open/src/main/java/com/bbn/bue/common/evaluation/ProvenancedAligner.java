package com.bbn.bue.common.evaluation;

/**
 * Something which can produce a {@link ProvenancedAlignment}. See that class for more details.
 *
 * Note that unlike a regular {@link Aligner}, the resulting alignment may not be over the same type
 * of objects as the input (which will become the provenances).
 */
public interface ProvenancedAligner<LeftT, LeftProvT, RightT, RightProvT> {

  ProvenancedAlignment<LeftT, LeftProvT, RightT, RightProvT> align(
      Iterable<? extends LeftProvT> leftItems,
      Iterable<? extends RightProvT> rightItems);
}
