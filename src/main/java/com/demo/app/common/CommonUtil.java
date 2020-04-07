package com.demo.app.common;

import com.alibaba.fastjson.JSONObject;
import com.demo.app.exception.LazyAppException;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 常用工具类
 *
 */
public class CommonUtil {
	
	/**
	 * 从map中根据key读取value
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getMapValue(Map map,String key){
		return (map.containsKey(key)&&map.get(key)!=null)?map.get(key).toString().trim():"";
	}
	
	/**
	 * 从map中根据key读取value,如果不存在，则返回默认值
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getMapValue(Map map,String key,String defaultValue){
		return (map.containsKey(key)&&map.get(key)!=null)?map.get(key).toString().trim():defaultValue;
	}
	
	/**
	 * 从JSONObject中根据key读取value
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static String getJSONValue(JSONObject jsonObject, String key){
		return (jsonObject.containsKey(key)&&jsonObject.get(key)!=null)?jsonObject.get(key).toString().trim():"";
	}
	
	/**
	 * 从JSONObject中根据key读取value,如果不存在，则返回默认值
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static String getJSONValue(JSONObject jsonObject,String key,String defaultValue){
		return (jsonObject.containsKey(key)&&jsonObject.get(key)!=null)?jsonObject.get(key).toString().trim():defaultValue;
	}
	
	/**
	 * 格式化成flexigrid需要的list
	 * @param list
	 * @param pKey
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static List formatFGList(List list,String pKey,int pageNum,int pageSize){
		try{
			List returnList = new ArrayList();
			if(list==null||list.size()==0){
				return new ArrayList();
			}else{
				Map map = null;
				Map cell = null;
				Map item = null;
				int xh = 1;
				for(int i=0;i<list.size();i++){
					map = new Hashtable();
					cell = new Hashtable();
					item = (Map)list.get(i);
					Iterator paIter = item.keySet().iterator();
					while (paIter.hasNext()) {
						String key = paIter.next().toString();
						String value = getMapValue(item,key);
						if(pKey.equals(key)){
							map.put(pKey, value);
							cell.put("xh", (pageNum - 1) * pageSize + xh);
						}
						cell.put(key, value);
					}
					map.put("cell", cell);
					returnList.add(map);
					xh++;
				}
				return returnList;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new LazyAppException("转换FlexiGrid数据出错！");
		}
	}
	
	/**
	 * 格式化成flexigrid需要的数据
	 * @param list
	 * @param pKey
	 * @param pageNum
	 * @param pageSize
	 * @param total
	 * @return
	 */
	public static Map formatFGMap(List list,String pKey,int pageNum,int pageSize,int total){
		Map result = new HashMap();
		result.put("page", pageNum + "");
		result.put("total", total + "");
		result.put("rows", formatFGList(list,pKey,pageNum,pageSize));
		return result;
	}
	 /**
	 * 获取用户的IP
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return ip String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * kid列表格式转化
	 * @param list<Map>
	 */
	public static String formatKid(List list){
		StringBuffer sb=new StringBuffer("");
		Map map=null;
		for(int i=0;i<list.size();i++){
			map=(Map)list.get(i);
			if(i==list.size()-1){
				sb.append(map.get("sjly")+"="+map.get("kid"));
			}else{
				sb.append(map.get("sjly")+"="+map.get("kid")+",");
			}
			
			
		}
		return sb.toString();
	}
	
	
	/**
	 * 从list中取出map，并取出指定字段的值，封装到新的List中返回
	 * 
	 * @param list
	 * @return
	 */
	public static List mapList2ValueList(List list, String fieldName)
	{
		List retList = new ArrayList();
		for (Iterator i = list.iterator(); i.hasNext();)
		{
			Map map = (Map) i.next();
			retList.add(map.get(fieldName));
		}
		return retList;
	}

	/**
	 * 过滤掉map中的null值,封装到新的map中
	 * 
	 * @version 1.0
	 */
	public static Map<String, Object> filterNullByMap(Map<String, Object> map) throws Exception
	{
		Map<String, Object> resMap = new HashMap<String, Object>();
		for (Iterator<String> i = map.keySet().iterator(); i.hasNext();)
		{
			String key = i.next();
			if (null != key)
			{
				Object value = map.get(key);
				if (null != value)
					resMap.put(key, value);
			}
		}
		return resMap;
	}
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */ 
    @SuppressWarnings({ "rawtypes", "unchecked" }) 
    public static Map convertBean(Object bean) 
            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
        Class type = bean.getClass(); 
        Map returnMap = new HashMap(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 
 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
    } 

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */ 
    @SuppressWarnings("rawtypes") 
    public static Object convertMap(Class type, Map map) 
            throws IntrospectionException, IllegalAccessException, 
            InstantiationException, InvocationTargetException { 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性 
        Object obj = type.newInstance(); // 创建 JavaBean 对象 
 
        // 给 JavaBean 对象的属性赋值 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
 
            if (map.containsKey(propertyName)) { 
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。 
                Object value = map.get(propertyName); 
 
                Object[] args = new Object[1]; 
                args[0] = value; 
 
                descriptor.getWriteMethod().invoke(obj, args); 
            } 
        } 
        return obj; 
    } 
    /**
	 * 取map2中的String数组的第一个值，放置个map1中
	 *
	 * @param map1
	 * @param map2
	 */
	@SuppressWarnings("unchecked")
	public static void map2map(Map map1,Map map2) {
		for(Iterator i=map2.keySet().iterator();i.hasNext();){
			String key = (String)i.next();
			if(null != key){
				String[] value = (String[])map2.get(key);
				if(null != value && value.length > 0){
					map1.put(key, value[0]);
				}
			}
		}
	}
	/**
	 * List格式转换
	 * List<Bean> 转换成List<Map>方便前台页面利用flexigrid展示数据
	 * @param tableSk
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List formatListBeanToListMap(List tableSk) {
		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < tableSk.size(); i++) {
			try {
				Map<String, Object> map =convertBean(tableSk.get(i));
				result.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 将数组转换为字符串
	 * @param arr
	 * @return
	 */
//	public static String arrToStr(String[] arr){
//		String str = "";
//		for(int i=0;i<arr.length;i++){
//			if(i==arr.length-1)
//			    str += ""+arr[i]+"";
//			else
//				 str += ""+arr[i]+""+",";
//		}
//		return str;
//	}
	static String _keyStr = ParameterCache.getSystemProperties("form.encrypt");;// "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	public static String decode(String str){
		String input = EscapeUnescape.unescape(str);
		String output = "";
	    int chr1, chr2, chr3;
	    int enc1, enc2, enc3, enc4;
	    int i = 0;
	    //input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	    while (i < input.length()) {
	        enc1 = _keyStr.indexOf(input.charAt(i++));
	        enc2 = _keyStr.indexOf(input.charAt(i++));
	        enc3 = _keyStr.indexOf(input.charAt(i++));
	        enc4 = _keyStr.indexOf(input.charAt(i++));
	        chr1 = (enc1 << 2) | (enc2 >> 4);
	        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	        chr3 = ((enc3 & 3) << 6) | enc4;
	        output = output + (char)(chr1);
	        if (enc3 != 64) {
	            output = output + (char)(chr2);
	        }
	        if (enc4 != 64) {
	            output = output + (char)(chr3);
	        }
	    }
	    output = _utf8_decode(output);
	    return output;
	}
	
	private static String _utf8_decode(String utftext){
		String string = "";
        int i = 0;
        int c = 0, c1 = 0, c2 = 0, c3 = 0;
        while (i < utftext.length()) {
            c = utftext.charAt(i);
            if (c < 128) {
                string += (char)c;
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charAt(i + 1);
                string += (char)(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charAt(i + 1);
                c3 = utftext.charAt(i + 2);
                string += (char)(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
	}
	
//	public static void main(String []args){
//		String s = new CommonUtil().decode("YUBfMTIzNDU2Nw%3D%3D");
//		System.out.println(s);
//	}
}
