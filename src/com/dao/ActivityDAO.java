package com.dao;
import java.util.*;

import com.bean.Activity;


public interface ActivityDAO {
	List<Activity> selectAll(HashMap map);
	void add(Activity activity);
	void update(Activity activity);
	Activity findById(int id);
	void delete(int id);
}
