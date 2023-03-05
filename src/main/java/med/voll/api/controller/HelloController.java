package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String olaMundo() {
        try {
            Thread.sleep(10);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 1000; i++) {
            var calc = 100 * 2 + (Math.pow(10, 2)) * 100;
            calc = calc * calc;

        }
        return "Hello World Spring!";
    }
}
