package com.example.inventory_service.controller;

import com.example.inventory_service.excepiton.ProductNotFoundException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice //This annotation indicates springboot that this class handle all the
// exceptions that arrives from close methods or arrives from all the methods call by any controller
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleProductNotFoundException(ProductNotFoundException ex){
        return GraphQLError.newError()
                .message(ex.getMessage())
                .build();
    }
}
