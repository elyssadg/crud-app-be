package com.project.spring.boot.web;

import com.project.spring.boot.command.impl.executor.CommandExecutor;
import com.project.spring.boot.command.model.request.product.*;
import com.project.spring.boot.command.product.*;
import com.project.spring.boot.web.model.request.product.CreateProductWebRequest;
import com.project.spring.boot.web.model.request.product.UpdateProductWebRequest;
import com.project.spring.boot.web.model.response.base.BaseResponse;
import com.project.spring.boot.web.model.response.product.AllProductWebResponse;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/backend/products")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ProductController {

    private final CommandExecutor commandExecutor;

    @GetMapping()
    @Cacheable(value = "product", key = "'products'")
    public Mono<BaseResponse<AllProductWebResponse>> getAll() {
        return commandExecutor.execute(GetAllProductCommand.class,
                GetAllProductCommandRequest.builder().build())
                .map(BaseResponse::ok);
    }

    @GetMapping("/{productId}")
    @Cacheable(value = "product", key = "#id")
    public Mono<BaseResponse<ProductWebResponse>> getById(@PathVariable(value = "productId", required = true) String id) {
        return commandExecutor.execute(GetProductByIdCommand.class,
                        GetProductByIdCommandRequest.builder()
                                                    .id(id)
                                                    .build())
                .map(BaseResponse::ok);
    }

    @PostMapping()
    @CacheEvict(cacheNames = "product", key = "'products'")
    public Mono<BaseResponse<ProductWebResponse>> createProduct(@Valid @RequestBody CreateProductWebRequest request) {
        return commandExecutor.execute(CreateProductCommand.class,
                CreateProductCommandRequest.builder()
                                            .name(request.getName())
                                            .stock(request.getStock())
                                            .build())
                .map(BaseResponse::ok);
    }

    @DeleteMapping("/{productId}")
    @CacheEvict(cacheNames = "product", key = "'products'")
    public Mono<BaseResponse<Void>> deleteProduct(@PathVariable(value = "productId", required = true) String productId) {
        return commandExecutor.execute(DeleteProductCommand.class,
                DeleteProductCommandRequest.builder()
                                            .id(productId)
                                            .build())
                .map(BaseResponse::ok);
    }

    @PutMapping("/{productId}")
    @CacheEvict(cacheNames = "product", key = "'products'")
    public Mono<BaseResponse<ProductWebResponse>> updateProduct(@PathVariable(value = "productId", required = true) String productId,
                                                                @Valid @RequestBody UpdateProductWebRequest request) {
        return commandExecutor.execute(UpdateProductCommand.class,
                UpdateProductCommandRequest.builder()
                        .id(productId)
                        .newName(request.getNewName())
                        .newStock(request.getNewStock())
                        .build())
                .map(BaseResponse::ok);
    }

}
