package com.dao;
import java.util.*;

import com.bean.Imgadv;

public interface ImgadvDAO {
	List<Imgadv> selectAll();
	void add(Imgadv imgadv);
	void delete(int id);
}
