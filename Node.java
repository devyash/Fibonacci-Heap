class node{

		private node right;
		private node left;
		private node parent;
		private node child;
		private int value;

	node(int value){

		this.value=value;
	}

	//Getter & Setters
	public node getRight(){
		return right;

	}
	public void setRight(node right){
		this.right=right;
	}
	public node getLeft(){
		return left;
	}
	public void setLeft(node left){
		this.left=left;
	}
	public node getParent(){
		return parent;
	}
	public void setParent(node parent){
		this.parent=parent;
	
	}
	public node getChild(){
		return child;

	}
	public void setChild(node child){
		this.child=child;
	
	}
	public int getValue(){
		return value;

	}
	public void setValue(int value){
		this.value=value;
	}
}