package lang;

import java.lang.Math;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class MathBenchmark {

    @State(Scope.Thread)
    public static class LongPair {
        Random random = new Random();
        long lhs;
        long rhs;

        @Setup(Level.Invocation)
        public void setup() {
            lhs = random.nextLong();
            rhs = random.nextLong();
        }
    }

    @State(Scope.Thread)
    public static class IntPair {
        Random random = new Random();
        int lhs;
        int rhs;

        @Setup(Level.Invocation)
        public void setup() {
            lhs = random.nextInt();
            rhs = random.nextInt();
        }
    }

    @Benchmark
    public void benchmarkMultiplyHigh(Blackhole blackhole, LongPair arguments) {
        blackhole.consume(Math.multiplyHigh(arguments.lhs, arguments.rhs));
    }

    @Benchmark
    public void benchmarkMultiplyHighByPow2(Blackhole blackhole, LongPair arguments) {
        blackhole.consume(Math.multiplyHigh(arguments.lhs, 1L << 63L));
    }

    @Benchmark
    public void benchmarkIntAddExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.addExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongAddExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.addExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkIntSubtractExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.subtractExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongSubtractExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.subtractExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }
    @Benchmark
    public void benchmarkIntMultiplyExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.multiplyExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongMultiplyExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.multiplyExact(arguments.lhs, arguments.rhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkIntAbsExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.absExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongAbsExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.absExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkIntIncrementExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.incrementExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongIncrementExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.incrementExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkIntDecrementExact(Blackhole blackhole, IntPair arguments) {
        try {
            blackhole.consume(Math.decrementExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

    @Benchmark
    public void benchmarkLongDecrementExact(Blackhole blackhole, LongPair arguments) {
        try {
            blackhole.consume(Math.decrementExact(arguments.lhs));
        } catch (ArithmeticException ex) {}
    }

}