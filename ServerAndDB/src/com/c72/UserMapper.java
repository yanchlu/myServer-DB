package com.c72;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class UserMapper implements RowMapper<User> {
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user=new User();
      user.setUid(rs.getInt("uid"));
      user.setName(rs.getString("name"));
      user.setPhone(rs.getString("phone"));
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      user.setMoney(rs.getInt("money"));
      user.setCredit(rs.getInt("credit"));
      return user;
   }
}