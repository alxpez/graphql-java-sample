package io.alxpez.sample.repository;

import io.alxpez.sample.pojo.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkRepository {

    private final List<Link> links;

    public LinkRepository() {
        links = new ArrayList<>();
        //add some links to start off with
        links.add(new Link("https://alxpez.github.io", "My website"));
        links.add(new Link("http://graphql.org/learn/", "The official graphQL docks"));
    }

    public List<Link> getAllLinks() {
        return links;
    }

    public void saveLink(Link link) {
        links.add(link);
    }
}