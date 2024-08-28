package com.codesoft_sis.service;

import java.util.List;

import com.codesoft_sis.entity.Marks;

public interface MarksService {
	public boolean saveMarks(Marks marks);

	public List<Marks> listOfMarks(int studentId);

}
