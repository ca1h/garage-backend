package com.backend.api.configs.mapper;

import java.util.List;

public interface DataMapper {
  <E, F> F mapTo(
      E input,
      Class<F> outputType
  );

  <E, F> List<F> mapAllTo(
      Iterable<? extends E> input,
      Class<F> outputType
  );
}
