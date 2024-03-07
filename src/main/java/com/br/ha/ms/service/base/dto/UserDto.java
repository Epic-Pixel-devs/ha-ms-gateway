package com.br.ha.ms.service.base.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

  private Long Id;
  private String name;
  private String message;

}
