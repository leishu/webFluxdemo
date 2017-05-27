package ls.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by leishu on 17-5-27.
 */
@Controller
public class HomeController {

    private DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello World");
        model.addAttribute("title", "Leishu Home");
        model.addAttribute("date", new Date());
        return "home";
    }

    @GetMapping(value = "/now")
    @ResponseBody
    public Flux<String> now() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(l -> LocalDateTime.now().format(f));
    }

    @GetMapping(value = "/now5")
    @ResponseBody
    public Flux<ServerSentEvent<String>> now5() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(l -> ServerSentEvent
                        .builder(LocalDateTime.now().format(f)).build());
    }
}
