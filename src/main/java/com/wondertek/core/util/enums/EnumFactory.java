package com.wondertek.core.util.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EnumFactory {
	
	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<Class, Set> map = new ConcurrentHashMap<Class, Set>();

	@SuppressWarnings("unchecked")
	public static <V, T extends BaseEnum<V>> void add(T t) {
		Set<BaseEnum<V>> list = map.get(t.getClass());
		if (list == null) {
			list = new HashSet<BaseEnum<V>>();
			map.putIfAbsent(t.getClass(), list);
			list = map.get(t.getClass());
		}
		list.add(t);
	}

	public static <V, T extends BaseEnum<V>> T getByValue(Class<T> clazz, V value) {
		return getByValue(clazz, value, false);
	}
	
	@SuppressWarnings("unchecked")
	public static <V, T extends BaseEnum<V>> T getByValue(Class<T> clazz, V value, boolean useDefaultOnMiss) {
		init(clazz);
		BaseEnum<V> defaultEnum = null;
		Set<BaseEnum<V>> list = map.get(clazz);
		for (BaseEnum<V> be : list) {
			if (be.getValue().equals(value)) {
				return (T) be;
			}
			if(useDefaultOnMiss && be.isDefault()) {
				defaultEnum = be;
			}
		}
		return (T)defaultEnum;
	}

	public static <T extends BaseEnum<V>, V> T getByLabel(Class<T> clazz, String label) {
		return getByLabel(clazz, label, false);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends BaseEnum<V>, V> T getByLabel(Class<T> clazz, String label, boolean useDefaultOnMiss) {
		init(clazz);
		BaseEnum<V> defaultEnum = null;
		Set<BaseEnum<V>> list = map.get(clazz);
		for (BaseEnum<V> be : list) {
			if (be.getLabel().equals(label)) {
				return (T) be;
			}
			if(useDefaultOnMiss && be.isDefault()) {
				defaultEnum = be;
			}
		}
		return (T)defaultEnum;
	}

    @SuppressWarnings("unchecked")
	public static <T extends BaseEnum<V>, V> List<T> getAll(Class<T> clazz) {
        init(clazz);
        Set<T> set = map.get(clazz);
        if (set != null && set.size() > 0){
        	List<T> list =  new ArrayList<T>(set.size());
            list.addAll(set);
            return list;
        }
        return null;
	}
    
    private static synchronized void init(Class<?> clazz){
		if(map.get(clazz) != null){
			return;
		}
        try {
            Field[] fields = clazz.getFields();
            for (Field f : fields) {
                f.get(null);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
