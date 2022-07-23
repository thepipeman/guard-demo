package io.pipecrafts.guard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer security) throws Exception {
    security.authenticationManager(authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    final var clientDetailsService = new InMemoryClientDetailsService();
//
//    final var clientCreds = new BaseClientDetails();
//    clientCreds.setClientId("client");
//    clientCreds.setClientSecret("secret");
//    clientCreds.setScope(List.of("read"));
//    clientCreds.setAuthorizedGrantTypes(List.of("password", "client_credentials"));
//
//    clientDetailsService.setClientDetailsStore(Map.of("client", clientCreds));
//    clients.withClientDetails(clientDetailsService);
    clients.inMemory()
      .withClient("client")
      .secret("secret")
      .authorizedGrantTypes("password", "refresh_token")
      .scopes("read")
      .and()
      .withClient("client2")
      .secret("secret")
      .authorizedGrantTypes("authorization_code", "refresh_token")
      .scopes("read")
      .redirectUris("http://localhost:9011/home")
      .and()
      .withClient("client3")
      .secret("secret")
      .authorizedGrantTypes("client_credentials")
      .scopes("read");
  }
}
