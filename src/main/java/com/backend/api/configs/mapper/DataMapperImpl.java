package com.backend.api.configs.mapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class DataMapperImpl implements DataMapper {
  private final ModelMapper modelMapper;

  @Override
  public <E, F> F mapTo(
      E input,
      Class<F> outputType
  ) {
    return modelMapper.map(input, outputType);
  }

  @Override
  public <E, F> List<F> mapAllTo(
      Iterable<? extends E> input,
      Class<F> outputType
  ) {
    var inputStream = StreamSupport.stream(input.spliterator(), false);

    return inputStream
        .map(element -> mapTo(element, outputType))
        .collect(Collectors.toList());
  }

}
