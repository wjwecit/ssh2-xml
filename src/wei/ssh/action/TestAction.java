package wei.ssh.action;

import wei.ssh.model.AreaChina;
import wei.ssh.service.QueryService;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2462415420539333403L;

	private QueryService queryService;
	
	private AreaChina areaChina; 
	
	private String name;
	
	@Override
	public String execute() throws Exception {
	
		areaChina=queryService.getUserArea();
		name=areaChina.getAreaName();
		return "success";
	}
	
	public String exe2() throws Exception{
		areaChina=queryService.getUserArea();
		areaChina.setAreaName("…Ó€⁄"+Math.random());
		queryService.updateViaHibernate(areaChina);
		areaChina=queryService.getUserArea();
		name=areaChina.getAreaName();
		return SUCCESS;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public AreaChina getAreaChina() {
		return areaChina;
	}

	public void setAreaChina(AreaChina areaChina) {
		this.areaChina = areaChina;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
