package courseservice.course;

import java.util.List;

import courseservice.exceptions.CollaboratorCallException;
import courseservice.user.User;

// BBBBBB 구현 제대로 하고 싶으면 이걸 implements 해~
public class CourseDAO {

	public static List<Course> findCoursesByUser(User user) {
		throw new CollaboratorCallException(
				"CourseDAO should not be invoked on an unit test.");
	}
	
}