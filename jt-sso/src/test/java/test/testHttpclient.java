package test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class testHttpclient {

	@Test
	public void test01() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String url="https://www.baidu.com/";
		HttpGet httpGet=new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if(response.getStatusLine().getStatusCode()==200) {
			System.out.println("请求结果:");
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			System.out.println(result);
		}
	}
}
