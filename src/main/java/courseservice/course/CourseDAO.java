package courseservice.course;

import java.util.List;

import courseservice.exceptions.CollaboratorCallException;
import courseservice.user.User;

// BBBBBB ���� ����� �ϰ� ������ �̰� implements ��~
public class CourseDAO {

	public static List<Course> findCoursesByUser(User user) {
		throw new CollaboratorCallException(
				"CourseDAO should not be invoked on an unit test.");
	}
	
}