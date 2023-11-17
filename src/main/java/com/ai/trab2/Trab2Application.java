package com.ai.trab2;

import com.ai.trab2.entities.Table;
import com.ai.trab2.services.BreadthFirst;
import com.ai.trab2.services.DepthFirst;
import com.ai.trab2.utils.ArrayTransformations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Trab2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Trab2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		int[][] teste = ArrayTransformations.arrayToMatrix(new int[]{1,2,0,4,5,6,7,8,3});
		int[][] finalArray = new int[][]{{1,2,3},{4,5,6},{7,8,0}};

		BreadthFirst breadthFirst = new BreadthFirst();
		breadthFirst.solveBreadthFirst(teste, finalArray, 3,0);
	}
}
