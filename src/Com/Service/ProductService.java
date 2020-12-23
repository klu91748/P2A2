package Com.Service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Com.Model.Product;
import Com.Utility.HibernateUtility;

public class ProductService {
	
	public void add(Product p) {
		try {
			Session session = HibernateUtility.getSession();
			Transaction tx = session.beginTransaction();
			
			session.save(p);
			tx.commit();
			
			System.out.println("Inserted product");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
