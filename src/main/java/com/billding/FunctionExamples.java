package com.billding;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    private interface Duration {
        int dividedBy(Duration duration);
    };
    private interface SimulationParameters {
        Duration runTime();
        Duration dt();
    }
    private interface Particle {}
    private interface Octree {}
    private interface Simulation {
        Simulation update(Duration dt);
    }
    private interface Screenshot {}
    private class VideoOutput {}

    public Lesson starFormation(
        SimulationParameters simulationParameters,
        Function<SimulationParameters, List<Particle>> particleGenerator,
        Function<List<Particle>, Octree> createTreeContaining,
        Function<Octree, Simulation> createSimulationWith,
        Function<Simulation, Screenshot> snapshot,
        Function<List<Screenshot>, VideoOutput> createVideoFrom
    ) {
        Function<SimulationParameters, VideoOutput> fullProgram;

        Function<SimulationParameters, Simulation> initializeSimulation = particleGenerator
            .andThen(createTreeContaining)
            .andThen(createSimulationWith);

        Simulation simulation = initializeSimulation.apply(simulationParameters);

        int numberOfSteps = simulationParameters.runTime().dividedBy(simulationParameters.dt());

        BinaryOperator<Simulation> simulationBinaryOperator = null;
        Stream.of(1, 2, 3).reduce(
            simulation,
            (sim, step) -> sim,
            simulationBinaryOperator);
        for (int i = 0; i < numberOfSteps; i++) {

        }

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
