package courseservice.course;

import java.util.ArrayList;
import java.util.List;

import courseservice.exceptions.UserNotLoggedInException;
import courseservice.user.User;
import courseservice.user.UserSession;

// 엉클밥에 의하면 5라인이 적당한 라인이고 3개정도의 아규먼트가 적당하다고 한다.
public class CourseService {
	private ICourseDAO dao; // BBBBBBBBBBBBBBBBBBBBB
	
	// BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	public CourseService(ICourseDAO dao) {
		super(); // 이현웅님 질문: 이거 뭔가여? 교수님 - 없어도 됨
		this.dao = dao;
	}

	// 이 메소드의 라인은 5라인이 넘는 너무 긴 코드일 수 있다.
	//public List<Course> getCoursesByUser(User user) throws UserNotLoggedInException {
	public List<Course> getCoursesByUser(User user, User loggedInUser) throws UserNotLoggedInException { // AAAAAAAAAAAA
		// courseList가 너무 먼 곳에 선언되어 있다.   
		//List<Course> courseList = new ArrayList<>(); // 111111111111
		// User loggedUser = getLoggedInUser(); // AAAAAAAAAAAAAAAAAAA
		User loggedUser = loggedInUser;
		//boolean isFriend = false; // 222222222222222222222

		// 일반적으로 if가 정상 케이스라 길곤 하다.
		// 정상 로직이 아닐 겨웅 빨리 나가는 것이 현대 스타일
		// invert if 하자 // 33333333333333333333333333333
//		if (loggedUser != null) {
//			boolean isFriend = false; // <- 여기로 이동 // 222222222222222222222222
//			for (User friend : user.getFriends()) {
//				if (friend.equals(loggedUser)) {
//					isFriend = true;
//					break;
//				}
//			}
//			
//			List<Course> courseList = new ArrayList<>(); // <- 여기로 이동 // 111111111111111111111
//			if (isFriend) {
//				courseList = courseBy(user);
//			}
//			return courseList;
//		} else {
//			throw new UserNotLoggedInException();
//		}
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		
		// 아래가 친구를 체크하므로 Extract Mehtod하자 // 4444444444444444444444444
//		boolean isFriend = false; // <- 여기로 이동
//		for (User friend : user.getFriends()) {
//			if (friend.equals(loggedUser)) {
//				isFriend = true;
//				break;
//			}
//		}
		
		// 이제 이 isFriend를 아래 메소드에 인라인으로 넣자. // 666666666666666666666
		// boolean isFriend = isFriendWith(user, loggedUser); // 이제 이렇게 바뀌었다. 44444444444 & 55555555555
		
//		List<Course> courseList = new ArrayList<>(); // <- 여기로 이동
////		if (isFriend) {
//		if (user.isFriendWith(loggedUser)) { // 66666666666666666666666666666666
//			courseList = courseBy(user); // else 경우일 대 빈 리스트가 리턴되도록, 위에 coursseList를 세퍼레이트 하고 else 붙이자 /// 7777
//		}
//		return courseList;
		
//		List<Course> courseList; // <- 여기로 이동
//		courseList = new ArrayList<>(); // <- 스필릿 /// 77777
////		if (isFriend) {
//		if (user.isFriendWith(loggedUser)) { // 66666666666666666666666666666666
//			courseList = courseBy(user); // else 경우일 대 빈 리스트가 리턴되도록, 위에 coursseList를 세퍼레이트 하고 else 붙이자 /// 7777
//		} else 
//		{
//			courseList = new ArrayList<>(); // <- else 추가 7777777777777 이제 싱글라인으로 바꾸자 88888888888888
//		}
//		return courseList;
		
//		List<Course> courseList; // <- 여기로 이동
//		courseList = new ArrayList<>(); // <- 스필릿 /// 77777
////		if (isFriend) {
//		courseList = user.isFriendWith(loggedUser) ? courseBy(user) : new ArrayList<>(); // 888888888888
//		return courseList; // 리턴도 합쳐버려 // 9999999999999999
		
		// SLA (Single Level of Abstraction)
//		return user.isFriendWith(loggedUser) ? courseBy(user) : new ArrayList<>(); //999999999999 <- ArrayList 도 함수로 바꿀 수 있다 // 1010101010
		return user.isFriendWith(loggedUser) ? courseBy(user) : emptyList();
		
	}
	
	// 1010101010109
	private List<Course> emptyList() {
		return new ArrayList<>();
	}

	// 아 이 클래스는 Friend를 판단하는 것이 과연 맞는 책임인가?
	// 이 건 user가 더 잘할 일이다. user의 책임으로 바꿔야 한다. // 5555555555555555555555
	// user.getFriends를 부른다는 것 자체가 벌써 Feature Envy 현상이 난다 (남이 가진 피쳐를 부러워하는 현상) Tell, Don't Ask; Don't talk to Stranger (Law of Demeter == Law of least Knowledge)
	// refactor -> move 명령어 이용하자
//	private boolean isFriendWith(User user, User loggedUser) {
//		boolean isFriend = false; // <- 여기로 이동
//		for (User friend : user.getFriends()) {
//			if (friend.equals(loggedUser)) {
//				isFriend = true;
//				break;
//			}
//		}
//		return isFriend;
//	}
	
	/**
	 * @deprecated Use {@link courseservice.user.User#isFriendWith(User)} instead
	 */
	private boolean isFriendWith(User user, User loggedUser) {
		return user.isFriendWith(loggedUser);
	}

	// extract method 를 통해서 기존 코드에서 메소드를 뽑았다.
	// 이거 보면 UserSession이 싱글턴을 쓰고 있는 것이보인다 BBBBBBBBBBBBBBBBBBBBBBBBB
	// Constructor Index방법으로 해결해보겠다.
	// Singleton 은 Global Single Access Point를 제공한다. 즉 글로벌 변수라고 볼 수 있으니 글로벌 변수가 가진 모든 단점을 다 가지고 있다.
	// Decoupling 하자
	protected List<Course> courseBy(User user) {
		//return CourseDAO.findCoursesByUser(user); BBBBBBBBBBB
		return dao.courseBy(user);
	}

	// extract method 를 통해서 기존 코드에서 메소드를 뽑았다.
	// 이거 보면 UserSession이 싱글턴을 쓰고 있는 것이보인다 AAAAAAAAAAAAAAAAAAAAAAAAAA
	// Singleton 은 Global Single Access Point를 제공한다. 즉 글로벌 변수라고 볼 수 있으니 글로벌 변수가 가진 모든 단점을 다 가지고 있다.
//	protected User getLoggedInUser() {
//		return UserSession.getInstance().getLoggedUser();
//	}
}