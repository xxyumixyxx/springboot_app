package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Infomation;
import com.example.demo.entity.SearchRequestInfomation;

// データベースを操作するクラス
@Mapper
public interface RecordMapper {
	
	// 全件表示
	@Select("SELECT * FROM item")
	List <Infomation> searchAll();

	// ID検索 #@Selectのselect文の記述方法{postされてくる値の変数名}
	@Select("SELECT * FROM item where id = #{id}")
	Infomation idSearch(SearchRequestInfomation searchRequestInfomation);
}
