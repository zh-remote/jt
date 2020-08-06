package com.jt.web.thread;

import com.jt.web.pojo.User;

public class UserThreadLocal {
	private static ThreadLocal<User> thread=new ThreadLocal();
    public static void set(User user) { 
    	thread.set(user);
    }
    public static User get() {
    	return thread.get();
    }

    //需要手动关闭，垃圾回收机制不会管本地线程
    public static void remove() {
    	thread.remove();
    }
}
