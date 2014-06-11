/**
 * 
 */
package wei.ssh.service;

import java.util.List;
import java.util.Map;

import wei.ssh.model.AreaChina;
import wei.ssh.model.UserInfo;

/**
 * @author Jerry
 *
 */
public interface IService {
	public void update(Object object);
	
	public void updateViaHibernate(Object object);
	
	public List<AreaChina> getList();
	
	public List<UserInfo> getUserInfo();
	
	public AreaChina getUserArea();

	public List<Map<String, Object>> queryList();
	
}
