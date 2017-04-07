/*
 * Task 11: Decision Tree. (Team 7)
 */
package team7;
import weka.core.Instances;
import weka.core.Instance;

public class DataPartition {
	/*
	 * the traindata to partition on
	 */
	private Instances traindata;
	/*
	 * index of the class to split on
	 */
	private int classIndex;
	/*
	 * the information gain value
	 */
	private double infoGain;
	/*
	 * stores the number of instances for each output class in the training data
	 */
	private int[] outputLabels;
	/**
	 * in case the the class has continuous value store a pivot point for binary classification
	 */
	private double pivotpoint;
	/**
	 * index of the attribue to partition on
	 */
	private int partitionIndex;
	/**
	 * number of labels for the attribute to be partitioned on
	 */
	private int numLabels;
	
	
	public DataPartition (Instances data, int index) {
		
		traindata = data;
		classIndex = index;
		
		//get the coutNum
		outputLabels = new int[traindata.classAttribute().numValues()];
		// iterate over all the rows for the data
		for(int i=0;i<traindata.numInstances();i++){
			outputLabels[(int)traindata.instance(i).classValue()]++;
		}
				
		// now chekc if the attribute to be partitioned on has finite labels or has continuous
		// move forward accordingly
		
		
		// if attribute has finite labels
		if(traindata.attribute(partitionIndex).isNominal()){
			numLabels = traindata.attribute(partitionIndex).numValues();
			infoGain = informationGainNom();  //get the information gain
			testValidation();			
		}
		// if the attribute has continuous values
		else if(traindata.attribute(index).isNumeric()){ 
			// creating two labels for continuous attributes by default
			numLabels = 2; 
			double min = Double.MAX_VALUE;
			double max = Double.MIN_NORMAL;
			
			// steps for finding the appropriate pivot point
			for (int i = 0; i < data.size(); i++) {		
				double n = traindata.instance(i).value(partitionIndex);
				if (n < min){
					min = n;
				}
				if (n > max) { 
					max = n;
				}
			}
					
			double range = max - min;
			// taking 10 steps over the range of values in
			// order to find the best pivot point
			double steps = 10;
					
			infoGain = informationGainNum(steps,range,min,max);					
			testValidation();
		}
	}
			
	public double informationGainNum(double steps,double range,double min,double max){
		double total_entropy = 0;
		for(int i=0;i<outputLabels.length;i++){
			if(outputLabels[i]>0){
				double r = (double)outputLabels[i]/traindata.numInstances();
				total_entropy -= r*(Math.log(r)/Math.log(2));
			}
		}
		
		double p = min;
		double infogainMax = Double.MIN_VALUE;
		
		// The best pivot point will be the point which reduces the total information gain to a minimum
		// This also means that at this point the partition class has maximum information gain
		// Thus it reduces the overall info gain for the entire data to a minimum
		for(int i=0;i<steps-1;i++){
			double pivot = min + (range/steps)*(i+1);
			double ifgain = infoGainInterval(pivot);
			if(ifgain>infogainMax){
				infogainMax = ifgain;
				p = pivot;
			}
							
		}
		this.pivotpoint = p;
				
				
		return total_entropy-infogainMax;
	}
			
	//this function returns the entropy for a particular pivot point
	public double infoGainInterval(double pivot){
		double EntropyAttr = 0;
		double [][] temp = new double[2][traindata.classAttribute().numValues()];
		double [] cout = new double[2];
		for(int i=0;i<traindata.numInstances();i++){
			double val = traindata.instance(i).value(partitionIndex);
					
			if(val<pivot){
				cout[0]++;
			}else{
				cout[1]++;
			}
					
		}
				
		return EntropyAttr;
	}
			
			
	public double informationGainNom(){
				
		double total_entropy = 0;
		for(int i=0;i<outputLabels.length;i++){
			if(outputLabels[i]>0){
				double r = (double)outputLabels[i]/traindata.numInstances();
				total_entropy -= r*(Math.log(r)/Math.log(2));
			}
		}
				
		/*		
		double EntropyAttr = 0;
		double [][] temp = new double[traindata.attribute(partitionIndex).numValues()][traindata.classAttribute().numValues()];
		double [] cout = new double[traindata.attribute(partitionIndex).numValues()];
			*/
		
		//TO BE COMPLETED
		return 0;
				
	}
	
	
	// FUNCTION TO CHECK THIS ATTRIBUTE AGAINST THE BASE CONDITIONS TO SEE IF THE NODE IS VALID OR NOT
	public void testValidation() {
		//TO BE COMPLETED
	}
	
	//TO BE COMPLETED
	
}
