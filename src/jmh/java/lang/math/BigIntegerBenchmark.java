package lang.math;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class BigIntegerBenchmark {

  public static void main(String[] args) {

    var state = new BenchmarkState();
    for (int i = 0; i < 10000; i++) {
      state.setup();
      var value = state.value;
      System.out.println(value.multiply(value));
    }
  }

  @State(Scope.Benchmark)
  public static class BenchmarkState {
    BigInteger value;
    Random rand = new Random();

    @Setup(Level.Invocation)
    public void setup() {
      value = new BigInteger(20*64, rand);
    }
  }

  @Benchmark
  // OpenJ9 options
  @Fork(jvmArgsAppend = "-Xjit:{*BigInteger.squareToLen*}(traceFull,log=log)")
  // GraalVM options
  //@Fork(jvmArgsAppend = {"-Dgraal.Dump=:1", "-Dgraal.MethodFilter=java.math.BigInteger.*"})
  public void benchmarkSquare(BenchmarkState state, Blackhole blackhole) {
    BigInteger value = state.value;
    blackhole.consume(value.multiply(value));
  }
}
