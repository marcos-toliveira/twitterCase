package com.marcoscdo.case.twitter;

import io.micronaut.http.annotation.*;

@Controller("/twitterCase")
public class TwitterCaseController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}