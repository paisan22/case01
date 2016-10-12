package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.*;
import java.util.List;


/**
 * Created by paisanrietbroek on 11/10/16.
 */

@NoArgsConstructor
@Getter
@Setter
public class ImportCourses {
    private FileReader filereader;
    private BufferedReader br;
    private List<String> lines = new ArrayList<>();
    private List<Course> courseList = new ArrayList<>();

    public List<Course> importFile(String pathToFile) throws IOException {
        readLines(pathToFile);
        convertToObjects();
        return courseList;
    }

    public void readLines(String path) throws IOException {
        filereader = new FileReader(path);
        br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(trimLine(line));
        }
    }

    public String trimLine(String line) {
        String parts[] = line.split(":");
        return line = parts[1].replaceFirst(" ", "");
    }

    public void convertToObjects() {
        int count = 0;
        Course course = new Course();
        for (String line : lines) {

            if (count <= 3) {

                switch (count) {
                    case 0:
                        course.setTitle(line);
                        break;
                    case 1:
                        course.setCursusCode(line);
                        break;
                    case 2:
                        int days = Integer.parseInt(String.valueOf(line.charAt(0)));
                        course.setNumberOfDays(days);
                        break;
                    case 3:
                        // 14/10/2013
                        String parts[] = line.split("/");
                        int day = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);

                        LocalDate localDate = LocalDate.of(year, month, day);
                        course.setStartDate(localDate);
                        break;
                }

                count++;

                if (count > 3) {
                    courseList.add(course);
                    course = new Course();
                    count = 0;
                }

            }

        }
    }
}

