// �̰� ��ü�� �� BBBBBBBBBBBBBBBBBBBBBB

package courseservice.course;

import java.util.List;

import courseservice.user.User;

public interface ICourseDAO {
	List <Course> courseBy(User user);
}
