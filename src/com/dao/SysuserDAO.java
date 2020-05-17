package com.dao;
import java.util.*;

import com.bean.Sysuser;

public interface SysuserDAO {
   Sysuser findById(int id);
   void update(Sysuser sysuser);
   void updatepwd(int id,String userpassword);
   List<Sysuser> selectOne(Sysuser sysuser);
   List<Sysuser> checkUsername(String username);
   void add(Sysuser sysuser);
   List<Sysuser> selectAll(HashMap map);
   void delete(int id);
   List<Sysuser> usernamecheck(String username);
   
}
