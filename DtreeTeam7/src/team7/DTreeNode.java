/*
 * Task 11: Decision Tree. (Team 7)
 */
package team7;
import weka.core.Instance;
import weka.core.Instances;

public class DTreeNode {

	/*
	 * stores the nodes of the children for this node 
	 */
	private DTreeNode[] childnodes;
	/*
	 * stores whether the node is a leaf node or not
	 */
	private boolean isLeaf = false;
	/*
	 * stores the number of instances for each output class in the training data
	 */
	private int[] outputLabels; 
	/*
	 * this variable stores the decision class in case this node is a root node
	 */
	private int decisionClass;
	DTreeNode () {
		//Empty Constructor
	}
	
	/*
	 * recursive function to build the DTree starting from this node
	 * @param the training data required to build the tree
	 */
	public void buildTree (Instances traindata) {
		//count the number of instances for each output class in the training data
		outputLabels = new int[traindata.classAttribute().numValues()];
		//iterate over all the rows in the training data and store
		//the number of each output labels.
		for (int i=0; i<traindata.numInstances(); i++) {
			outputLabels[(int)traindata.instance(i).classValue()] += 1;
		}
		// conditions to terminate further propagation for a node
		// 1: less than 5 rows left to train on. Too less.
		// 2: all the training data left has the same output class for all rows.
		if (isAllSame(traindata.numInstances()) || traindata.numInstances()<5) {
			isLeaf = true;
			int maxclass = 0;
			//in this case let decide the decision class for this leaf node based on the majority of the output labels in trainig data
			for (int i=0; i<outputLabels.length; i++) {
				if (outputLabels[i]>outputLabels[maxclass]) {
					maxclass = i;
				}
			}
			decisionClass =  maxclass;
			return;
		}
		
		/** Starting information gain calculation for each attribute */
		double maxInformtionGain = Double.MIN_VALUE;
		
		// Iterate over all the classes in the training data and choose
		// the one with the max information gain
		for (int i=0; i<traindata.numAttributes()-1; i++) {
			
		}
		
	}
	
	/*
	 *method to determine whether the subset of training data contains
	 *intances of only one class
	 *@param totalrows number of instances or rows in data
	 */
	private boolean isAllSame (int totalrows) {
		for (int i=0; i<outputLabels.length; i++) {
			 if (totalrows == outputLabels[i]) {
				 return true;
			 }
		}
		return false;
	}
}
