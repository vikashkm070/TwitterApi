package com.twitter.twitter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;



@RestController
@RequestMapping("/api")
public class TwitterController {

    private Twitter twitter;

    public TwitterController(Twitter twitter) {
        this.twitter = twitter;
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String query) throws TwitterException {
        ResponseList<User> result = twitter.searchUsers(query, 20);
        return result;
    }

    @GetMapping("/tweets")
    public List<Status> getUserTweets(@RequestParam String username) throws TwitterException {
        Paging paging = new Paging(1, 20);
        return twitter.getUserTimeline(username, paging);
    }
}