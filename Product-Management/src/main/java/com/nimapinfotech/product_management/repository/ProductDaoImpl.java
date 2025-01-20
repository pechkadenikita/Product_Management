package com.nimapinfotech.product_management.repository;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nimapinfotech.product_management.entities.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Product getProductById(Long id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return session.get(Product.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> getAllProducts(int page, int size) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return session.createQuery("from Product", Product.class).setFirstResult(page * size).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean deleteProduct(Long id) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, id);
			if (product != null) {
				if (product.getCategory() != null) {
					product.getCategory().getProducts().remove(product);
				}
				session.delete(product);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
