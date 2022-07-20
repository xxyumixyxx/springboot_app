package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Infomation;
import com.example.demo.entity.SearchRequestInfomation;

@Service
public class InfomationService {
	
	// @Autowiredは自動でnewしてくれるアノテーション
	@Autowired
	// recordMapper(DBの操作を行うクラス)をnew
	private RecordMapper recordMapper;

	public List<Infomation> searchAll() {
		return recordMapper.searchAll();
	}

	// searchRequestInfomation に格納された値(postされた値) を引数に、DBをselectするためのidSearchメソッド
	public Infomation idSearch(SearchRequestInfomation searchRequestInfomation) {
		// Mapperクラスでの idSearch の検索結果をreturnで返す
		return recordMapper.idSearch(searchRequestInfomation);
	}
}