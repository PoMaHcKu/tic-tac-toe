package com.herokuapp.crosses.mapper;

import com.herokuapp.crosses.model.persist.User;
import org.springframework.security.core.Authentication;

public interface IPrincipalMapper {

    User toUser(Authentication authentication);
}