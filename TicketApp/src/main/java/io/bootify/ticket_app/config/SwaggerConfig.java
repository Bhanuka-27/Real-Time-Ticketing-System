package io.bootify.ticket_app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiSpec() {
        return new OpenAPI().components(new Components()
                // Define components for reuse in API documentation
                .addSchemas("ApiErrorResponse", new ObjectSchema()  // Define a custom schema for API error response
                        .addProperty("status", new IntegerSchema()) // Status code of the response (integer)
                        .addProperty("code", new StringSchema())  // Error code (string)
                        .addProperty("message", new StringSchema()) // Error message (string)
                        .addProperty("fieldErrors", new ArraySchema().items(
                                new Schema<ArraySchema>().$ref("ApiFieldError")))) // Field-specific errors
                // Define a custom schema for individual field errors in the API response
                .addSchemas("ApiFieldError", new ObjectSchema()
                        .addProperty("code", new StringSchema()) // Field-specific error code
                        .addProperty("message", new StringSchema()) // Error message for the field
                        .addProperty("property", new StringSchema()) // Name of the field with error
                        .addProperty("rejectedValue", new ObjectSchema()) // Value that caused the error
                        .addProperty("path", new StringSchema()))); // Path to the field in the request
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        // add error type to each operation
        return (operation, handlerMethod) -> {
            operation.getResponses().addApiResponse("4xx/5xx", new ApiResponse()
                    .description("Error")
                    .content(new Content().addMediaType("*/*", new MediaType().schema(
                            new Schema<MediaType>().$ref("ApiErrorResponse")))));
            return operation;
        };
    }

}
