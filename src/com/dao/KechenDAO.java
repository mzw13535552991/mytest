package com.dao;
import java.util.*;

import com.bean.Activity;
import com.bean.Kechen;


public interface KechenDAO {
	List<Kechen> selectAll(HashMap map);
	void add(Kechen kechen);
	void update(Kechen kechen);
	Kechen findById(int id);
}
