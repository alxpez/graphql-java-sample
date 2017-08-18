package io.alxpez.sample;

import graphql.servlet.GraphQLContext;
import io.alxpez.sample.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthContext extends GraphQLContext {

    private final User user;


    public AuthContext(User user, Optional<HttpServletRequest> req, Optional<HttpServletResponse> resp){
        super(req, resp);
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}
