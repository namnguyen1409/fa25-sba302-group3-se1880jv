package sba.group3.backendmvc.controller.staff;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/staff")
@Tag(name = "Staff Management", description = "APIs for managing staff by admin")
public class StaffController {
}
