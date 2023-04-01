## What is SAML?
SAML (Security Assertion Markup Language) is an XML-based framework for exchanging authentication and authorization data between parties, in particular, between an identity provider (IdP) and a service provider (SP).

## Overview
This project provides a basic implementation of a SAML Service Provider (SP) using Spring Boot and Spring Security. It demonstrates how to integrate SAML authentication into a Java web application using the Spring Security SAML extension.

## Features
* Support for SAML Web Browser SSO Profile
* Support for HTTP Redirect and HTTP POST bindings
* Service Provider (SP) initiated SSO flow
* Logout using SAML Single Logout Profile
* Support for HTTP Redirect and HTTP POST bindings
* Supports multiple Identity Providers
* Requirements
* Java 8 or higher
* Spring Boot 2.x.x
* Maven 3.x.x 

## Installation and Configuration
* To use this project, you need to:
* Clone the repository
* Configure the SAML properties in `src/main/resources/application.yml` file
* Customize the application to your needs

## How to run
* Run the application using Maven 
* Access the application at http://localhost:8080/
* The user will be redirected to the Identity Provider (IdP) login page where they will enter their credentials
* Upon successful authentication, the user will be redirected back to the Service Provider (SP) application
* The user can then access the protected resource(s) in the SP application
## References
* [Spring Security SAML Extension](https://docs.spring.io/spring-security-saml/docs/current/reference/htmlsingle/)
* [SAML Specification](https://www.oasis-open.org/committees/tc_home.php?wg_abbrev=security)