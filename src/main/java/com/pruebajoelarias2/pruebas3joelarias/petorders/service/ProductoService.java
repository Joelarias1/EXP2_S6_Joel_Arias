package com.pruebajoelarias2.pruebas3joelarias.petorders.service;

import java.util.List;
import java.util.Optional;

import com.pruebajoelarias2.pruebas3joelarias.petorders.dto.ProductoDTO;

public interface ProductoService {
    List<ProductoDTO> getAllProductos();
    Optional<ProductoDTO> getProductoById(Long id);
    ProductoDTO saveProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);
    void deleteProducto(Long id);
}
