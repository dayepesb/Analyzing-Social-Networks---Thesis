package co.edu.poli.MatrixAdj;

public interface Matrix {

	// getters
	public double getWidht(int idA, int idB);

	/*
	 * Retorna 1 si el grafo es dirijido Retorna 0 si el grafo es mixto Retorna
	 * -1 si el grafo no es dirijido
	 */

	public int whoIs();
	
	public void addNode(long idNode);
	public void removeNode(long idNode);
	

	// setters
	public void setWidht(int idA, int idB, double newValue);

}
