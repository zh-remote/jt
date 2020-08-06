package com.jt.common.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用于http请求的工具api，基于httpclient
 * @author asus
 *
 */
@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;

    /**
     * 实现post请求
     * @param url 请求路径
     * @param params 请求参数 map<String,String>
     * @param charset 编码 默认:utf-8
     * @return
     */
    public String doPost(String url,Map<String, String> params,	String charset) {
    	if(StringUtils.isEmpty(charset)) {
    		charset="utf-8";
    	}
    	//post请求对象，封装各种信息
    	HttpPost httpPost=new HttpPost(url);
    	httpPost.setConfig(requestConfig);
    	
    	String result=null;
    	try {
			if(params!=null) {
				//将参数封装为api指定接口 name=value
				List<NameValuePair> parameters=new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					BasicNameValuePair pair=new BasicNameValuePair(entry.getKey(), entry.getValue());
					parameters.add(pair);
				}
				//按照form表单封装
				UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(parameters, charset);
				httpPost.setEntity(formEntity);
				//发起请求
				CloseableHttpResponse response  = httpClient.execute(httpPost);
				
				//判断请求结果
				if(response.getStatusLine().getStatusCode()==200) {
					result=EntityUtils.toString(response.getEntity(),charset);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    public String doPost(String url) {
    	return doPost(url, null, null);
    }
   
    public String doPost(String url,Map<String, String> params) {
    	return doPost(url, params, null);
    }
    
    
    
    
    /**
     * 实现get请求
     * @throws IOException 
     * @throws ClientProtocolException 
     */
	public String doGet(String url, Map<String, String> params, String charset) {
		String result = null;
		try {
			if (StringUtils.isEmpty(charset)) {
				charset = "utf-8";
			}
			if (params != null) {
				URIBuilder builder = new URIBuilder(url);
				for (Map.Entry<String, String> entry : params.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
				url = builder.build().toString();
			}
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String doGet(String url) {
		return doGet(url, null, null);
	}

	public String doGet(String url, Map<String, String> params) {
		return doGet(url, params, null);
	}
}
