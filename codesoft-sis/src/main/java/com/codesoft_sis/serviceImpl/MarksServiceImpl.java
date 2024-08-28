package com.codesoft_sis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesoft_sis.entity.Marks;
import com.codesoft_sis.repository.MarksRepository;
import com.codesoft_sis.service.MarksService;

@Service
public class MarksServiceImpl implements MarksService {
	@Autowired
	private MarksRepository markRepo;

	@Override
	public boolean saveMarks(Marks marks) {
		try {
			this.markRepo.save(marks);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Marks> listOfMarks(int studentId) {
		return this.markRepo.findByStudentId(studentId);
	}

}
