package ie.gmit.sw.ai.traverse;

import java.io.IOException;
import java.util.Scanner;

import ie.gmit.sw.ai.Maze.Maze_Generate;

public class traverse_DFS {

	static Maze_Generate mg=new Maze_Generate();
	private static int row=mg.getROWMAZE();
	private static int col=mg.getROWMAZE();

	
	public static  boolean traverse(int i1, int j1, int i2, int j2) {
		boolean result = false;

		if (i1 == 1 && j1 == 0)// set the start point 
			mg.node_set[i1][j1]=1;

		if (valid(i1, j1, i2, j2)) {
			mg.node_set[i2][j2]=-1;// ���趨ΪnotMain��ʾ������·����

			if (i2 == 2 * row - 1 && j2 == 2 * col)
				result = true; // �����յ�
			else {
				result = traverse(i2, j2, i2 + 1, j2); // down
				if (!result)
					result = traverse(i2, j2, i2, j2 + 1); // right
				if (!result)
					result = traverse(i2, j2, i2 - 1, j2); // up
				if (!result)
					result = traverse(i2, j2, i2, j2 - 1); // left
			}

			if (result) // ��doneΪtrue˵�������������·����
				mg.node_set[i2][j2]=1;
		} else {
		
		}

		return result;
	}

	private static boolean valid(int i1, int j1, int i2, int j2) {
		boolean result = false;

		if (i2 >= 0 && i2 < 2 * row + 1 && j2 >= 0
				&& j2 < 2 * col + 1)
			if (Maze_Generate.node_set[i2][j2] == 0) {// ��֤û��Խ�����Ŀ�귽��δ̤���
				if (i1 == i2 - 1 && mg.newMaze[i1][j1] == mg.newMaze[i2][j2]
						&& mg.newMaze[i2][j2] == 0)// ��������
					result = true;
				else if (i1 == i2 + 1 && mg.newMaze[i1][j1] == mg.newMaze[i2][j2]
						&& mg.newMaze[i2][j2] == 0)// ��������
					result = true;
				else if (j1 == j2 - 1 && mg.newMaze[i1][j1] == mg.newMaze[i2][j2]
						&& mg.newMaze[i2][j2] == 0)// ��������
					result = true;
				else if (j1 == j2 + 1 && mg.newMaze[i1][j1] == mg.newMaze[i2][j2]
						&& mg.newMaze[i2][j2] == 0)// ��������
					result = true;
			}
		return result;
	}
}