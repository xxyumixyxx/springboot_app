package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Infomation;

// @GetMapping 記載してるなら @Requestmappingは不要 
@Controller
public class DemoController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	// ↓thymleafのレンダリング練習
	// ブラウザから"/greeting"をgetしにいったら、greetingメソッドが呼ばれるというマッピング
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "/greetingだよ") String name, Model model) {
		// nameパラメータに引数nameの値がmodelオブジェクトにバインドされる(viewテンプレートからアクセスできるようになる)
		model.addAttribute("name", name);
		return "greeting";
		// このgreetingメソッドのは、HTMLのサーバー側のレンダリングを実装している
		// Thymeleafはgreetimg.htmlテンプレートを解析し、 th:text　で記述されてる値が ${name} となっているところにレンダリングする
	}

	// ＊↓mysqlのitemテーブルにあるレコードを全件表示する練習＊
	// @Autowiredは自動でnewしてくれるアノテーション
	@Autowired
	//InfomationServiceに記述された処理を呼び出すためにnewする
	InfomationService infomationService;
	
	// ブラウザから "/display_all"をgetしにいったら、getAllRecordメソッドが呼ばれるというマッピング
	@GetMapping("/display_all")
	public String getAllRecord(Model model) {
		// infomation型(データベースから取得したデータを格納するクラスの型(Entityクラス))に
		//InfomationService(処理メソッドの呼び出し元（Service）)のインスタンスメソッドsearchAllを呼び出す
		List <Infomation> dbInfomation = infomationService.searchAll();
		
		// 結果を dbInfomation に格納する #model.addAllAttributes("thymleafのキーとなるカラム名", 取得したデータを格納したい変数名)
		model.addAttribute("dbInfomation", dbInfomation);
		// 出力したいhtmlテンプレート名
		return "/all_list";
	}
}
