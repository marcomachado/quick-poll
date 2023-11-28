package com.masm.quickpoll.client.v1;

import com.masm.quickpoll.model.Option;
import com.masm.quickpoll.model.Poll;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuickPollClientV1 {
    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private RestTemplate restTemplate = new RestTemplate();
    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.
                class, pollId);
    }

    public List<Poll> getAllPolls() {
        return restTemplate.getForObject(QUICK_POLL_URI_V1, List.class);
    }

    public URI createPoll(Poll newPoll) {
        return restTemplate.postForLocation(QUICK_POLL_URI_V1, newPoll);
    }

    public void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_V1 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_V1 + "/{pollId}", pollId);
    }

    public static void main(String[] args) {
        System.out.println("\n#######################\nGetting 1 Poll from DB");
        QuickPollClientV1 client = new QuickPollClientV1();
        Poll poll = client.getPollById(1L);
        System.out.println(poll);

        System.out.println("\n#######################\nListing all polls");

        List<Poll> allPolls = client.getAllPolls();
        System.out.println("List size " + allPolls.size());
        System.out.println(allPolls);

        System.out.println("\n#######################\nCreating new poll");

        Poll newPoll = new Poll();
        newPoll.setQuestion("What is your favourite color?");

        Set<Option> options = new HashSet<>();
        options.add(new Option("Red"));
        options.add(new Option("Blue"));
        newPoll.setOptions(options);

        URI pollLocation = client.createPoll(newPoll);
        System.out.println("Newly Created Poll Location " + pollLocation);

        System.out.println("\n#######################\nUpdating poll ID=21");

        options.clear();
        options.add(new Option("Green"));
        options.add(new Option("Yellow"));

        Poll poll_id_4 = client.getPollById(21L);
        System.out.println(poll_id_4);
        poll_id_4.setQuestion("Which color do you prefer?");
        poll_id_4.setOptions(options);

        client.updatePoll(poll_id_4);
        Poll poll_id_4_after_put = client.getPollById(21L);
        System.out.println(poll_id_4_after_put);

        System.out.println("\n#######################\nDeleting poll ID=21");
        allPolls = client.getAllPolls();
        System.out.println("List size before delete " + allPolls.size());

        client.deletePoll(4L);

        allPolls = client.getAllPolls();
        System.out.println("List size after delete " + allPolls.size());
    }
}
