package com.gm.EcommerceBackend.payloads;

public record ProductDTO(String name, String description, double price, int stock_quantity, String image_url, int category_id) {
}
