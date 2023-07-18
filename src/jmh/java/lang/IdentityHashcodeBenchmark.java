package lang;

import java.lang.Integer;
import java.lang.Object;
import java.util.Random;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

public class IdentityHashcodeBenchmark {

  @State(Scope.Benchmark)
  public static class BenchmarkState {
    @Param({"10000", "100000", "1000000"})
    public int size;

    public Object[] input;

    @Setup(Level.Trial)
    public void setup() {
      input = new Random().ints(size).mapToObj(i -> Integer.valueOf(i)).toArray();
    }
  }

  @Benchmark
  public void checksum(BenchmarkState state, Blackhole blackhole) {
    int sum = 0;
    for (int i = 0; i < state.size; i++) {
      sum += state.input[i].hashCode();
    }
    blackhole.consume(sum);
  }
}