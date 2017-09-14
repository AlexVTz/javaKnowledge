package removeKFromList;

class ListNode<T> {
	T value;
    ListNode<T> next;

    ListNode(T x) {
      value = x;
    }
  
}

class LinkedList<T>{
	
	ListNode<T> head;
	
	LinkedList(){
		head = null;
	}
	
	public void add(ListNode<T> newNode){
		
		ListNode<T> aux = head;
		
		if(head == null){
			head = newNode;
			head.next = null;
		}
		else{
			while(aux != null){
				if(aux.next == null){
					aux.next = newNode;
					break;
				}
				aux = aux.next;
			}
		}
		
	}
	
	public void printList(){
		ListNode<T> aux = head;
		
		while(aux != null){
			System.out.println(aux.value);
			aux = aux.next;
		}

	}
	
}


class remove<T> {

	ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
		ListNode<Integer> temp = l;
		ListNode<Integer> new1 = null;
		if (l == null) {
			return l;
		}
		while (temp != null) {
			int i = 0;
			System.out.println(i + 1);
			if (temp.value == k) {
				System.out.println(i + 1);
				new1 = temp.next;
				temp.value = new1.value;
				temp.next = new1.next;
			} else {
				temp = temp.next;
			}

		}
		return l;
	}
}

public class Main {
	
	public static ListNode<Integer> removeKFromList(ListNode<Integer> l, int k){
		
		if(l == null)
			return null;
		
		l.next = removeKFromList(l.next, k);
		
		if(l.value == k){
			return l.next;
		}
		
		return l;
	}
	
	public static ListNode<Integer> removeKFromList2(ListNode<Integer> l , int k){
		
		//Initialize a helper node to null
		ListNode<Integer> helper = new ListNode<>(null);
	    helper.next = l;
	    
	    //Declare a node p to go over the list
	    ListNode<Integer> p = helper;
	 
	    while(p.next != null){
	    	//This compares if the node's value is equal to k value
	    	//it compares on the next node
	        if(p.next.value == k){
	        	
	            ListNode<Integer> next = p.next;
	            p.next = next.next; 
	        }else{
	            p = p.next;
	        }
	    }
	 
	    return helper.next;
	}

	public static void main(String[] args) {
		
//		ListNode<Integer> lista, lista2;
//		lista = new ListNode<>(3);
//		lista2 = new ListNode<>(1);
//		lista.next = lista2; 
		
//		lista = new ListNode<Integer>(1);
//		lista = new ListNode<Integer>(2);
//		lista = new ListNode<Integer>(3);
//		lista = new ListNode<Integer>(4);
//		lista = new ListNode<Integer>(5);
		
		//System.out.println(lista.value);
		
//		LinkedList<String> list = new LinkedList<>();
//		
//		list.add(new ListNode<String>("Hola"));
//		list.add(new ListNode<String>(" "));
//		list.add(new ListNode<String>("Mundo"));
//		list.printList();
		
		
//		HashSet<ListNode> list = new HashSet<>();
//		
//		list.add(new ListNode<Integer>(2));
		
		ListNode<Integer> list = new ListNode<>(3);
		ListNode<Integer> list2 = new ListNode<>(1);
		ListNode<Integer> list3 = new ListNode<>(2);
		ListNode<Integer> list4 = new ListNode<>(3);
		//ListNode<Integer> list5 = new ListNode<>(4);
		//ListNode<Integer> list6 = new ListNode<>(5);
		
		list.next = list2;
		list2.next = list3;
		list3.next = list4;
		list4.next = null;
		//list5.next = list6;
		//list6.next = null;
		
		System.out.println(removeKFromList2(list, 2).next.next.value);
		
//		int k = 3;
//
//		ListNode<Integer> newList = new ListNode<>(null);
//		
//		while(list != null){
//			if(list.value != k){
//				newList = 
//				
//			}
//			
//		}
		
//		change = list.next;
//		if(change != null){
//			list = change;
//		}
		
		//System.out.println(newList.value);

	}

}
