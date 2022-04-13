package com.gosuljo.swagger.confi

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors

import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact

import springfox.documentation.spi.DocumentationType

import springfox.documentation.spring.web.plugins.Docket




@Configuration
class SpringFoxConfig {
    @Value("\${api.common.version}")
    var apiVersion: String? = null

    @Value("\${api.common.title}")
    var apiTitle: String? = null

    @Value("\${api.common.description}")
    var apiDescription: String? = null

    @Value("\${api.common.termsOfServiceUrl}")
    var apiTermsOfServiceUrl: String? = null

    @Value("\${api.common.license}")
    var apiLicense: String? = null

    @Value("\${api.common.licenseUrl}")
    var apiLicenseUrl: String? = null

    @Value("\${api.common.contact.name}")
    var apiContactName: String? = null

    @Value("\${api.common.contact.url}")
    var apiContactUrl: String? = null

    @Value("\${api.common.contact.email}")
    var apiContactEmail: String? = null

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(
                ApiInfo(
                    apiTitle,
                    apiDescription,
                    apiVersion,
                    apiTermsOfServiceUrl,
                    Contact(apiContactName, apiContactUrl, apiContactEmail),
                    apiLicense,
                    apiLicenseUrl,
                    emptyList()
                )
            )
    }
}
