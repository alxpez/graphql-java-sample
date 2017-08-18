package io.alxpez.sample;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;
import io.alxpez.sample.pojo.User;
import io.alxpez.sample.repository.LinkRepository;
import io.alxpez.sample.repository.UserRepository;
import io.alxpez.sample.resolver.LinkResolver;
import io.alxpez.sample.resolver.Mutation;
import io.alxpez.sample.resolver.Query;
import io.alxpez.sample.resolver.SigninPayloadResolver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;

    static {
        MongoDatabase mongo = new MongoClient().getDatabase("java-sanbox");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(linkRepository),
                        new Mutation(linkRepository, userRepository),
                        new SigninPayloadResolver(),
                        new LinkResolver(userRepository))
                .build()
                .makeExecutableSchema();
    }


    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        User user = request
                .map(req -> req.getHeader("Authorization"))
                .filter(id -> !id.isEmpty())
                .map(id -> id.replace("Bearer ", ""))
                .map(userRepository::findById)
                .orElse(null);

        return new AuthContext(user, request, response);
    }
}