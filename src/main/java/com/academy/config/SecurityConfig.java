package com.academy.config;


import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import com.academy.core.security.AcademyAuthenticationProvider;
import com.academy.core.security.DataInitializer;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@ComponentScan(basePackages={"com.academy.core.security"})
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    DataInitializer dataInitializer;

	@Autowired
    AcademyAuthenticationProvider academyAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(academyAuthenticationProvider);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
                .antMatchers("/academy/new").hasRole("ADMIN")
				.antMatchers("/members").hasRole("OWNER")
				.antMatchers("/login").permitAll()
				.antMatchers("/greeting").permitAll()
				.antMatchers("/").permitAll()
				.and().httpBasic();

	}

	@Bean
	public MapperFactory mapperFactory() {

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		ClassMapBuilder<Member, MemberBean> mapp = mapperFactory.classMap(Member.class, MemberBean.class);
		mapp.field("id","id").byDefault().register();
		return mapperFactory;
	}
}