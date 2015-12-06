package dao;

import org.hibernate.SessionFactory;

import model.Khoa;

public class KhoaDaoImpl extends GenericDaoImpl<Khoa, Long> implements KhoaDao{

	public KhoaDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
