基于SpringBoot的shrio安全认证
1.关于shiro的配置主要为ShiroConfig和CustomShiroRealm两个配置类
2.在CustomShiroRealm中继承了AuthorizingRealm，需要实现两个接口
	doGetAuthorizationInfo   权限认证
	doGetAuthenticationInfo  用户认证
3.对于页面的权限是写死的ShiroConfig中的，该逻辑实现比较复杂，根据不同场景有不同的实现
4.只是一个自己做的模板，仅供参考