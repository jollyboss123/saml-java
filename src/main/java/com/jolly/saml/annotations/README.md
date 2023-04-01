# annotations

## CurrentUser
This custom annotation allows the `CurrentUserHandlerMethodArgumentResolver` class to identify which method parameters represent the current user of the application, and therefore, it can resolve them appropriately.

- This is a custom annotation class in Java. It is used to mark a parameter in a method as the current user of the application.

- The `@Target` annotation specifies where this annotation can be used, in this case, it can only be used on method parameters.

- The `@Retention` annotation specifies the retention policy of this annotation, which determines how long the annotation should be retained. In this case, it is set to `RUNTIME`, which means the annotation will be available at runtime for reflection.

- The `@Documented` annotation indicates that this annotation should be included in the generated documentation.


