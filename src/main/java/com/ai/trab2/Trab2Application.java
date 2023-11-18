package com.ai.trab2;

import com.ai.trab2.services.BreadthFirst;
import com.ai.trab2.services.DepthFirst;
import com.ai.trab2.utils.ArrayTransformations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trab2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Trab2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		int[][] matrix1 = {
				{1, 2, 3},
				{4, 5, 6},
				{0, 7, 8}
		};

		int[][] matrix2 = {
				{1, 3, 0},
				{4, 2, 5},
				{7, 8, 6}
		};

		int[][] matrix3 = {
				{1, 3, 5},
				{2, 6, 0},
				{4, 7, 8}
		};

		int[][] matrix4 = {
				{1, 8, 3},
				{4, 2, 6},
				{7, 5, 0}
		};

		int[][] matrix5 = {
				{1, 2, 3},
				{7, 0, 6},
				{4, 8, 5}
		};




		int[][] finalMatrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};

		BreadthFirst breadthFirst = new BreadthFirst();
		DepthFirst depthFirst = new DepthFirst();
//		breadthFirst.solveBreadthFirst(matrix1, finalMatrix, 0,2);
		depthFirst.solveDepthFirst(matrix1, finalMatrix, 2, 0);
	}
}
