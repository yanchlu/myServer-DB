package com.c72;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class UserJoinsMapper implements RowMapper<UserJoins> {
   public UserJoins mapRow(ResultSet rs, int rowNum) throws SQLException {
      UserJoins userjoins=new UserJoins();
      userjoins.setTid(rs.getInt("tid"));
      userjoins.setUid(rs.getInt("uid"));
      userjoins.setTime(rs.getTimestamp("time"));
      return userjoins;
   }
}
