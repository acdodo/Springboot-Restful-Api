package com.demo.app.servlet;

import com.demo.app.common.ParameterCache;
import com.demo.app.exception.LazyAppException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * 
* @ClassName: SystemResourceServlet 
* @Description: webapp初始化时把一些配置信息加载至内存（配置文件classpath:system.properties） 
* @author May 
* @date 2015年8月4日 上午10:11:57
 */

@Component
@Order(value = 1)
public class SystemResourceServlet implements CommandLineRunner {

	
	private static final long serialVersionUID = -4388477871879866619L;
	/**
	 * log4j 记录器
	 */
	//private static final Logger log = Logger.getLogger(SystemResourceServlet.class);

	@Override
	public void run(String... args) throws Exception {
		this.init();
	}

	public void init() {
		/*ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Resource res = context.getResource("classpath:system.properties");*/
		Resource res = new ClassPathResource("system.properties");
		EncodedResource rsSys = new EncodedResource(res,"UTF-8");
		Properties propSys = new Properties();
		try {
			propSys.load(rsSys.getReader());
		} catch (IOException e) {
			//log.error("读取文件system.properties出错！");
			throw new LazyAppException("运行时错误！", e);
		}
		ParameterCache.setSystemProperties(propSys);


		/*Map<String, String> propertiesMap = new HashMap<>();
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("classpath:system.properties");

			propertiesMap = new HashMap<String, String>();
			for (Object key : properties.keySet()) {
				String keyStr = key.toString();
				try {
					// PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
					propertiesMap.put(keyStr, new String(properties.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (java.lang.Exception e) {
					e.printStackTrace();
				}
			}

		}catch (Exception e){

		}*/



	}

}