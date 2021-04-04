package br.com.luizalabs.test.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Product {
    private UUID id;
    private Float price;
    private String image;
    private String brand;
    private String title;
}
