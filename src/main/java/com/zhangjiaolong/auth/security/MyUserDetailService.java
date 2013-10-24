package com.zhangjiaolong.auth.security;  
 
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zhangjiaolong.auth.dao.IUserDao;
import com.zhangjiaolong.auth.model.Role;
import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.auth.model.UserInfo;
public class MyUserDetailService implements UserDetailsService  
{  
 
    private IUserDao userDao;
 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {  
        User user = null;
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("loginName", username);
        user = this.userDao.queryAndRoleByPrimaryKey(params);
        if (user == null) {  
            throw new UsernameNotFoundException("用户名不存在！");  
        }
        if(user.isLock()){
        	throw new UsernameNotFoundException("用户名已锁定！");  
        }
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);  
      
        boolean enables = true;  
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;
        
        return new UserInfo(user.getPassword(),user,user.getLoginName(),grantedAuths,accountNonExpired, accountNonLocked, credentialsNonExpired,enables);
    }  
      
    // 取得用户的权限资源  
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        Set<Role> roles = user.getRoles();  
        for (Role role : roles) {
            authSet.add(new SimpleGrantedAuthority(role.getName()));  
        }  
        return authSet;  
    }

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
      
}  