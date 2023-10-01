package com.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SecuredUser {
  String name;
  String password;
}
