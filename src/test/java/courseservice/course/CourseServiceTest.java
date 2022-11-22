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
	
	// Builder Pattern 사용해보기
	static class UserBuilder {
		private Course[] courses;
		private User[] friends;

		public static UserBuilder of() {
			return new UserBuilder();
		}
		
		// ctrl+1 누르면 메뉴 나옴
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
		// 김수필님 질문 @BeforeAll 에 넣어도 되나요? 넣어도 되지만 모든 녀석에게 다 영햐을 미치니깐 상태가 변할 수 있어! 다음 테스트에 영햐을 미칠 수 있으니 하지마~!
		// 이현웅님 질문 implements를 따로 명시하지 않아도 interface를 구현하게 된 건가요? Anonymous Implement 라고 자바의 방법이야 
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
		// CourseService.java 파일에서 else 를 가는지 확인하고자 함이다
		// Given (Arrange)
		// User user = new User(); // else 테스트 시 필요 없다.
		loggedInUser = GUEST;
		
		// When (Act)
		// SUT - System Under Test or CUT - Class Under Test
		//CourseService courseService = new MyCourseService(); -> before each 로 변경
		
		// Then (Assert)
		// 어차피 else 부분 테스트할 것이니 user 파라메터를 넣을 필요 없다.
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
		
		//loggedInUser = new User(); // 친구가 아닌 유저
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
		
		//loggedInUser = new User(); // 친구가 아닌 유저
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

