package en.ratings.own.my.controller;

import en.ratings.own.my.dto.StartPageDTO;
import en.ratings.own.my.service.StartPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.PermissionConstants.HAS_ROLE_USER_PERMISSION;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_START_PAGE;
import static en.ratings.own.my.utility.ResponseEntityUtility.createOkResponseEntity;

@RestController
public class StartPageController {

    private final StartPageService startPageService;

    @Autowired
    public StartPageController(StartPageService startPageService) {
        this.startPageService = startPageService;
    }

    @PreAuthorize(HAS_ROLE_USER_PERMISSION)
    @GetMapping(value={ROUTING_START_PAGE})
    public ResponseEntity<StartPageDTO> index() throws Exception {
        return createOkResponseEntity(startPageService.index());
    }

}
