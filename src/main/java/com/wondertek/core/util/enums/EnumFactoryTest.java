package com.wondertek.core.util.enums;

public class EnumFactoryTest {
	//这是我们的枚举类
	public static class MonthEnum extends BaseEnum<Integer>{
		public static MonthEnum January = new MonthEnum(1, "1月", true);
		public static MonthEnum February = new MonthEnum(2, "2月", false);
		public static MonthEnum March = new MonthEnum(3, "3月", false);
	    
	    public MonthEnum(int value, String label, boolean isDefault){  
	        super(value, label, isDefault);
	    }  
	}
	//test
	public static void main(String[] args)throws Exception {
		MonthEnum monthEnum = EnumFactory.getByValue(MonthEnum.class, 1);
		System.out.println(monthEnum);
	}
}
