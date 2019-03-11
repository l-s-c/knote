package cn.buu.note;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buu.note.dao.RedisOperator;
import cn.buu.note.utils.push.AppPush;
import cn.buu.note.utils.push.PushtoSingle;


@SpringBootApplication(scanBasePackages= {"cn.buu.note"})
//@RestController				/**返回类型支持Json   @Controller+@ResponseBody*/
@Controller
@MapperScan("cn.buu.note.dao")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class App{
	@Resource
	private PushtoSingle ps;
	@Resource
	private AppPush ap;
	@Resource
	private RedisOperator ro;
	
	@RequestMapping("/t")
	@ResponseBody
	public List t(HttpSession session) throws Exception {
		ro.set("s", "s");
		System.out.println("sesssionIDtest:"+session.getId());
		return new ArrayList();
	}
	
	@RequestMapping("/")
	@ResponseBody
	public String index(HttpSession session) throws Exception {
		System.out.println("sesssionID:"+session.getId());
		
		
		
		
try {
//	ps.push("eb69e766e5e982e8f49e518e77bd2e28");
//	ap.push("zgc", "chou", "http://www.baidu.com");
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
