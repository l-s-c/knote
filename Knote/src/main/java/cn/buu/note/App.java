package cn.buu.note;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.buu.note.utils.push.AppPush;
import cn.buu.note.utils.push.PushtoSingle;


@SpringBootApplication(scanBasePackages= {"cn.buu.note"})
//@RestController				/**返回类型支持Json   @Controller+@ResponseBody*/
@Controller
@MapperScan("cn.buu.note.dao")
public class App{
	@Resource
	private PushtoSingle ps;
	@Resource
	private AppPush ap;
	@RequestMapping("/")
	public String index() throws Exception {
try {
	ps.push("5dd87d313e37716caf840f00c2ec7688");
	//ap.push("zgc", "chou", "http://www.baidu.com");
} catch (Exception e) {
	e.printStackTrace();
}	
		return "redirect:http://127.0.0.1:8020/Knote2.0/html/index.html";
		//return "redirect:index.html";
	}
	public static void main(String[] args) {
		System.out.println("start");
		SpringApplication.run(App.class);
	}
	
	
}
