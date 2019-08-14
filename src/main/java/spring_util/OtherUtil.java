package spring_util;

public class OtherUtil {

	/**
	 * 判断是否是main 方法调用
	 * @param thread
	 * @return
	 */
	protected boolean isMain(Thread thread) {
		return thread.getName().equals("main") && thread.getContextClassLoader()
				.getClass().getName().contains("AppClassLoader");
	}
	/**
	 * 获取线程的堆栈信息
	 * @param thread
	 */
	public void getThreadStackTrace(Thread thread) {
		StackTraceElement[] stackTrace = thread.getStackTrace();
	}
	
	public void getNowThreadStackTrace() {
		StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
	}
}
