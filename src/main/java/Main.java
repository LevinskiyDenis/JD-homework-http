import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.*;

import java.io.IOException;
import java.util.*;

public class Main {

    public static final String REMOTE_URL = "https://cat-fact.herokuapp.com/facts";
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Chrome")
                .setDefaultRequestConfig(RequestConfig.custom().
                        setConnectTimeout(8000)
                        .setSocketTimeout(20000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse httpResponse = httpClient.execute(request);

        List<CatFact> catFacts = objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<List<CatFact>>() {
        });

        // на https://cat-fact.herokuapp.com/facts нет поля upvotes с голосами,
        // поэтому по аналогии сделал вывод постов больше 70 символов
        catFacts.stream().filter(x -> x.getText().length() > 70).forEach(System.out::println);
    }
}