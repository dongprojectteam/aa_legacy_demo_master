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
		// unmodifiableList도 readonly 로 보낸다는 것이다 private이니깐 쓰지 못하게
		return Collections.unmodifiableList(courses);
	}

	// 55555555555555555555555555555
	// Replace Algorithm (더 같단하게 가능) // 666666666666
	public boolean isFriendWith(User loggedUser) {
//		boolean isFriend = false; // <- 여기로 이동
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
