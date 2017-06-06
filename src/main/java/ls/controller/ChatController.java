package ls.controller;

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
/*
    @Autowired
    public ChatController(ChatService service, ChatRoomRepository chatRoomRepository) {
        this.service = service;
        this.chatRoomRepository = chatRoomRepository;
    }*/

    @GetMapping("/chat")
    public String home(Model model) {
        model.addAttribute("title", "Chat Room");
        model.addAttribute("rooms", chatRoomRepository.findAll().collectList().block());
        return "chat";
    }

    @PostMapping("/enterRoom")
    public Mono<Integer> enterRoom(String user, String room) {
        return service.enter();
    }
}
