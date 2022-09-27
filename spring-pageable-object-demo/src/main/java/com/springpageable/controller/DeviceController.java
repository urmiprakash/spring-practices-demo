package com.springpageable.controller;

import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.service.FutureDeviceService;
import com.springpageable.swagger.SwaggerErrorResponses;
import com.springpageable.swagger.SwaggerPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.springpageable.configuration.WebPath.API_VERSION_1;
import static com.springpageable.configuration.WebPath.PATH_FUTURE_DEVICES;

@RestController
@RequestMapping(API_VERSION_1)
@Tag(name = "Device future operations", description = "Operations related to device future")
public class DeviceController {

  private final FutureDeviceService futureDeviceService;

  @Autowired
  public DeviceController(FutureDeviceService futureDeviceService) {
    this.futureDeviceService = futureDeviceService;
  }

  @GetMapping(PATH_FUTURE_DEVICES)
  @Operation(summary = "Retrieves all future devices")
  @SwaggerPageable
  @SwaggerErrorResponses
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              array =
                  @ArraySchema(
                      schema = @Schema(implementation = GetFutureDeviceResponseDTO.class))))
  @Parameter(
      name = "searchParameter",
      example = "gts",
      description = "Search by serial number, product id or customer name")
  public Page<GetFutureDeviceResponseDTO> retrieveFutureDevices(
      @PageableDefault(direction = Sort.Direction.ASC, sort = "customer.name") Pageable p,
      @RequestParam(name = "searchParameter", required = false, defaultValue = "")
          String searchParameter) {
    return futureDeviceService.retrieveFutureDevices(p, searchParameter);
  }
}
