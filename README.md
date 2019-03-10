# RentalApplication

[![Build Status](https://travis-ci.org/travis-ci/travis-web.svg?branch=master)](https://travis-ci.org/travis-ci/travis-web)

This is a rental application built with spring boot.

### Project structure
The project consists of 4 modules.

    - client module : A Vue application to communicate with the server
    - web module : A maven project which holds all controllers of the project. All requestes by the client are captured here. This is also the entry point of the spring application.
    - domain module : It holds the business logic of the application.
    - persistence module : It holds logic for persisting entities. It persists entities to a SQL database and also to external accounting applications. Currenty only support for *Intuit Quickbooks* and *Microsoft Dynamics* is added.
    
The persistence module is an independent module and has no dependency on the other modules. However, domain module depends on persistence and web depends on both domain and persistence. This dependency is reflected in the respective pom files.
 
