package org.utej.k8sdemo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/home")
    public String home() {
        return "ChotuOP";
    }
    @GetMapping("/end")
    public String end() {
        return "Chotu2Op";
    }
}





