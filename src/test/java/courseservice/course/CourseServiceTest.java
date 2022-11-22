package courseservice.course;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;

import courseservice.exceptions.UserNotLoggedInException;
import courseservice.user.User;

public class CourseServiceTest {
	private User loggedInUser;
	private static final Course PATTERNS = new Course();
	private static final Course REFACTORINGS = new Course();
	private static final User GUEST = null;
	private static final User MEMBER = new User();
	private static final User UNUSED_USER = null;
	private static final User ANOTHER_USER = new User();
	private CourseService courseService;
	
	// Builder Pattern ����غ���
	static class UserBuilder {
		private Course[] courses;
		private User[] friends;

		public static UserBuilder of() {
			return new UserBuilder();
		}
		
		// ctrl+1 ������ �޴� ����
		public UserBuilder withCourses(Course ...courses) {
			this.courses = courses;
			return this;
		}
		
		public UserBuilder withFriends(User ...friends) {
			this.friends = friends;
			return this;			
		}
		
		public User build() {
			User user = new User();
			for(Course course : courses) {
				user.addCourse(course);
			}
			
			Arrays.stream(friends).forEach(friend -> user.addFriend(friend));
			return user;
		}
	}
	
	private ICourseDAO dao; // BBBBBBBBBBBBB
	@BeforeEach
	void init() {
		// BBBBBBBBBBBBBBB Fake Object
		// ����ʴ� ���� @BeforeAll �� �־ �ǳ���? �־ ������ ��� �༮���� �� ������ ��ġ�ϱ� ���°� ���� �� �־�! ���� �׽�Ʈ�� ������ ��ĥ �� ������ ������~!
		// �������� ���� implements�� ���� ������� �ʾƵ� interface�� �����ϰ� �� �ǰ���? Anonymous Implement ��� �ڹ��� ����̾� 
		dao = new ICourseDAO() {
			@Override
			public List<Course> courseBy(User user) {
				return user.courses();
			}
		};
		//courseService = new MyCourseService();
		courseService = new CourseService(dao);
		loggedInUser = MEMBER;
		
		
	}
	
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	

	
	@Test
	@Disabled
	public void should_throw_exception_if_there_is_no_loggedInUser() {
		// CourseService.java ���Ͽ��� else �� ������ Ȯ���ϰ��� ���̴�
		// Given (Arrange)
		// User user = new User(); // else �׽�Ʈ �� �ʿ� ����.
		loggedInUser = GUEST;
		
		// When (Act)
		// SUT - System Under Test or CUT - Class Under Test
		//CourseService courseService = new MyCourseService(); -> before each �� ����
		
		// Then (Assert)
		// ������ else �κ� �׽�Ʈ�� ���̴� user �Ķ���͸� ���� �ʿ� ����.
		assertThrows(
				UserNotLoggedInException.class,
//				() -> courseService.getCoursesByUser(UNUSED_USER)
				() -> courseService.getCoursesByUser(UNUSED_USER, loggedInUser) // AAAAAAAAAAA
				);
	}
	
	@Test
	@DisplayName("No Friends Test")
	public void should_return_empty_list_if_they_are_not_friends() {
		//CourseService courseService = new MyCourseService();
		
//		User user = new User();
//		user.addCourse(PATTERNS);
//		user.addCourse(REFACTORINGS);
//		user.addFriend(ANOTHER_USER);
		
		User user = UserBuilder.of().withCourses(PATTERNS, REFACTORINGS).withFriends(ANOTHER_USER).build();
		
		//loggedInUser = new User(); // ģ���� �ƴ� ����
		//loggedInUser = MEMBER;
		
		// When
		//List<Course> courses = courseService.getCoursesByUser(user); // AAAAAAAAAAAAAAAAAAA
		List<Course> courses = courseService.getCoursesByUser(user, loggedInUser); // AAAAAAAAAAAAAAAAAAA
		System.out.println(courses.size());
		
		// then
		assertEquals(0, courses.size());
	}
	
	@Test
	@DisplayName("Friends Test")
	public void should_return_empty_list_if_they_are_friends() {
		//CourseService courseService = new MyCourseService();
				
		User user = UserBuilder.of().withCourses(PATTERNS, REFACTORINGS).withFriends(ANOTHER_USER, loggedInUser).build();
		
		//loggedInUser = new User(); // ģ���� �ƴ� ����
		//loggedInUser = MEMBER;
		
		// When
//		List<Course> courses = courseService.getCoursesByUser(user);
		List<Course> courses = courseService.getCoursesByUser(user, loggedInUser); // AAAAAAAAAAAAAA
		System.out.println(courses.size());
		
		// then
		assertEquals(2, courses.size());
	}
	
	// BBBBBBBBBBBBB
//	class MyCourseService extends CourseService {
//		@Override
//		protected User getLoggedInUser() {
//			return loggedInUser;
//		}
//		
//		protected List<Course> courseBy(User user) {
//			return user.courses();
//		}
//	}

}

