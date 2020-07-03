package ru.javawebinar.topjava.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;

public class TimeLogger implements TestRule {
    private Map<String,Long> map = new HashMap<>();

    public Map<String, Long> getMap() {
        return map;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String name = description.getMethodName();
                statement.evaluate();
                long startTime = System.currentTimeMillis();
                statement.evaluate();
                long endTime = System.currentTimeMillis();
                map.put(name,endTime-startTime);
            }
        };
    }
}
