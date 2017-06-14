package ls.controller;

import ls.entity.output.LoginResult;
import ls.repository.ChatRoomRepository;
import ls.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

/**
 * Created by leishu on 17-6-5.
 */
@Controller
public class ChatController {
    @Autowired
    private ChatService service;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/chat")
    public String home(Model model) {
        model.addAttribute("title", "Chat Room");
        model.addAttribute("rooms", chatRoomRepository.findAll());
        return "chat";
    }

    @PostMapping("/enterRoom")
    public String enterRoom(String user, String room, final Model model) {
        Mono<LoginResult> lr = service.login(room, user);
        model.addAttribute("lr", lr);

        return "enterRoom";
    }
}
