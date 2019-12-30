/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package cuong.sample;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
//@Fork(value = 1, jvmArgsPrepend = {"-XX:-UseBiasedLocking"})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MyBenchmark {


    int x;

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }


    @State(Scope.Thread)
    public static class MyState {
        public List<String> strings = Arrays.asList(createArray());
        public List<String> stringsLinked = new LinkedList<>(Arrays.asList(createArray()));
        int loopOfLoop = 1000000;
    }

    @Benchmark
    public int loopFor(MyState state) {
        int sum = 0;
        var input = state.strings;
        var loopOfLoop = state.loopOfLoop;
        for (int j = 0; j < loopOfLoop; j++) {
            for (int i = 0; i < input.size(); i++) {
                String s = input.get(i);
                sum += s.length();
            }
        }
        return sum;
    }

//    @Benchmark
    public int loopForLinkedList(MyState state) {
        int sum = 0;
        var input = state.stringsLinked;
        var loopOfLoop = state.loopOfLoop;
        for (int j = 0; j < loopOfLoop; j++) {
            sum = 0;
            for (int i = 0; i < input.size(); i++) {
                String s = input.get(i);
                sum += s.length();
            }
        }
        return sum;
    }

    @Benchmark
    public int loopWhile(MyState state) {
        int sum = 0;
        var input = state.strings;
        int i = 0;
        var loopOfLoop = state.loopOfLoop;
        for (int j = 0; j < loopOfLoop; j++) {
            sum = 0;
            while (i < input.size()) {
                String s = input.get(i);
                i++;
                sum += s.length();
            }
            i = 0;
        }
        return sum;

    }

    @Benchmark
    public int loopIterator(MyState state) {
        int sum = 0;
        var input = state.strings;
        var loopOfLoop = state.loopOfLoop;
        for (int i = 0; i < loopOfLoop; i++) {
            sum = 0;
            Iterator<String> iterator = input.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                sum += next.length();
            }
        }
        return sum;
    }

    @Benchmark
    public int loopStream(MyState state) {
        var input = state.strings;
        int sum = 0;
        var loopOfLoop = state.loopOfLoop;
        for (int i = 0; i < loopOfLoop; i++) {
            sum = input.stream().reduce(i, (total, s2) -> total + s2.length(), (t1, t2) -> t1 + t2);
        }
        return sum;
    }

    @Benchmark
    public int loopStreamByMap(MyState state) {
        var input = state.strings;
        int sum = 0;
        var loopOfLoop = state.loopOfLoop;
        for (int i = 0; i < loopOfLoop; i++) {
            sum = input.stream().mapToInt(String::length).sum();
        }
        return sum;
    }


    private static String[] createArray() {
        int size = 500;
        String[] sArray = new String[size];
        for (int i = 0; i < size; i++) {
            sArray[i] = "t " + i;
        }
        return sArray;
    }
}
