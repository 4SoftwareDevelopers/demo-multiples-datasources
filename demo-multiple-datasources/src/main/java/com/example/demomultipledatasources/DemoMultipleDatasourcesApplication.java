package com.example.demomultipledatasources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demomultipledatasources.comercio.model.Producto;
import com.example.demomultipledatasources.comercio.repo.ProductoRepository;
import com.example.demomultipledatasources.persona.model.Persona;
import com.example.demomultipledatasources.persona.repo.PersonaRepository;

@SpringBootApplication
public class DemoMultipleDatasourcesApplication implements CommandLineRunner{
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoMultipleDatasourcesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Persona> personas = new ArrayList<>();
		List<Producto> productos = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Persona persona = new Persona();
			persona.setNombre("Jordy "+i);
			persona.setApellido("Rodriguez "+i);
			persona.setDireccion("Cra 1000");
			persona.setTelefono("423123131232");
			
			personas.add(persona);
			
			Producto producto = new Producto();
			producto.setNombre("Zapatos");
			producto.setPrecio(String.valueOf(i));
			productos.add(producto);
		}
		
		System.out.println("INSERTANDO PERSONAS....");
		
		personaRepository.saveAll(personas);
		
		personaRepository.findAll().forEach(System.out::println);
		
		System.out.println("INSERTANDO PRODUCTOS....");
		
		productoRepository.saveAll(productos);
		
		productoRepository.findAll().forEach(System.out::println);
		
		
	}

}
