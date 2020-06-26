package web.util;

public class MyGenerator {
	public static String generateOrderId(String code) {
		String kq = "ORD";
		int num = Integer.parseInt(code.substring(3));
		num++;
		return kq + String.format("%03d", num);
	}

	public static String generateOrderDetailId(String code) {
		String kq = "ORDDE";
		int num = Integer.parseInt(code.substring(5));
		num++;
		return kq + String.format("%03d", num);
	}
	public static String generateProductId(String code) {
		String kq = "S";
		int num = Integer.parseInt(code.substring(1));
		num++;
		return kq + String.format("%03d", num);
	}
}
