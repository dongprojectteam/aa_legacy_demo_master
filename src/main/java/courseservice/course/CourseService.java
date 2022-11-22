package courseservice.course;

import java.util.ArrayList;
import java.util.List;

import courseservice.exceptions.UserNotLoggedInException;
import courseservice.user.User;
import courseservice.user.UserSession;

// ��Ŭ�信 ���ϸ� 5������ ������ �����̰� 3�������� �ƱԸ�Ʈ�� �����ϴٰ� �Ѵ�.
public class CourseService {
	private ICourseDAO dao; // BBBBBBBBBBBBBBBBBBBBB
	
	// BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	public CourseService(ICourseDAO dao) {
		super(); // �������� ����: �̰� ������? ������ - ��� ��
		this.dao = dao;
	}

	// �� �޼ҵ��� ������ 5������ �Ѵ� �ʹ� �� �ڵ��� �� �ִ�.
	//public List<Course> getCoursesByUser(User user) throws UserNotLoggedInException {
	public List<Course> getCoursesByUser(User user, User loggedInUser) throws UserNotLoggedInException { // AAAAAAAAAAAA
		// courseList�� �ʹ� �� ���� ����Ǿ� �ִ�.   
		//List<Course> courseList = new ArrayList<>(); // 111111111111
		// User loggedUser = getLoggedInUser(); // AAAAAAAAAAAAAAAAAAA
		User loggedUser = loggedInUser;
		//boolean isFriend = false; // 222222222222222222222

		// �Ϲ������� if�� ���� ���̽��� ��� �ϴ�.
		// ���� ������ �ƴ� �ܿ� ���� ������ ���� ���� ��Ÿ��
		// invert if ���� // 33333333333333333333333333333
//		if (loggedUser != null) {
//			boolean isFriend = false; // <- ����� �̵� // 222222222222222222222222
//			for (User friend : user.getFriends()) {
//				if (friend.equals(loggedUser)) {
//					isFriend = true;
//					break;
//				}
//			}
//			
//			List<Course> courseList = new ArrayList<>(); // <- ����� �̵� // 111111111111111111111
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
		
		// �Ʒ��� ģ���� üũ�ϹǷ� Extract Mehtod���� // 4444444444444444444444444
//		boolean isFriend = false; // <- ����� �̵�
//		for (User friend : user.getFriends()) {
//			if (friend.equals(loggedUser)) {
//				isFriend = true;
//				break;
//			}
//		}
		
		// ���� �� isFriend�� �Ʒ� �޼ҵ忡 �ζ������� ����. // 666666666666666666666
		// boolean isFriend = isFriendWith(user, loggedUser); // ���� �̷��� �ٲ����. 44444444444 & 55555555555
		
//		List<Course> courseList = new ArrayList<>(); // <- ����� �̵�
////		if (isFriend) {
//		if (user.isFriendWith(loggedUser)) { // 66666666666666666666666666666666
//			courseList = courseBy(user); // else ����� �� �� ����Ʈ�� ���ϵǵ���, ���� coursseList�� ���۷���Ʈ �ϰ� else ������ /// 7777
//		}
//		return courseList;
		
//		List<Course> courseList; // <- ����� �̵�
//		courseList = new ArrayList<>(); // <- ���ʸ� /// 77777
////		if (isFriend) {
//		if (user.isFriendWith(loggedUser)) { // 66666666666666666666666666666666
//			courseList = courseBy(user); // else ����� �� �� ����Ʈ�� ���ϵǵ���, ���� coursseList�� ���۷���Ʈ �ϰ� else ������ /// 7777
//		} else 
//		{
//			courseList = new ArrayList<>(); // <- else �߰� 7777777777777 ���� �̱۶������� �ٲ��� 88888888888888
//		}
//		return courseList;
		
//		List<Course> courseList; // <- ����� �̵�
//		courseList = new ArrayList<>(); // <- ���ʸ� /// 77777
////		if (isFriend) {
//		courseList = user.isFriendWith(loggedUser) ? courseBy(user) : new ArrayList<>(); // 888888888888
//		return courseList; // ���ϵ� ���Ĺ��� // 9999999999999999
		
		// SLA (Single Level of Abstraction)
//		return user.isFriendWith(loggedUser) ? courseBy(user) : new ArrayList<>(); //999999999999 <- ArrayList �� �Լ��� �ٲ� �� �ִ� // 1010101010
		return user.isFriendWith(loggedUser) ? courseBy(user) : emptyList();
		
	}
	
	// 1010101010109
	private List<Course> emptyList() {
		return new ArrayList<>();
	}

	// �� �� Ŭ������ Friend�� �Ǵ��ϴ� ���� ���� �´� å���ΰ�?
	// �� �� user�� �� ���� ���̴�. user�� å������ �ٲ�� �Ѵ�. // 5555555555555555555555
	// user.getFriends�� �θ��ٴ� �� ��ü�� ���� Feature Envy ������ ���� (���� ���� ���ĸ� �η����ϴ� ����) Tell, Don't Ask; Don't talk to Stranger (Law of Demeter == Law of least Knowledge)
	// refactor -> move ��ɾ� �̿�����
//	private boolean isFriendWith(User user, User loggedUser) {
//		boolean isFriend = false; // <- ����� �̵�
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

	// extract method �� ���ؼ� ���� �ڵ忡�� �޼ҵ带 �̾Ҵ�.
	// �̰� ���� UserSession�� �̱����� ���� �ִ� ���̺��δ� BBBBBBBBBBBBBBBBBBBBBBBBB
	// Constructor Index������� �ذ��غ��ڴ�.
	// Singleton �� Global Single Access Point�� �����Ѵ�. �� �۷ι� ������� �� �� ������ �۷ι� ������ ���� ��� ������ �� ������ �ִ�.
	// Decoupling ����
	protected List<Course> courseBy(User user) {
		//return CourseDAO.findCoursesByUser(user); BBBBBBBBBBB
		return dao.courseBy(user);
	}

	// extract method �� ���ؼ� ���� �ڵ忡�� �޼ҵ带 �̾Ҵ�.
	// �̰� ���� UserSession�� �̱����� ���� �ִ� ���̺��δ� AAAAAAAAAAAAAAAAAAAAAAAAAA
	// Singleton �� Global Single Access Point�� �����Ѵ�. �� �۷ι� ������� �� �� ������ �۷ι� ������ ���� ��� ������ �� ������ �ִ�.
//	protected User getLoggedInUser() {
//		return UserSession.getInstance().getLoggedUser();
//	}
}