package com.br.ha.ms.service.base.controller;

import com.br.ha.ms.service.base.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDto> health() {
    var response = new UserDto();

    response.setMessage("The server is ok");

    return ResponseEntity.ok(response);
  }
}
