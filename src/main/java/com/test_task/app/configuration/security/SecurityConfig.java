package com.test_task.app.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

  private static final String LIST_AVAILABLE_GOODS = "/api/product/products";
  private static final String PLACE_ORDER = "/api/order";
  private static final String ADD_PRODUCT = "/api/product";

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails manager = User.builder()
            .username("manager")
            .password("{noop}1234")
            .roles(RoleName.MANAGER.name())
            .build();

    UserDetails client = User.builder()
            .username("client")
            .password("{noop}1234")
            .roles(RoleName.CLIENT.name())
            .build();

    return new InMemoryUserDetailsManager(manager, client);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector)
          throws Exception {
    MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

    return httpSecurity.authorizeHttpRequests(configure ->
                    configure
                            .requestMatchers(mvc.pattern(LIST_AVAILABLE_GOODS))
                            .hasAnyRole(RoleName.MANAGER.name(), RoleName.CLIENT.name())
                            .requestMatchers(mvc.pattern(PLACE_ORDER))
                            .hasRole(RoleName.CLIENT.name())
                            .requestMatchers(mvc.pattern(ADD_PRODUCT))
                            .hasRole(RoleName.MANAGER.name())
                            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .headers(AbstractHttpConfigurer::disable)
            .build();

  }
}
