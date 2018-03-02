package courseCollection;

import java.util.List;

import course.Course;

public class CourseCollection {
	private List<Course> courseList;
	
	public CourseCollection(List<Course> courseList) {
		if (courseList == null) {
			throw new NullPointerException();
		}
		this.courseList = courseList;
	}
}
