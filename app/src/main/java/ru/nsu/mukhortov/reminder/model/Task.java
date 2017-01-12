package ru.nsu.mukhortov.reminder.model;


import com.orm.SugarRecord;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 12/01/2017.
 */
@Data
public class Task extends SugarRecord {
    private String title;
    private String description;
    private Date deadline;
    private List<Remind> reminds;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
