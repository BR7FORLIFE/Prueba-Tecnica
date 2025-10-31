package com.archives.backend.security.dtos.response;

import java.sql.Date;
import java.util.Set;

import com.archives.backend.security.enums.RolUser;

public record LoginUserResponseDto(String username, Set<RolUser> rols, Date create_at) {

}
