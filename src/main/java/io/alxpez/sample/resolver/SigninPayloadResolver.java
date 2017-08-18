package io.alxpez.sample.resolver;


import com.coxautodev.graphql.tools.GraphQLResolver;
import io.alxpez.sample.pojo.SigninPayload;
import io.alxpez.sample.pojo.User;


/*
* Because SigninPayload data class contains a complex (non-scalar) object User,
* it needs a companion resolver class
* */
public class SigninPayloadResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
