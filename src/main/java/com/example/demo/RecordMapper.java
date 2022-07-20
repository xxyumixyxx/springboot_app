package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Infomation;

// データベースを操作するクラス
@Mapper
public interface RecordMapper {
	
	
	//全件表示
	@Select("SELECT * FROM item")
	List <Infomation> searchAll();
}
