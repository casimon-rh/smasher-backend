package com.redhat;

import java.util.Arrays;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.dto.Login;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

@Path("/auth")
public class AuthResource {

  @ConfigProperty(name = "default.user")
  String user;
  @ConfigProperty(name = "default.pass")
  String pass;

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  public String login(Login dto) {

    if (dto.user.equals(user) && dto.pass.equals(pass)) {
      return "Bearer " + Jwt.issuer("https://example.com/issuer").upn(dto.user)
          .groups(new HashSet<>(Arrays.asList("User", "Admin"))).claim(Claims.birthdate.name(), "1999-09-09").sign();
    }else {
      throw new UnauthorizedException();
    }

  }
}
