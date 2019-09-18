package com.yuqi.demo;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Demo1 {

	private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
			.split(",");

	/**
	 * 获取随机手机号
	 * 
	 * @return
	 */
	private static String getTel() {
		int index = getNum(0, telFirst.length - 1);
		String first = telFirst[index];
		String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
		String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
		return first + second + third;
	}

	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 * 获取4位随机数
	 * 
	 * @return
	 */
	public static int getNum() {
		return new Random().nextInt(9999 - 1000 + 1) + 1000;
	}

	/**
	 * 发送请求方法
	 */
	public static void send() {
		RestTemplate restTemplate = new RestTemplate();
		// 必须添加请求头才能访问
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("X-Requested-With", "XMLHttpRequest");
		requestHeaders.add("Referer", "http://m.alipayi1.com/");
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
		String url1 = "http://m.alipayi1.com/?act=save&mobile=" + getTel();
		ResponseEntity<String> response = restTemplate.exchange(url1, HttpMethod.GET, requestEntity, String.class);
		// String result = restTemplate.getForObject(url1, String.class);
		String result = response.getBody();
		// {"code":"1","msg":"97"}
		JSONObject jsonObject = JSON.parseObject(result);
		String msg = jsonObject.getString("msg");
		String url2 = "http://m.alipayi1.com/step2.aspx?act=save&id=" + msg + "&idcard=" + getNum() + "&password="
				+ generatePassword(new Random().nextInt(9) + 6);
		// String result2 = restTemplate.getForObject(url2, String.class);
		ResponseEntity<String> response2 = restTemplate.exchange(url2, HttpMethod.GET, requestEntity, String.class);
		String result2 = response2.getBody();
		System.out.println(result2);
	}

	/*public static void send2() {
//		HttpRequest request = HttpRequest.get("https://api.taokouling.com/tkl/tkljm?apikey=%E7%99%BB%E5%BD%95%E5%90%8E%E6%9F%A5%E7%9C%8Bapikey&tkl=%EF%BF%A5dGQeGHRuSf%EF%BF%A5");
//		request.useProxy("localhost", 8080);
		String url1 = "http://m.alipayi1.com/?act=save&mobile=" + getTel();
		HttpRequest request = HttpRequest.get(url1);
		request.useProxy("124.193.37.5", 8888);
		request.header("X-Requested-With", "XMLHttpRequest").header("Referer",
				"http://m.alipayi1.com/");
		String result = request.body();
		// {"code":"1","msg":"97"}
//		JSONObject jsonObject = JSON.parseObject(result);
//		String msg = jsonObject.getString("msg");
//		String url2 = "http://m.alipayi1.com/step2.aspx?act=save&id=" + msg + "&idcard=" + getNum() + "&password="
//				+ generatePassword(new Random().nextInt(9) + 6);
//		String result2 = HttpRequest.get(url2).header("X-Requested-With", "XMLHttpRequest")
//				.header("Referer", "http://m.alipayi1.com/").body();
		System.out.println(result);
	}*/

	/**
	 * 生成随机密码
	 * 
	 * @param length 密码位数
	 * @return
	 */
	public static String generatePassword(int length) {
		// 最终生成的密码
		String password = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
			int charOrNum = random.nextInt(2);
			if (charOrNum == 1) {
				// 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
				int temp = random.nextInt(2) == 1 ? 65 : 97;
				password += (char) (random.nextInt(26) + temp);
			} else {
				// 生成随机数字
				password += random.nextInt(10);
			}
		}
		return password;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			send();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}