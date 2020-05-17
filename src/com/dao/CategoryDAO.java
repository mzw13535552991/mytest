package com.dao;
import java.util.*;

import com.bean.Activity;
import com.bean.Category;


public interface CategoryDAO {
	List<Category> selectAll(HashMap map);
	void add(Category category);
	void update(Category category);
	Category findById(int id);
}
