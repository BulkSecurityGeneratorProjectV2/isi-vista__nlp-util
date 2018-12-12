package edu.isi.nlp.strings.offsets;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import edu.isi.nlp.symbols.Symbol;
import java.util.Map;

public final class AnnotatedOffsetRange<OffsetType extends Offset<OffsetType>> {

  private final OffsetRange<OffsetType> range;
  private final Symbol type;
  private final ImmutableMap<String, String> attributes;

  public OffsetRange<OffsetType> range() {
    return range;
  }

  public Symbol type() {
    return type;
  }

  public Map<String, String> attributes() {
    return attributes;
  }

  public static <OffsetType extends Offset<OffsetType>> AnnotatedOffsetRange<OffsetType> create(
      final Symbol spanType,
      final OffsetRange<OffsetType> range,
      final Map<String, String> otherAttributes) {
    return new AnnotatedOffsetRange<OffsetType>(spanType, range, otherAttributes);
  }

  public static <OffsetType extends Offset<OffsetType>> AnnotatedOffsetRange<OffsetType> create(
      final Symbol spanType, final OffsetRange<OffsetType> range) {
    return create(spanType, range, ImmutableMap.<String, String>of());
  }

  private AnnotatedOffsetRange(
      final Symbol spanType,
      final OffsetRange<OffsetType> range,
      final Map<String, String> otherAttributes) {
    this.range = checkNotNull(range);
    this.type = checkNotNull(spanType);
    this.attributes = ImmutableMap.copyOf(otherAttributes);
  }

  public static <OffsetType extends Offset<OffsetType>>
      Function<AnnotatedOffsetRange<OffsetType>, OffsetRange<OffsetType>> toOffsetRangeFunction() {
    return new Function<AnnotatedOffsetRange<OffsetType>, OffsetRange<OffsetType>>() {
      @Override
      public OffsetRange<OffsetType> apply(AnnotatedOffsetRange<OffsetType> input) {
        return input.range();
      }
    };
  }

  @Override
  public String toString() {
    final String attributesString = attributes().isEmpty() ? "" : (" " + attributes().toString());
    return "[" + type.toString() + ": " + range().toString() + attributesString + "]";
  }
}
