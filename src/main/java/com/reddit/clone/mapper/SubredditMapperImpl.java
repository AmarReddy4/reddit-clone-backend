package com.reddit.clone.mapper;

import com.reddit.clone.dto.SubredditDto;
import com.reddit.clone.model.Subreddit;
import org.springframework.stereotype.Component;

@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        return null;
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subredditDto) {
        return null;
    }
}
