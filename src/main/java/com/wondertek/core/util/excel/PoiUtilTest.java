package com.wondertek.core.util.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wondertek.core.util.IOUtil;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年8月23日 下午1:48:42<br/>
 */
public class PoiUtilTest {
	public static class User{//不用遵守javabean规范
    	@FiledOrder(0)
    	private int id;
    	@FiledOrder(1)
    	private String name;
    	@FiledOrder(2)
    	private Date birthDay;
    	@FiledOrder(3)
    	private boolean isMale;
    	public User() {}
    	public User(int id, String name, Date birthDay, boolean isMale) {
    		this.id = id;
    		this.name = name;
    		this.birthDay = birthDay;
    		this.isMale = isMale;
    	}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", birthDay=" + birthDay + ", isMale=" + isMale + "]";
		}
    }
    public static void main(String args[])throws Exception {
    	String file = "C:\\Users\\xujs\\Desktop\\aa.xls";
    	User user1 = new User(1, "aaa", new Date(), true);
    	User user2 = new User(2, "bbb", new Date(), false);
    	User user3 = new User(3, "ccc", new Date(), true);
    	User user4 = new User(4, "ddd", new Date(), false);
    	List<User> users = new ArrayList<User>(4);
    	users.add(user1);users.add(user2);users.add(user3);users.add(user4);
    	FieldSerializer<User, Boolean> serializer = new FieldNameSerializer<User, Boolean>("isMale") {
			@Override
			public String serialize(User bean, Boolean fieldValue) {
				if(fieldValue) {
					return "是";
				}else {
					return "否";
				}
			}
    	};
    	byte[] bytes = PoiUtil.writeExcel(new String[] {"id","姓名","生日","是否男性"}, users, serializer);
    	OutputStream out = new FileOutputStream(file);
    	out.write(bytes);
    	out.close();
    	System.out.println("write over");
    	
    	InputStream in = new FileInputStream(file);
    	bytes = IOUtil.readInputStream(in);
    	in.close();
    	List<User> list = PoiUtil.readExcel(file,bytes, User.class);
    	System.out.println(list.size());
    	for(User u : list) {
    		System.out.println(u);
    	}
    	System.out.println("read over");
	}
}
