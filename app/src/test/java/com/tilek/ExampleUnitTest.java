package com.tilek;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("g");
        answers.add("a");
        answers.add("b");
        answers.add("c");
        ArrayList<String> answers2 = new ArrayList<>();
        answers.add("c");
        answers.add("a");
        answers.add("b");
        answers.add("g");
        Collections.shuffle(answers);
        assertEquals(answers, answers2);
    }
}