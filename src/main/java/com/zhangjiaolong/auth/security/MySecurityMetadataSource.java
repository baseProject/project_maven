package com.zhangjiaolong.auth.security;  
 
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
 
import org.springframework.security.access.ConfigAttribute;  
import org.springframework.security.access.SecurityConfig;  
import org.springframework.security.web.FilterInvocation;  
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;  

import com.zhangjiaolong.auth.dao.IResourceDao;
import com.zhangjiaolong.auth.model.Resources;
import com.zhangjiaolong.auth.model.Role;
 
/**  
 *   
 * SecurityMetadataSource  
 *   
 * @author administrator  
 * @since 2012  
 * @version 1.0.0  
 *   
 */ 
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource  
{     
          
    private IResourceDao resourceDao;
    
    // 由spring调用  
    public MySecurityMetadataSource(IResourceDao resourceDao) {  
        this.resourceDao = resourceDao;  
        loadResourceDefine();
    }  
 
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;  
 
    // 加载资源与权限的关系  
    private void loadResourceDefine() {  
        if (resourceMap == null) {  
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
            List<Resources> resources = this.resourceDao.queryByParameter(null);
              
            for (Resources resource : resources){              
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();  
                  
                // 以权限名封装为Spring的security Object  
                Set<Role> roles = resource.getRoles();
                for (Role role : roles) {  
                    ConfigAttribute configAttribute = new SecurityConfig(role.getName());  
                    configAttributes.add(configAttribute);  
                    resourceMap.put(resource.getUrl(), configAttributes);  
                }  
            }  
        }  
 
        //Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();  
        //Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();  
 
    }  
 
    // 返回所请求资源所需要的权限(role name)  
    // (要有 url.indexOf("?")这样的判断，要通过判断对URL特别是Action问号之前的部分进行匹配，  
    // 防止用户请求的带参数的URL与你数据库中定义的URL不匹配，造成访问拒绝)  
    // 此处简单匹配  
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
        //获得请求的URL地址  
        String requestUrl = ((FilterInvocation) object).getRequestUrl();  
        //System.out.println("requestUrl is " + requestUrl);  
          
        if (resourceMap == null) {  
            loadResourceDefine();  
        }  
        //System.out.println(resourceMap.get(requestUrl));  
          
        //动态资源管理  
        if(resourceMap.get(requestUrl) == null) {  
            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();  
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("url", requestUrl);
            Resources resource = this.resourceDao.queryByParameterSingle(map);  
            if(resource == null) {  
                return null;  
            }  
            Set<Role> roles = resource.getRoles();  
            for (Role role : roles) {
                ConfigAttribute configAttribute = new SecurityConfig(role.getName());  
                configAttributes.add(configAttribute);  
                resourceMap.put(resource.getUrl(), configAttributes);  
            }  
        }  
          
        return resourceMap.get(requestUrl);  
    }  
      
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
          
        return null;  
    }  
 
    public boolean supports(Class<?> clazz) {  
          
        return true;  
    }

	public IResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}  