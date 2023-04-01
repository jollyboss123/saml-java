# config

## MvcConfig:
This class provides callbacks for configuring Spring MVC
- `addViewControllers`: maps a specific URL path to a view name, in this case mapping the root path ("/") to the "pages/index" view.
- `addResourceHandlers`: adds a resource handler for serving static resources such as CSS, JavaScript, and images. It maps any URL that starts with "/static/" to the "/static/" directory in the web application.
- `addArgumentResolvers`: adds a custom HandlerMethodArgumentResolver implementation, CurrentUserHandlerMethodArgumentResolver, to the list of argument resolvers. This resolver is used to resolve the CurrentUser parameter in controller methods.
- `viewResolver` bean definition that configures a JSP view resolver. Can be configured to use JSP views instead of Thymeleaf or another view technology.

## WebSecurityConfig:
This class has several methods annotated with the `@Bean` annotation, which define various components such as a **Velocity engine**, an **HTTP client**, a **parser pool**, and various SAML profile implementations. These components are used to _handle the SAML messages_ exchanged between the Identity Provider (IDP) and the Service Provider (SP).
Beans are also defined for SSO profiles, such as **WebSSOProfile**, **WebSSOProfileConsumer**, and **WebSSOProfileConsumerHoKImpl**.
- A `MetadataGenerator` bean generates the Service Provider (SP) metadata and a `MetadataGeneratorFilter` is added to the filter chain to display the metadata.
- The `SAMLEntryPoint` is the entry point for SSO and the `SAMLLogoutProcessingFilter` is added to handle logout requests.
- The `FilterChainProxy` is configured with a list of `SecurityFilterChain` objects to handle SAML requests and responses.
- The `HttpSecurity` configuration permits unauthenticated access to the root URL and URLs for SAML-related endpoints, while requiring authentication for all other URLs.
- A custom `SAMLAuthenticationProvider` is used to authenticate users with SAML assertions.
- Velocity engine: 
  - Velocity is a Java-based template engine that can be used to generate dynamic HTML pages, emails, or any other text-based format. It is a popular open-source project maintained by the Apache Software Foundation. The Velocity engine allows you to define templates that contain placeholders for variables that can be replaced with dynamic values at runtime.
- HTTP client: 
  - An HTTP client is a program or library that sends HTTP requests to a web server and receives HTTP responses. The HTTP client is responsible for establishing a connection with the server, sending requests, and processing the server's response. In the context of SAML, the HTTP client is used to communicate with SAML identity providers and service providers.
- Parser pool: 
  - A parser pool is a collection of XML parsers that can be reused across multiple threads. Parsing XML can be an expensive operation, so reusing parsers can improve performance. In the context of SAML, the parser pool is used to parse SAML metadata and SAML responses.
- SAML profile implementations: 
  - SAML defines several profiles that specify how SAML messages should be exchanged between identity providers and service providers. The SAML specification includes profiles for single sign-on (SSO), single logout (SLO), and other use cases. Implementing these profiles involves understanding the SAML specification and configuring the SAML software to comply with the specification.
- The `configure(HttpSecurity http)` method configures the HTTP security of the application, defining which requests are allowed or denied access based on the user's authentication status. The method also sets up filters to handle SAML messages and to generate SP metadata.
- The `configure(AuthenticationManagerBuilder auth)` method sets up a custom authentication provider, which is responsible for validating SAML messages and extracting user details from them.
- The `InitializingBean` and `DisposableBean` interfaces provide a mechanism for executing initialization and cleanup code for the SAML components.


