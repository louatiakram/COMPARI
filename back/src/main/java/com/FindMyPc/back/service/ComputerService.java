package tech.louatiakram.scrapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.louatiakram.scrapping.entities.Computer;
import tech.louatiakram.scrapping.repository.ComputerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAllProducts() {
        return computerRepository.findAll();
    }

    public Optional<Computer> getProductById(Long id) {
        return computerRepository.findById(id);
    }

    public Computer saveProduct(Computer computer) {
        List<Computer> existingComputers = computerRepository.findByNameAndPrice(computer.getName(), computer.getPrice());
        if (existingComputers.isEmpty()) {
            computerRepository.save(computer);
            System.out.println("Saving new computer: " + computer.getName() + " with price: " + computer.getPrice());
        } else {
            System.out.println("Computer already exists: " + computer.getName() + " with price: " + computer.getPrice());
            Computer existingComputer = existingComputers.get(0);
            existingComputer.setProcessor(computer.getProcessor());
            existingComputer.setProcessorRef(computer.getProcessorRef());
            existingComputer.setMemory(computer.getMemory());
            existingComputer.setHardDrive(computer.getHardDrive());
            existingComputer.setGpu(computer.getGpu());
            existingComputer.setGpuRef(computer.getGpuRef());
            existingComputer.setScreenSize(computer.getScreenSize());
            existingComputer.setScreenType(computer.getScreenType());
            existingComputer.setTouchScreen(computer.getTouchScreen());
            existingComputer.setNetwork(computer.getNetwork());
            existingComputer.setCamera(computer.getCamera());
            existingComputer.setWarranty(computer.getWarranty());
            existingComputer.setRefreshRate(computer.getRefreshRate());
            existingComputer.setColor(computer.getColor());
            computerRepository.save(existingComputer);
            System.out.println("Updated existing computer: " + existingComputer.getName() + " with price: " + existingComputer.getPrice());
        }
        return computer;
    }

    public boolean deleteProduct(Long id) {
        if (computerRepository.existsById(id)) {
            computerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Computer getProductByNameAndPrice(String name, Double price) {
        return computerRepository.findByNameAndPrice(name, price).stream().findFirst().orElse(null);
    }
}
