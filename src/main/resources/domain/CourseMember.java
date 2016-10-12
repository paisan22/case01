package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by paisanrietbroek on 07/10/16.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseMember {
    private int id;

    private String
        firstname,
        preposition,
        lastname,
        address,
        city;

    private List<Course> courseList;

}
