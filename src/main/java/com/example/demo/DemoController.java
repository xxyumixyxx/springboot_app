package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Infomation;
import com.example.demo.entity.SearchRequestInfomation;

// @GetMapping 記載してるなら @Requestmappingは不要 
@Controller
public class DemoController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	// ＊↓thymleafのレンダリング練習＊
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
		
		// 結果を dbInfomation に格納する #model.addAllAttribute("thymleafのキーとなるカラム名", 取得したデータを格納したい変数名)
		model.addAttribute("dbInfomation", dbInfomation);
		// 出力したいhtmlテンプレート名
		return "/all_list";
	}

	// ＊↓画面から入力された値を取得し、sqlで検索し、該当するレコードを表示する練習(同じhtmlテンプレートに検索画面、結果画面があるバージョン)
	// ●●1●● 検索formの画面表示 ブラウザから　"/index"　をgetしに行ったらdisplaySearchメソッドが呼ばれるというマッピング
	// つまりこれから検索する値を入力するformを表示する
	@GetMapping("/index")
	public String displaySearch() {
		return "index";
	}
	
	// ●●2●● index.htmlの入力ボックスに値を入力し、検索ボタン(submit)を押下したら"/id_search"にpostで値を送信し、idSearchメソッドが呼ばれるというマッピング
	@PostMapping("/id_search")
	// @ModelAttribute がpostで送信された値を searchRequestInfomation に格納
	public String idSearch(@ModelAttribute SearchRequestInfomation searchRequestInfomation, Model model ) {
		// 1,InfomationServiceクラスのidSearchメソッドの引数に searchInfomation(=postされてきた値) を記述
		// 2,Postで送られてきた　searchRequestInfomation　を　InfomationService　のidSearchメソッドの引数にする
		Infomation userSearchInfomation = infomationService.idSearch(searchRequestInfomation);
		// 結果を userSearchInfomation に格納する #model.addAllAttribute("thymleafのキーとなるカラム名", 取得したデータを格納したい変数名)
		model.addAttribute("userSearchInfomation", userSearchInfomation);
		// 出力したいhtmlテンプレート名
		return "index";
	}
	
}
