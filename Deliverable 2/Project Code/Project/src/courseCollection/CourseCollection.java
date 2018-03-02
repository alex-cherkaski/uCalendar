package courseCollection;

import java.util.ArrayList;
import java.util.List;

import course.Course;

public class CourseCollection {
	private List<Course> courseList;
	
	public CourseCollection(){
		this.courseList = new ArrayList<Course>();
	}
	
	public CourseCollection(List<Course> courseList) {
		if (courseList == null) {
			throw new NullPointerException();
		}
		this.courseList = courseList;
	}
	
	public void addCourse(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		if (!this.courseList.contains(course)) {
			this.courseList.add(course);
		}
	}
	
	public void removeCourse(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		if (!this.courseList.contains(course)) {
			throw new IllegalArgumentException();
		}
		this.courseList.remove(course);
	}
	
	public void removeAllCourses() {
		this.courseList.clear();
	}
}
