package com.zhangjiaolong.frame.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NullToKongCuan {
	public static void mapNullTokong(Map<String, Object> map) {
		if (map!=null&&map.size() > 0) {
			for (String key : map.keySet()) {
				if (map.get(key) == null) {
					map.remove(key);
					map.put(key, "");
				}
			}
		}
	}

	public static List<Map<String, Object>> listNullTokong(
			List<Map<String, Object>> list) {
		List<Map<String, Object>> returnlist = null;
		if (list!=null&&list.size() > 0) {
			returnlist = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : list) {
				if (map.size() > 0) {
					for (String key : map.keySet()) {
						if (map.get(key) == null) {
							map.remove(key);
							map.put(key, "");
						}
					}
				}
				returnlist.add(map);
			}
		}
		return returnlist;
	}
}
