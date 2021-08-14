package com.backend.api.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class AddCommentToBookingsDto {
  String comment;
}
