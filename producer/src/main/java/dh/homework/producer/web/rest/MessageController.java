package dh.homework.producer.web.rest;

import dh.homework.producer.domain.Site;
import dh.homework.producer.service.SenderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {

    SenderService senderService;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam(required = false) String key,
                                              @RequestParam String message) {
        senderService.sendMessage(key, message);
        return ResponseEntity.ok(key + " : " + message);
    }

    @PostMapping("/send")
    public ResponseEntity<Site> sendSiteMessage(@RequestBody Site site) {
        senderService.sendSiteMessage(site);
        return ResponseEntity.ok(site);
    }
}
