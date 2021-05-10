package com.crypto.ficc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SwaggerRedirect {

    @GetMapping(value ="/")
    public String home(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location","/swagger-ui.html");
        response.sendRedirect("/swagger-ui.html");
        return "redirect to swagger ui";
    }
}
