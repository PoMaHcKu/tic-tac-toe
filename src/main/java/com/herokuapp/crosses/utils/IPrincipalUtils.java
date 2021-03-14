package com.herokuapp.crosses.utils;

import com.herokuapp.crosses.model.persist.User;
import org.springframework.security.core.Authentication;

public interface IPrincipalUtils {

    User toUser(Authentication authentication);

    User getAuthUser();
}