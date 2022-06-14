package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

@RequiredArgsConstructor
public class TaskDTOMatcher extends TypeSafeMatcher<TaskDTO>  {

    private final Long id;
    private final String name;
    private final Boolean status;

    public static TaskDTOMatcher task(Long id, String name, Boolean status) {
        return new TaskDTOMatcher(id, name, status);
    }

    public static TaskDTOMatcher task(String name, Boolean status) {
        return new TaskDTOMatcher(null, name, status);
    }

    @Override
    protected boolean matchesSafely(TaskDTO item) {
        return Objects.equals(item.getId(), id) &&
                item.getName().equals(name) &&
                item.getStatus().equals(status);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" ");
        describeTask(description, id, name, status);
    }

    @Override
    protected void describeMismatchSafely(TaskDTO item, Description mismatchDescription) {
        mismatchDescription.appendText("was ");
        describeTask(mismatchDescription, item.getId(), item.getName(), item.getStatus());
    }

    private void describeTask(Description description, Long id, String name, Boolean status) {
        description.appendText("<TaskDTO(id=")
                .appendValue(id)
                .appendText(", name= ")
                .appendValue(name)
                .appendText(", status= ")
                .appendValue(status)
                .appendText(")>");
    }
}
