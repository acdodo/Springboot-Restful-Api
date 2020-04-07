package com.demo.app.common;

import java.util.*;
import java.util.Map.Entry;

/**
 * Escape&Unescape
 *
 */
public class EscapeUnescape {
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	//对list数据集的值进行解码
	public static List listUnescape(List listPage) {
		if (listPage.size()>0) {
			List<Map<String, String>> listUnescape=new ArrayList<Map<String, String>>();
			try {
				for (int i = 0; i < listPage.size(); i++) {
					Map<String, String> mapvalue=new HashMap<String, String>();
					Map map=(Map) listPage.get(i);
					// 通过entrySet()取得key值和value值
					Iterator<Entry> itor = map.entrySet().iterator(); 
					while(itor.hasNext()) { 
						Entry<String, String> entry = itor.next(); 
						if (!"ROWNUM_".equals(entry.getKey())) {
							if (entry.getValue() != null) {
								mapvalue.put(entry.getKey(),EscapeUnescape.unescape(entry.getValue()));//解码
							}else {
								mapvalue.put(entry.getKey(), null);
							}
						}
					}
					listUnescape.add(mapvalue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listUnescape;
		}else {
			return listPage;
		}
		
	}
}
