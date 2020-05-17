package com.dao;
import java.util.*;

import com.bean.Pinlun;
public interface PinlunDAO {
	void add(Pinlun pinlun);
	List<Pinlun> selectAll(HashMap map);
	void delete(int id);
	Pinlun findById(int id);
	void update(Pinlun pinlun);
}
