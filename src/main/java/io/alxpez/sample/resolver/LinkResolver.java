package io.alxpez.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import io.alxpez.sample.pojo.Link;
import io.alxpez.sample.pojo.User;
import io.alxpez.sample.repository.UserRepository;

public class LinkResolver implements GraphQLResolver<Link> {

    private final UserRepository userRepository;


    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return  userRepository.findById(link.getUserId());
    }
}
