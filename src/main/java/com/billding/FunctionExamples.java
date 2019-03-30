package com.billding;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
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
        Function<List<Screenshot>, VideoOutput> createVideoFrom,
        Consumer<Screenshot> printOnScreen, // TODO Maybe "ScreenShot" isn't the best name. Frame?
        Consumer<Screenshot> saveScreenshotToDisk,
        Consumer<VideoOutput> saveVideoToDisk,
        Supplier<List<Screenshot>> getScreenshotsFromDisk
    ) {
        Function<SimulationParameters, VideoOutput> fullProgram;

        Function<SimulationParameters, Simulation> initializeSimulation = particleGenerator
            .andThen(createTreeContaining)
            .andThen(createSimulationWith);

        Simulation simulation = initializeSimulation.apply(simulationParameters);

        int numberOfSteps = simulationParameters.runTime().dividedBy(simulationParameters.dt());

        BinaryOperator<Simulation> simulationBinaryOperator =
            (sim1, sim2) -> {
                printOnScreen.andThen(saveScreenshotToDisk);
                printOnScreen.accept(snapshot.apply(sim2));
                return sim2; // Afer sim1 has been used to create sim2, we can trash it.
            };

        Stream<Integer> steps = Stream.of(1, 2, 3);
        Simulation completedSimulation = steps.reduce(
            simulation,
            (sim, step) -> sim.update(simulationParameters.dt()),
            simulationBinaryOperator
        );

        List<Screenshot> screenshots = getScreenshotsFromDisk.get();
//        createVideoFrom.compose(getScreenshotsFromDisk);
        createVideoFrom.andThen(saveVideoToDisk);
        createVideoFrom.apply(screenshots);
        return new Lesson(EnumSet.of(Topic.Function, Topic.Composition));
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
