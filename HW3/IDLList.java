//Student Name:Chenxu Wang
//CWID: 10457625
//CS 570: Homework Assignment 3 IDLList
import java.util.ArrayList;

public class IDLList<E>{
	
	/**The reference to the list head*/
	private Node<E> head;
	/**The reference to the list tail*/
	private Node<E> tail;
	/**The number of items in the list*/
	private int size;
	/** The index in the list*/
	private ArrayList<Node<E>> indices;

	
	/**The Node is the building block for a Indexed Double‐Linked List or IDLList.*/
	private static class Node<E>{
		/**The data value. */
		E data;
		/**The link to the next node. */
		Node<E> next=null;
		/**The link to the previous node. */
		Node<E> prev=null;
		
		/** Construct a node with the given data value.
		@param elem The data value */
		private Node(E elem) 
		{
			this.data=elem;
		}
		
		
		/**Constructs a node with the give data value and the references of
		 the previous node and the next node.
		@param elem The data value
		@param prev The previous node
		@param next The next node*/
		private Node(E elem, Node<E> prev, Node<E> next)
		{
			this.data=elem;
			this.prev=prev;
			this.next=next;
		}
	}
	
	/**Creates an empty  IDLList.*/
	public IDLList()
	{
		head=null;
		tail=null;
		size=0;
		indices=new ArrayList<Node <E>>();
	}
	
	/**Adds elem at position index(counting from
	wherever head is). It uses the index for fast access.
	@param index The position that elem will be added to.
	@param elem The data value to be added.*/
	public boolean add(int index, E elem)
	{
		//Make sure the index is not out of bounds.
		if(index<0||index>=size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		//When it will add an element at the head, then call method:add(elem)
		if(index==0)
		{
			add(elem);
			return true;
		}
		else
		{
			//Find the position before the new element will be added.
			Node<E> formerNode=head;
			for(int i=0;i<index-1;i++)
			{
				formerNode=formerNode.next;
			}
			//Add the new node at the index position
			formerNode.next=new Node<E>(elem,formerNode,formerNode.next);
			formerNode.next.prev=formerNode.next;
			//Updata the indices
			indices.add(index,formerNode.next);
			size++;
			return true;
		}
		
		
	}
	/**Adds elem at the head (i.e. it becomes the first element
	of the list).
	@param elem The data value to be added.*/
	public boolean add(E elem)
	{
		if(head!=null)
		{
			head= new Node<E>(elem, null, head);
			head.next.prev=head;
			size++;
			indices.add(0, head);
			return true;
		}
		else//When the IDLList is empty, updates both head and tail.
		{
			head=new Node<E>(elem);
			tail=head;
			size++;
			indices.add(0,head);
			return true;
		}
	}
	
	
	/**Adds elem as the new last element of the list (i.e. at
	the tail).
	@param elem The data value to be append*/
	public boolean append(E elem)
	{
		//IDLList is empty.
		if(tail==null)
		{
			head=new Node<E>(elem);
			tail=head;
			size++;
			indices.add(0,head);
			return true;
		}
		else//Appends a new node at the tail.
		{
			tail=new Node<E>(elem,tail,null);
			tail.prev.next=tail;
			size++;
			indices.add(size-1,tail);
		}
		return true;
	}
	
	
	/**Returns the object at position index from the head. It uses
	the index for fast access. Indexing starts from 0, thus get(0) returns the head element
	of the list.
	@param index The position that the data will be returned */
	public E get(int index)
	{
		if(index<0||index>=size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		return indices.get(index).data;
	}
	
		
	/**Returns the object at the head.*/
	public E getHead()
	{
		return head.data;
	}
	
	/**Returns the object at the tail.*/
	public E getLast()
	{
		return tail.data;
	}
	
	/**Returns the list size.*/
	public int size()
	{
		return size;
	}
	
	/**Removes and returns the element at the head.*/
	public E remove()
	{
		Node<E> temp=head;
		if(head!=null)
		{
			head=head.next;
			head.prev=null;
		}
		// Return data at old head or null if IDLList is empty.
		if(temp!=null)
		{
			indices.remove(0);
			size--;	
			return temp.data;
		}
		else
			return null;
	}
	
	/**Removes and returns the element at the tail.*/
	public E removeLast()
	{
		Node<E> temp=tail;
		if(tail!=null)
		{
			tail=tail.prev;
			tail.next=null;
		}
		// Return data at old tail or null if IDLList is empty.
		if(temp!=null)
		{
			indices.remove(size-1);
			size--;
			return temp.data;
		}
		return null;
	}
	
	
	/**Removes and returns the element at the index.
	Use the index for fast access.
	@param index The position that will be removed*/
	public E removeAt(int index)
	{
		if(index<0||index>=size)
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index==0) 
		{	
			return remove();
		}
		if(index==size-1)
		{
			return removeLast();
		}
		Node<E> temp=head;
		if(head!=null)
		{
			for(int i=0;i<index;i++)
			{
				temp=temp.next;
			}
			temp.prev.next=temp.next;
			temp.next.prev=temp.prev;
			indices.remove(index);
			size--;
			return temp.data;
		}
		else
		{
			return null;
		}
		
	}
	
	/**removes the first occurrence of elem in the list and
	returns true. Return false if elem was not in the list.
	@param elem The data value to be removed*/
	public boolean remove(E elem)
	{
		for(int i=0;i<size;i++)
		{
			if(elem==indices.get(i).data)
			{
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**Presents a string representation of the list.
	 * There are two approaches of output 
	 * including using the relationship between nodes and fast accessing from indices*/
	public String toString()
	{
		/*The approach of using relationshop between nodes*/
//		System.out.println("Output by the list ：");
//		Node<E> node=head;
//		StringBuilder result=new StringBuilder();		
//		while(node!=null)
//		{
//			result.append(node.data);
//			node=node.next;
//		}
//		System.out.println(result.toString());
//		return result.toString(); 

		/*The approach of using fast accessing*/		
		System.out.println("Output by the indices：");
		StringBuilder result=new StringBuilder();
		for(int i=0;i<size;i++)
		{
			result.append(indices.get(i).data);
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
}
