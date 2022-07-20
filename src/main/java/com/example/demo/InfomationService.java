package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Infomation;

@Service
public class InfomationService {
	
	// @Autowiredは自動でnewしてくれるアノテーション
	@Autowired
	// recordMapper(DBの操作を行うクラス)をnew
	private RecordMapper recordMapper;

	public List<Infomation> searchAll() {
		return recordMapper.searchAll();
	}
}