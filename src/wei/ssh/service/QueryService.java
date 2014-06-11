/**
 * 
 */
package wei.ssh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import wei.ssh.model.AreaChina;
import wei.ssh.model.UserInfo;

/**
 * @author wei
 *
 */
public class QueryService implements IService{
	
	private JdbcTemplate jdbcTemplate;
	
	private HibernateTemplate htemplate;
	
	public HibernateTemplate getHtemplate() {
		return htemplate;
	}

	public void setHtemplate(HibernateTemplate htemplate) {
		this.htemplate = htemplate;
	}

	public List<Map<String,Object>> queryList(){
		String sql="select * from areachina where areaCode like '%5%'";
		
		return jdbcTemplate.queryForList(sql);
	}

	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void updateViaHibernate(final Object object) {
		htemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.saveOrUpdate(object);
				session.getSessionFactory().getCache().evictQueryRegion("cache2");
				return true;
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	public List<AreaChina> getList() {
		final String sql = "from wei.ssh.model.AreaChina ac where ac.areaCode like '%5%'";
		return htemplate.execute(new HibernateCallback<List<AreaChina>>() {

			public List<AreaChina> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(sql).setMaxResults(50).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserInfo() {
		return htemplate.execute(new HibernateCallback<List<UserInfo>>() {			
			@Override
			public List<UserInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql="select u.id,u.name,a.areaName,a.areaCode from "
						+ "userarea u left join areachina a on "
						+ "u.areaCode=a.areaCode where u.areaCode=:areaCode";
				return session.createSQLQuery(sql).addEntity(UserInfo.class).setParameter("areaCode", 755).setCacheable(true).setCacheRegion("cache2").list();

				//sql="select * from areachina where areaCode=:areaCode";
				//return session.getNamedQuery("queryUserInfo").setParameter("areaCode", 755).setMaxResults(20).list();
				//return session.createSQLQuery(sql).addEntity(AreaChina.class).setParameter("areaCode", 755).setCacheable(true).setCacheRegion("cache2").list();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	public AreaChina getUserArea() {
		htemplate.execute(new HibernateCallback<List<Object[]>>() {			
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				List<Map<String,Object>> list=session.getNamedQuery("qUserArea").setParameter("phone", "15220157260").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setCacheable(true).setCacheRegion("cache2").list();
				
				for(Map<String,Object> map:list){
					for(Map.Entry<String, Object> en:map.entrySet()){
						System.out.println(en.getKey()+"=>"+ en.getValue());
					}					
				}
				
				return null;
			}
			
		});		
		
		return htemplate.get(AreaChina.class, 755);
	}
}
