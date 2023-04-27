package ru.mcclinics.orderservice.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Configuration
public class SwaggerConfiguration implements OpenApiCustomizer {

  public static final String UNSECURED = "unsecured";

  private static final List<Function<PathItem, Operation>> OPERATION_GETTERS =
      Arrays.asList(
          PathItem::getGet,
          PathItem::getPost,
          PathItem::getDelete,
          PathItem::getHead,
          PathItem::getOptions,
          PathItem::getPatch,
          PathItem::getPut);

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "bearerAuth",
                    new SecurityScheme()
                        .type(HTTP)
                        .scheme("bearer")
                        .bearerFormat("jwt")
                        .in(HEADER)
                        .name("Authorization")))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .info(new Info().title("ORDER SERVICE API").version("1.0.0"));
  }

  @Override
  public void customise(OpenAPI openApi) {
    openApi
        .getPaths()
        .forEach(
            (path, item) ->
                getOperations(item)
                    .forEach(
                        operation -> {
                          List<String> tags = operation.getTags();
                          if (tags != null && tags.contains(UNSECURED)) {
                            operation.setSecurity(Collections.emptyList());
                            operation.setTags(filterTags(tags));
                          }
                        }));
  }

  private static Stream<Operation> getOperations(PathItem pathItem) {
    return OPERATION_GETTERS.stream()
        .map(getter -> getter.apply(pathItem))
        .filter(Objects::nonNull);
  }

  private static List<String> filterTags(List<String> tags) {
    return tags.stream().filter(t -> !t.equals(UNSECURED)).collect(Collectors.toList());
  }
}
