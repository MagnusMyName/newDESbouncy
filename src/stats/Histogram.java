package stats;

public class Histogram {
	
	// List for avalanche effect values
	private int[] histogram;
	private int muestras;
	private double media;
	private double desviacion;


	public Histogram(int n, int muestras){
		histogram = new int[n];
		this.muestras = muestras;
	}

	public int[] getHistogram() {
		return histogram;
	}
	

	// Add avalanche effect value
	public void addData(int data){
		histogram[data]++;
	}
	

	// Displays the avalanche effect values ​​on the screen
	public void print() {
		for (int i = 0; i < histogram.length; i++){
			System.out.println(histogram[i]);
		}
//		media();
//		System.out.println("Media: " + getMedia());
//		desviacion();
//		System.out.println("Desviacion: " + getDesviacion());
	}

//	public void media(){
//		double res = 0;
//		for (int i = 0; i < histogram.length; i++){
//			double suma = i * histogram[i];
//			res = res + suma;
//		}
//		media = res / muestras;
//	}
//
//	public void desviacion(){
//		double res;
//		double suma = 0;
//		for (int i = 0; i < histogram.length; i++){
//			suma = suma + (Math.pow(i - media, 2) * histogram[i]);
//		}
//		res = Math.sqrt(suma / (muestras - 1));
//		desviacion = res;
//	}
//
//	public double getMedia() {
//		return media;
//	}
//
//	public double getDesviacion() {
//		return desviacion;
//	}
}
