package com.masm.quickpoll.client.v2;

import com.masm.quickpoll.model.Poll;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class QuickPollClientV2 {
    private static final String QUICK_POLL_URI_2 = "http://localhost:8080/v2/polls";
    private RestTemplate restTemplate = new RestTemplate();
    public PageWrapper<Poll> getAllPolls(int page, int size) {
        ParameterizedTypeReference<PageWrapper<Poll>> responseType = new
                ParameterizedTypeReference<>() {};

        UriComponentsBuilder builder =
                UriComponentsBuilder
                    .fromHttpUrl(QUICK_POLL_URI_2)
                    .queryParam("page", page)
                    .queryParam("size", size);

        ResponseEntity<PageWrapper<Poll>> responseEntity = restTemplate.exchange
                (builder.build().toUri(),
                        HttpMethod.GET,
                        null,
                        responseType);

        return responseEntity.getBody();
    }

    public static void main(String[] args) {
        QuickPollClientV2 client = new QuickPollClientV2();

        PageWrapper<Poll> allPolls = client.getAllPolls(2, 5);
        System.out.println("Total polls in page "+allPolls.getSize());
    }
}
