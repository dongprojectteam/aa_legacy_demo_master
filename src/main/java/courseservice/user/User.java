package courseservice.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import courseservice.course.Course;

public class User {

	private List<Course> courses = new ArrayList<>();
	private List<User> friends = new ArrayList<>();
	
	public List<User> getFriends() {
		return friends;
	}
	
	public void addFriend(User user) {
		friends.add(user);
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public List<Course> courses() {
		// unmodifiableList�� readonly �� �����ٴ� ���̴� private�̴ϱ� ���� ���ϰ�
		return Collections.unmodifiableList(courses);
	}

	// 55555555555555555555555555555
	// Replace Algorithm (�� �����ϰ� ����) // 666666666666
	public boolean isFriendWith(User loggedUser) {
//		boolean isFriend = false; // <- ����� �̵�
//		for (User friend : getFriends()) {
//			if (friend.equals(loggedUser)) {
//				isFriend = true;
//				break;
//			}
//		}
//		return isFriend;
		return friends.contains(loggedUser); // 666666666666
	}
	

}
