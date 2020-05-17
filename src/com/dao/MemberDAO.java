package com.dao;
import java.util.*;

import com.bean.Member;

public interface MemberDAO {
   Member findById(int id);
   void update(Member member);
   List<Member> selectAll(HashMap map);
   void add(Member member);
   void updateYue(Member member);
   
   
}
