# controllers

## LandingController
- This controller class handles requests to the [/landing]() URL endpoint and adds the current user's username to the response model to be rendered in the view.

- The `@CurrentUser` annotation on the `User` parameter indicates that the `User` object should be resolved by a custom argument resolver. The Model parameter is used to add attributes to the response model, which can be used in the view to render dynamic content.

- The method uses the `SecurityContextHolder` to retrieve the current authentication instance and logs its presence or absence. It then adds the username attribute, which is retrieved from the `User` object, to the model and returns the name of the view [pages/landing]().

## SSOController
- This is a Spring MVC controller class with a base path of [/saml](). The class has an instance of `MetadataManager` injected, which is used to obtain the configured **Identity Providers** (IdPs).

- The class has a single endpoint mapped to [/discovery]() and _HTTP GET_ method. This endpoint is used for the IdP discovery process, which determines which IdP should be used for authentication.

- When this endpoint is accessed, the controller checks whether the user is already authenticated or not by inspecting the authentication context. If the user is already authenticated, the controller redirects them to the landing page. If the user is not authenticated, the controller retrieves the available IdPs from the `MetadataManager` and adds them to the model.

- The controller then returns the name of the view, which in this case is [pages/discovery](). The view is responsible for rendering the IdP selection page and presenting the available IdPs to the user.




