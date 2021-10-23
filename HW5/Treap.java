import java.util.Random;
import java.util.Stack;
/**
 * Treap is a binary search tree which additionally maintains heap priorities.
 * @author Chenxu Wang(10457625)
 * @version CS570_Spring_HW5_V1.0
 *  */
public class Treap<E extends Comparable<E>>{

	//Data Field
	/**The value of priority assigned at random.*/
	private Random priorityGenerator;
	/**The reference of root node. */
	private Node<E> root;
	/**Return value from the public delete method.*/
	protected boolean deleteReturn;
	
	
	/**Class to encapsulate a treap node.*/
	private static class Node<E>{
		//Data field
		/**The key for the Search.*/
		public E data;
		/**Random heap priority.*/
		public int priority;
		/**The reference of left node.*/
		public Node<E> left;
		/**The reference of right node.*/
		public Node<E> right;
		
		//Constructor of class Node
		/**Construct a new node with the given data and priority.
		 * The pointers to child nodes are null.
		 * @param data The data to store in this node
		 * @param priority The priority to store in this node
		 * @exception IllegalArgumentException when data is null */
		public Node(E data, int priority)
		{
			if(data==null)
			{
				throw new IllegalArgumentException("Argument data cannot be null.");
			}
			this.data=data;
			this.priority=priority;
			this.left=null;
			this.right=null;
		}
		
		//Methods
		/**Perform a right rotation
		 * @return A reference to the root of the result*/
		public Node<E> rotateRight()
		{
			Node<E> root= this.left;
			Node<E>	rightTemp= root.right;
			root.right=this;
			this.left=rightTemp;
			return root;	
		}
		
		/**Perform a left rotation
		 * @return A reference to the root of the result*/
		public Node<E> rotateLeft()
		{
			Node<E> root=this.right;
			Node<E> leftTemp=root.left;
			root.left=this;
			this.right=leftTemp;
			return root;
		}
		/**Return a string representation of the node.
		 * @return A string representation of the data fields
		 * */
		@Override
		public String toString()
		{
			return "(key="+data+", priority="+priority+")";
		}
	}
	
	
	//Constructor of class Treap
	/**Construct an empty Treap.*/
	public Treap()
	{
		this.priorityGenerator= new Random();
		this.root=null;
	}
	/**Construct an empty Treap and initialize priorityGenerator
	 * using new Random(seed).
	 * @param seed A seed for initializing priorityGenerator*/
	public Treap(long seed)
	{
		this.priorityGenerator= new Random(seed);
		this.root=null;
	}
	
	//Methods of class Treap	
	
	/**Starter method add.
	 * pre: The object to insert must implement the Comparable interface.
	 * @param key The object being inserted
	 * @return true if the object is inserted, false if the object already exists in the tree*/
	//The wrapper method of add()
	public boolean add(E key)
	{
		return add(key, priorityGenerator.nextInt());
	}
	
	/**Iterative method add.
	 * post: Find a path which the new node should be inserted 
	 * and store each node in the path from the root in a stack.
	 * @param key The data to be inserted
	 * @param priority The priority the new node should have
	 * @return true if the object is inserted, false if the object already exists in the tree*/
	public boolean add(E key , int priority )
	{
		if(root==null)
		{
			root=new Node<E>(key,priority);
			return true;
		}
		else 
		{
			Node<E> newNode= new Node<E> (key, priority);
			Node<E> localRoot= this.root;
			Stack<Node<E>> path=new Stack<Node<E>>();
			while(localRoot!=null)
			{
				//Compare the new key with the data at the local root.
				int compResult= localRoot.data.compareTo(key);
				if(compResult==0)
				{
					return false;
				}
				//If the new Node is bigger than the local root, put it in the right branch.
				else if(compResult<0)
				{
					//Go toward right and find a proper localroot which is a leaf  
					//Store each node in the path from the root
					path.push(localRoot);
					if(localRoot.right!=null)
					{
						localRoot=localRoot.right;
					}
					else
					{
						localRoot.right=newNode;
						reheap(newNode, path);
						return true;
					}
						
				}
				//If the new node is smaller than the local root, put it in the left branch.
				else
				{
					//Go toward left and find a proper localroot which is a leaf  
					//Store each node in the path from the root
					path.push(localRoot);
					if(localRoot.left!=null)
					{
						localRoot=localRoot.left;
					}
					else
					{
						localRoot.left=newNode;
						reheap(newNode, path);
						return true;
					}
				}
			}	
			return false;
		}
		
	}
	
	// 
	/**Helper method add.
	 * post:To restore the heap invariant after performing the insertion.
	 * @param newNode The node will be operated.
	 * @param path A path stored the nodes from the root to this position 
	 * */
	protected void reheap(Node<E> newNode, Stack<Node<E>> path)
	{
		while(!path.isEmpty())
		{
			Node<E> parent= path.pop();
			if(parent.priority>newNode.priority)
			{
				break;
			}
			else//Bubble up the node when the priority of parent is smaller than newNode
			{
				int flag=parent.data.compareTo(newNode.data);
				
				if(flag<0)
					newNode=parent.rotateLeft();
				else
					newNode=parent.rotateRight();
				
				//Check whether the parent is left or right child of its predecessor.
				//Make a link bewteen the predecessor and newNode.
				if(path.isEmpty())
					this.root=newNode;
				else
				{
					if(path.peek().right==parent)
						path.peek().right=newNode;
					else
						path.peek().left=newNode;
				}
			}
		}
	}
	
	/**Starter method delete
	 * post: The node is not in the treap.
	 * @param key The data to be deleted
	 * @return true if the object is deleted, false if the object doesn't exist in the tree or root is null
	 * @throws ClassCastException if key does not implement Comparable
	 * */
	public boolean delete(E key )
	{
		root=delete(root,key);
		return deleteReturn;
	}
	

	/**Recursive delete method
	 * post: The node is not int the tree;
	 * deleteReturn is equal to true when the node is deleted successfully, or false.
	 * @param localRoot The root of the current subtree
	 * @param key the data will be deleted
	 * @return The modified local root */
	private Node<E> delete(Node<E> localRoot, E key)
	{
		//The tree is empty.
		if(localRoot==null)
		{
			deleteReturn=false;//////////
			return localRoot;
		}
		//Search for node to delete.
		int compResult = key.compareTo(localRoot.data);
		
		if(compResult<0)//key is smaller than localRoot.data.
		{
			localRoot.left=delete(localRoot.left, key);
			return localRoot;
		}
		else if(compResult>0)//key is larger than localRoot.data.
		{
			localRoot.right=delete(localRoot.right,key);
			return localRoot;
		}
		
		else //key is at the local root
		{
			//If there is no left child, return right child 
			//in order to link it with upper level.
			
			if(localRoot.left==null && localRoot.right==null)
			{
				deleteReturn=true;
				return null;
			}
			else
			{				
				if(localRoot.left==null)
				{
					localRoot=localRoot.rotateLeft();
					delete(localRoot,key);
					return localRoot;
				}
						
				//If there is no right child, return left child 
				//in order to link it with upper level.
				else if(localRoot.right==null)	
				{
					localRoot=localRoot.rotateRight();
					delete(localRoot,key);
					return localRoot;
				}
				
				//If there are 2 children, bubble up the node has a higher priority
				else
				{
					if(localRoot.left.priority>localRoot.right.priority)
					{
						Node<E> temp=localRoot.rotateRight();
						delete(temp,key);
						return temp;
					}
					else
					{
						Node<E> temp=localRoot.rotateLeft();
						delete(temp,key);
						return temp;
					}
				}
			}		
		}		
	}
	
	
	
	/**Starter method find.
	 * pre: The key must implement the Comparable interface.
	 * @param key The data will be found
	 * @return true if the key is found successfully, or false
	 * */
	public boolean find(E key)
	{
		return find(root, key);
	}
	/**Recursive find method.
	 * @param localRoot The root of local subtree
	 * @param key The data will be found
	 * @return true if the key is found successfully, or false*/
	private boolean find(Node <E> localRoot , E key )
	{
		if(localRoot==null)
		{	
			return false;
		}
		else
		{
			int flag=localRoot.data.compareTo(key);
			if(flag==0)
				return true;
			else
				return find(localRoot.right, key)||find(localRoot.left,key);
//			else if(flag<0)
//			{
//				return find(localRoot.right,key);
//			}
//			else
//			{
//				return find(localRoot.left,key);
//			}
		}
		
	}
	
	

	
	/**Starter method toString
	 * post: Carry out a preorder traveral of the tree 
	 * @return A representation of the nodes as string*/
	public String toString()
	{
		StringBuilder treapString= new StringBuilder();
		toString(root, 1 , treapString);
		return treapString.toString();
	}
	
	/**Recurive method toString.
	 * post: Convert a treap to a string
	 * @param node The local root
	 * @param depth The depth
	 * @param sb The StringBuilder to save the output 
	 * */
	private void toString(Node<E> node, int depth, StringBuilder sb)
	{
		for(int i=1;i<depth;i++)
		{
			sb.append("  ");
		}
		if(node==null)
		{
			sb.append("null\n");
		}
		else
		{
			sb.append(node.toString());
			sb.append("\n");
			toString(node.left,  depth+1, sb);
			toString(node.right, depth+1, sb);
		}
	}
	
	/**Main function*/
	public static void main(String[] args) {
		Treap<Integer> testTree=new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		
		System.out.println("Print the whole Treap:\n"+testTree.toString());
		
		
		System.out.println("Delete one node:"+testTree.delete(5));
		
		System.out.println(testTree.toString());
		
		System.out.println("Find a key:"+testTree.find(7));
		
		

	}
}
