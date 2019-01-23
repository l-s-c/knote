package cn.buu.note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication(scanBasePackages= {"cn.buu.note"})
//@RestController				/**返回类型支持Json   @Controller+@ResponseBody*/
@Controller
@MapperScan("cn.buu.note.dao")
public class App {

	@RequestMapping("/")
	public String index() {
		return "redirect:http://127.0.0.1:8020/Knote2.0/html/index.html";
		//return "redirect:index.html";
	}
	public static void main(String[] args) {
		System.out.println("start");
		SpringApplication.run(App.class);
	}

}
