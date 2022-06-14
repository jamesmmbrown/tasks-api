package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

@RequiredArgsConstructor
public class TaskMatcher extends TypeSafeMatcher<Task> {

    private final String name;
    private final Boolean status;

    public static TaskMatcher task(String name, Boolean status) {
        return new TaskMatcher(name, status);
    }

    @Override
    protected boolean matchesSafely(Task item) {
        return Objects.equals(item.getName(), name) &&
                Objects.equals(item.getStatus(), status);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" ");
        describeTask(description, name, status);
    }

    @Override
    protected void describeMismatchSafely(Task item, Description mismatchDescription) {
        mismatchDescription.appendText("was ");
        describeTask(mismatchDescription, item.getName(), item.getStatus());
    }

    private void describeTask(Description description, String name, Boolean status) {
        description.appendText("<Task(name=")
                .appendValue(name)
                .appendText(", status= ")
                .appendValue(status)
                .appendText(")>");
    }
}
