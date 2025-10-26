package sba.group3.backendmvc.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.common.AdministrativeUnitResponse;
import sba.group3.backendmvc.enums.AdministrativeUnitLevel;
import sba.group3.backendmvc.service.common.AdministrativeUnitService;

import java.util.List;

@RestController
@RequestMapping("/api/common/administrative-units")
public class AdministrativeUnitController {

    private final AdministrativeUnitService administrativeUnitService;

    public AdministrativeUnitController(AdministrativeUnitService administrativeUnitService) {
        this.administrativeUnitService = administrativeUnitService;
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse<List<AdministrativeUnitResponse>>> getAdministrativeUnits(
            @RequestParam AdministrativeUnitLevel level,
            @RequestParam(required = false) String parentCode
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<AdministrativeUnitResponse>>builder()
                        .message("Administrative units retrieved successfully")
                        .data(administrativeUnitService.getUnit(level, parentCode))
                        .build()
        );
    }
}
