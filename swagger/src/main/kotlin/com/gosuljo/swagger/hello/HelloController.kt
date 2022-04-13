package com.gosuljo.swagger.hello

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(description = "REST API for hello world text")
@RestController
@RequestMapping("/")
class HelloController {

    @ApiOperation(
        value = "\${api.product-composite.get-composite-product.description}",
        notes = "\${api.product-composite.get-composite-product.notes}"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
            ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fails. See response message for more information.")
        ]
    )
    @GetMapping("/hello")
    fun getHelloWorld(): ResponseEntity<String> {
        return ResponseEntity.ok("HelloWorld!")
    }
}