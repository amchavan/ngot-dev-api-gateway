package alma.obops.ngot.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author amchavan 14-Nov-2022
 */
@Configuration
public class RouteConfig {

//    @Bean
//    public KeyResolver hostAddressKeyResolver() {
//        return exchange -> {
//            final var hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
//            return Mono.just( hostAddress );
//        };
//    }

    @Bean
    public RouteLocator myRoutes( RouteLocatorBuilder builder ) {

        return builder.routes()
                
                .route( "identity-provider",
                        p -> p.path( "/ngotGateway/identity-provider/**" )
                              .filters(f -> f.rewritePath( "/ngotGateway/identity-provider/(?<segment>.*)",
                                                           "/dev-keycloak/auth/realms/ALMA/${segment}" ))
                                             .uri( "https://www.eso.org" ))

//                .route( "book2", p -> p
//                        .path("/book/**")
//                        .and().weight( "a", 5 )
//                        .filters(f -> f.rewritePath( "/book/(?<id>.*)", "/book-service/book/${id}" ))
//                        .uri( "http://localhost:10001" )
//                )

                .build();
    }
}
