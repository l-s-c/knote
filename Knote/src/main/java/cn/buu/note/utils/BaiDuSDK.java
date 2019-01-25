package cn.buu.note.utils;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;

public class BaiDuSDK {
    //设置APPID/AK/SK
    private static final String APP_ID = "14782439";
    private static final String API_KEY = "x3jXn22QzXrTxih1smMigI5P";
    private static final String SECRET_KEY = "bCtAzQw2edeqnt32PwMPrdqHh7K5pgY5";
    /**
     * 识别通用文字
     * @param byt
     * @return
     * @throws Exception
     */
    public static JSONObject getOcrResult(byte[] byt) throws Exception {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        
        // 参数为二进制数组
        JSONObject res = null;
        try {
        	res = client.basicGeneral(byt, new HashMap<String, String>());
        }catch(Exception e) {
        	e.printStackTrace();
        	throw new CustomException(ErrorEnum.ANALYZE_ERROR);
        }
   	    System.out.println(res.toString(2));
   	    return res;
    }
}

