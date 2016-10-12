package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.List;

/**
 * Created by paisanrietbroek on 08/10/16.
 */

@Getter
@Setter
@NoArgsConstructor
public class Course {
    private int id;
    private String cursusCode;
    private String title;
    private LocalDate startDate;
    private int numberOfDays;

    private List<CourseMember> courseMemberList;
}
