package Com.Service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Com.Model.User;
import Com.Utility.HibernateUtility;

public class UserService {
	
	public boolean register(User u) {
		try {
			Session session = HibernateUtility.getSession();
			Transaction tx = session.beginTransaction();
			if (!isRegistered(u.getName())) {
				session.save(u);
				tx.commit();
				
				System.out.println("Inserted user");
				return true;
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean login(User user) {
		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("from User where name=:name and password=:password"); //need cap in User!!!
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
		List<User> list = query.list();
		if (list.size() == 0) {
			return false;
		}
		return true;
	}
	
	private boolean isRegistered(String username) {
		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("from User where name=:name"); //need cap in User!!!
		query.setParameter("name", username);
		List<User> list = query.list();
		if (list.size() == 0) {
			return false;
		}
		return true;
	}
}
