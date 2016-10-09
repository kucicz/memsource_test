package cz.kucicz.memsource

import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.apache.juli.logging.LogFactory

class HttpUtil {
    private static final log = LogFactory.getLog(this)

    public static String sendPost(String url, Map<String, Object> parameters) {
        try {
            log.debug("sendPost[url=${url}]")
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            parameters.each { key, value -> urlParameters.add(new BasicNameValuePair(key, (String) value)) }
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            log.error("sendPost error: ${ex.getMessage()}")
            throw new RuntimeException(ex);
        }
    }

}
