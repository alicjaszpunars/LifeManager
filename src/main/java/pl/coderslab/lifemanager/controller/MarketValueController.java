package pl.coderslab.lifemanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.lifemanager.service.MarketValueService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market-values")
public class MarketValueController {

    private final MarketValueService marketValueService;

    @PostMapping("/update")

    public ResponseEntity<String> updateValues() {
        try {
            marketValueService.updateValuesManually();
            return ResponseEntity.ok("values updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error, no updates");
        }
    }
}
