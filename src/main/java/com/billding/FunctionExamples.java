package com.billding;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExamples {
    private class Click {}
    private class HttpRequest<T> {}
    private class User {}
    private class Commit {}
    private class Json<T> {}


    public Lesson predicateExamples(
        Function<Click, HttpRequest<Json<List<Commit>>>> frontEndCode,
        Function<HttpRequest<?>, User> getAuthenticatedUser,
        Function<User, List<Commit>> retrieveCommitsForUser,
        Function<Commit, Json<Commit>> serializeCommit,
        // Getting shaky here
        Function<List<Json<Commit>>, Json<List<Commit>>> someFunctionalTransformer
    ) {

        Function<List<Commit>, Json<List<Commit>>> listJsonSerializer =
             commits ->
                someFunctionalTransformer
                    .apply(
                            commits
                                .stream()
                                .map(serializeCommit)
                                .collect(Collectors.toList()));
        frontEndCode
            .andThen(getAuthenticatedUser)
            .andThen(retrieveCommitsForUser)
            .andThen(listJsonSerializer)
            ;
        Function<Click, Json<List<Commit>>> gitUserActivity;

        return new Lesson(EnumSet.of(Topic.Function, Topic.Composition));
    }
}
