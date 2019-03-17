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
    private class URI {}

    // Should the request actually dictate return types?
    // Or does the user only know the URL, and it's up to the server to decide response format?
    // The Client at least *expects* a certain format.

    public Lesson functionComposition(
        Function<Click, HttpRequest<Json<List<Commit>>>> frontEndCode,
        Function<HttpRequest<?>, User> getAuthenticatedUser,
        Function<User, List<Commit>> retrieveCommitsForUser,
        Function<List<Commit>, Json<List<Commit>>> listJsonSerializer
    ) {

        Function<Click, Json<List<Commit>>> gitUserActivity =
            frontEndCode
                .andThen(getAuthenticatedUser)
                .andThen(retrieveCommitsForUser)
                .andThen(listJsonSerializer)
            ;

        return new Lesson(EnumSet.of(Topic.Function, Topic.Composition));
    }

    private class SimulationParameters {}
    private class Particle {}
    private class Octree {}
    private class Simulation {}
    private class VideoOutput {}

    public Lesson starFormation(
        Function<SimulationParameters, List<Particle>> particleGenerator


    ) {
        Function<SimulationParameters, VideoOutput> fullProgram;


    }












    private void figureOutThisMonadTransformer(
        Function<List<Json<Commit>>, Json<List<Commit>>> someFunctionalTransformer,
        Function<Commit, Json<Commit>> serializeCommit
    ) {

        Function<List<Commit>, Json<List<Commit>>> listJsonSerializer =
            commits ->
                someFunctionalTransformer
                    .apply(
                        commits
                            .stream()
                            .map(serializeCommit)
                            .collect(Collectors.toList()));
    }
}
