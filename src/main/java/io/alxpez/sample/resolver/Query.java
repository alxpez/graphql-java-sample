package io.alxpez.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import io.alxpez.sample.pojo.Link;
import io.alxpez.sample.repository.LinkRepository;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
}