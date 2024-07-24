package tech.louatiakram.scrapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String processor;
    private String processorRef;
    private String memory;
    private String hardDrive;
    private String gpu;
    private String gpuRef;
    private String screenSize;
    private String screenType;
    private String touchScreen;
    private String network;
    private String camera;
    private String warranty;
    private String refreshRate;
    private String color;
    private Double price;

    public Computer(Long id, String name, String processor, String processorRef, String memory, String hardDrive,
                    String gpu, String gpuRef, String screenSize, String screenType, String touchScreen, String network,
                    String camera, String warranty, String refreshRate, String color, Double price) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.processorRef = processorRef;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.gpu = gpu;
        this.gpuRef = gpuRef;
        this.screenSize = screenSize;
        this.screenType = screenType;
        this.touchScreen = touchScreen;
        this.network = network;
        this.camera = camera;
        this.warranty = warranty;
        this.refreshRate = refreshRate;
        this.color = color;
        this.price = price;
    }
}
