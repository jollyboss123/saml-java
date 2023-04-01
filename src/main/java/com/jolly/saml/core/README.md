# core

## CurrentUserHandlerMethodArgumentResolver
This class provides a convenient way to inject the currently authenticated `User` object into a controller method using the `@CurrentUser` annotation.

- This class is a Spring component that implements the `HandlerMethodArgumentResolver` interface, which provides a way to resolve controller method arguments. Specifically, this class resolves the `User` object annotated with `@CurrentUser` in controller methods.

- The `supportsParameter` method checks whether the parameter annotated with `@CurrentUser` is of type User. If it is, the method returns true and the `resolveArgument` method is called.

- The `resolveArgument` method retrieves the `Principal` object from the `NativeWebRequest` and casts it to an `Authentication` object. From the `Authentication` object, the `User` object is extracted and returned.

## SAMLUserDetailsServiceImpl
- This is a service class `SAMLUserDetailsServiceImpl` that implements the `SAMLUserDetailsService` interface, which provides a method to load user details based on the SAML credential. The `loadUserBySAML` method is responsible for identifying the local account of a user based on the data present in the SAML assertion and returning a `UserDetails` object that describes the user.

- In this specific implementation, the loadUserBySAML method takes in a SAMLCredential object, which contains the user's SAML authentication data. It then extracts the user ID from the `SAMLCredential` and creates a List of `GrantedAuthority` objects, which represent the user's authorization roles. In this example, there is only one role (ROLE_USER).

- This implementation is a simplified one and doesn't actually locate the user in a data store based on the information present in the `SAMLCredential`. Instead, it simply returns a hardcoded `User` object that represents the user. In a real scenario, this method would need to locate the user in a data store and construct a `UserDetails` object based on that information.

