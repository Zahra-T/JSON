
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class JProject {

	public static class myList <K > implements List<K>{
		Object [] elements = new Object[2];
		int size = 0;
		@Override
		public boolean add(K arg0) {
			try {
				if(size == elements.length) {
					Object [] e = new Object[size * 2];
					for(int i = 0; i<size; i++) e[i] = elements[i];
					elements = e;
				}
				elements[size++] = arg0;
			}catch(Exception e){
				return false;
			}

			return true;
		}

		@Override
		public void add(int arg0, K arg1) {
			if(size == elements.length) {
				Object [] e = new Object[size * 2];
				for(int i = 0; i<size; i++) e[i] = elements[i];
				elements = e;
			}
			for(int i = size; i>arg0; i++) elements[i] = elements[i-1];

			elements[arg0] = arg1;
		}

		@Override
		public boolean addAll(Collection arg0) {
			Iterator itr = arg0.iterator();
			while(itr.hasNext()) {
				Object a = itr.next();

				try {
					if(size == elements.length) {
						Object [] e = new Object[size * 2];
						for(int i = 0; i<size; i++) e[i] = elements[i];
						elements = e;
					}
					elements[size++] = a; // inja bayad a mibood , zadi arg0 :))
				}catch(Exception e){
					return false;
				}


			}
			return true;
		}

		@Override
		public boolean addAll(int arg0, Collection arg1) {
			try {
				int a = arg1.size();
				if(size + a > elements.length) {
					Object [] o = new Object[size + a + 5];
					for(int i = 0; i<size; i++) {
						o[i] = elements[i];
						elements = o;
					}
				}

				for(int i = size; i>=arg0; i++) {
					elements[i+a] = elements[i];
				}

				int where = arg0;
				Iterator itr = arg1.iterator();
				while(itr.hasNext()) {
					Object o = itr.next();
					elements[where++] = o;
				}
				size += a;
			}catch(Exception e) {
				return false;
			}

			return true;
		}

		@Override
		public void clear() {
			for(int i = 0; i<size; i++) elements[i] = null;
		}

		@Override
		public boolean contains(Object arg0) {
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) return true;
			}
			return false;
		}

		@Override
		public boolean containsAll(Collection arg0) {
			Iterator itr = arg0.iterator();
			while(itr.hasNext()) {
				Object o = itr.next();
				int where = 0;
				while(!elements[where].equals(o)) {
					where++;
					if(where == size) return false;
				}
			}
			return true;
		}

		@Override
		public K get(int arg0) {
			if(arg0 < 0 && arg0 >= size) {
				throw new IndexOutOfBoundsException();
			}
			return (K)elements[arg0];

		}

		@Override
		public int indexOf(Object arg0) {
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) return i;
			}
			return -1;
		}

		@Override
		public boolean isEmpty() {
			return (size == 0);
		}

		@Override
		public Iterator iterator() {
			Object []fitSize = new Object[size];
			for (int i = 0 ; i < size ; i ++) fitSize[i] = elements[i];
			return Arrays.stream(fitSize).iterator();
		}

		@Override
		public int lastIndexOf(Object arg0) {
			for(int i = size-1; i>=0; i--) {
				if(elements[i].equals(arg0)) return i;
			}
			return -1;
		}

		@Override
		public ListIterator listIterator() {
			return null;
		}

		@Override
		public ListIterator listIterator(int arg0) {
			return null;
		}

		@Override
		public boolean remove(Object arg0) {
			int where = -1;
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) {
					where = i;
					break;
				}
			}

			if(where != -1) {
				while(where < size-1) {
					elements[where] = elements[where+1];
					where++;
				}
			}
			elements[--size] = null;

			return (where != -1);
		}

		@Override
		public K remove(int arg0) {
			Object o = elements[arg0];
			int where = arg0;
			while(where < size-1) {
				elements[where] = elements[++where];
			}
			elements[--size] = null;
			return (K)o;
		}

		@Override
		public boolean removeAll(Collection arg0) {
			for(int i = 0; i<size; i++) elements[i] = null;
			return false;
		}

		@Override
		public boolean retainAll(Collection arg0) {
			try{
				Iterator itr = arg0.iterator();
				int n = 0;
				boolean [] mark = new boolean[size];
				while(itr.hasNext()) {
					Object o = itr.next();
					for(int i = 0; i<size; i++) {
						if(elements[i].equals(o)) {
							mark[i] = true;
							n++;
						}
					}
				}

				Object [] e = new Object[n];
				int where = 0;
				for(int i = 0; i<size; i++) {
					if(mark[i]) {
						e[where++] = elements[i];
					}
				}
				size = n;
				elements = e;
			}
			catch(Exception e){
				return false;
			}

			return true;
		}

		@Override
		public K set(int arg0, K arg1) { //try catch mikhad?
			Object p = elements[arg0];
			elements[arg0] = arg1;

			return (K)p;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public List subList(int arg0, int arg1) {
			myList a = new myList();
			for(int i = arg0; i<=arg1; i++) {
				a.add(elements[i]);
			}
			return a;
		}

		@Override
		public Object[] toArray() {
			Object [] a = new Object[size];
			for(int i = 0; i<size; i++) a[i] = elements[i];
			return a;
		}

		@Override
		public Object[] toArray(Object[] arg0) {
			if(size < arg0.length) {
				Object [] a = new Object[size];
				for(int i = 0; i<size; i++) a[i] = elements[i];
				return a;
			}
			else {
				for(int i = 0; i<size; i++) {
					arg0[i] = elements[i];
				}
				for(int i = size; i<arg0.length; i++) {
					arg0[i] = null;
				}
			}


			return arg0;
		}
		
		public void sort() {
			myTree [] m = new myTree[size];
			for(int i = 0 ;i<size; i++) {
				m[i] = (myTree)elements[i];
			}
			Arrays.sort(m);
			elements = m;
		}
		
	
		
//		public void sort()
//		{
//			for(int i = 0; i<size; i++) {
//				for(int j = i+1; j<size; j++) {
//					String s = ((myTree)elements[j]).name;
//					String t = ((myTree)elements[i+1]).name;
//					if(s.compareTo(t) < 0)  replace(i , j);
//				}
//			}
//			
//			// bache hash ro bar hasb e name mikhaym sort konim
//		}
//		public void replace(int i, int j) {
//			myTree u = (myTree)elements[i];
//			myTree v = (myTree)elements[j];
//			
//			elements[i] = v;
//			elements[j] = u;
//
//		}

	}

//	public static class myTree implements Comparable
//	{
//		ArrayList<myTree> graph = new ArrayList<>();
//		int totalNodes = 0 ;
//		String name = "empty";
//		String typeOfRoot = "null";
//		String value = "";
//		int cntLeaf = 0;
//		boolean isBarg = false;
//		//		myTree()
//		//		{
//		//
//		//		}
//		public void add(myTree son)
//		{
//			//			for(int i = 0; i<graph.size(); i++) {
//			//				if(son.compareTo(graph.get(i)) < 0) graph.add(i, son);
//			//			}
//			//ya in
//			graph.add(son);
//			sort();
//		}
//		public void erase(String esmeshChie)
//		{
//			for(int i = 0; i<graph.size(); i++) {
//				if(graph.get(i).name.equals(esmeshChie)) graph.remove(i);
//				break;
//			}		// ye bache ba in esm ro mikhaym hazf konim ! 
//		} 
//
//		@Override
//		public int compareTo(Object o) {
//			return this.name.compareTo(((myTree)o).name);
//		}
//
//		public void sort()
//		{
//			graph.sort((myTree a , myTree b) -> a.compareTo(b));
//			for (int i = 0 ; i < graph.size() ; i ++)
//				graph.get(i).sort();
//		}
//				public void sort()
//				{
//					for(int i = 0; i<graph.size(); i++) {
//						for(int j = i+1; j<graph.size(); j++) {
//							if(graph.get(j).compareTo(graph.get(i+1)) < 0) replace(i , j);
//						}
//					}
//					// bache hash ro bar hasb e name mikhaym sort konim
//				}
//				public void replace(int i, int j) {
//					myTree u = graph.get(i);
//					myTree v = graph.get(j);
//		
//					graph.remove(i);
//					graph.add(i, v);
//					graph.remove(j);
//					graph.add(j, u);
//				}
//
//
//	}
	
	public static class myTree implements Comparable
	{
		myList<myTree> graph = new myList<>();
		int totalNodes = 0 ;
		String name = "empty";
		String typeOfRoot = "null";
		String value = "";
		int cntLeaf = 0;
		boolean isBarg = false;

		public void add(myTree son)
		{
			graph.add(son);
		}
		public void erase(String esmeshChie)
		{
			for(int i = 0; i<graph.size(); i++) {
				if(graph.get(i).name.equals(esmeshChie)){ graph.remove(i);
				break;}
			}		
		} 

		@Override
		public int compareTo(Object o) {
			return this.name.compareTo(((myTree)o).name);
		}

		public void sort()
		{
			graph.sort((myTree a , myTree b) -> a.compareTo(b));
			for (int i = 0 ; i < graph.size() ; i ++)
				graph.get(i).sort();
		}


	}
	
	public static class mySet<K /*extends Comparable<K> */> implements Set<K>{
		Object [] elements = new Object[2];
		int size = 0;

		@Override
		public boolean add(K arg0) {
			boolean b = true;
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) {
					b = false;
					break;
				}
			}
			if(b) {
				try {
					if(size == elements.length) {
						Object [] e = new Object[size * 2];
						for(int i = 0; i<size; i++) e[i] = elements[i];
						elements = e;
					}

					elements[size++] = arg0;
				}catch(Exception e){
					return false;
				}
			}


			return b;
		}

		@Override
		public boolean addAll(Collection arg0) {
			try {
				Iterator itr = arg0.iterator();
				while(itr.hasNext()) {
					Object o = itr.next();
					boolean b = false;
					for(int i = 0; i<size; i++) {
						if(elements[i].equals(o)) {
							b = true;
							break;
						}
					}

					if(!b) {
						if(size == elements.length) {
							Object [] e = new Object[size * 2];
							for(int i = 0; i<size; i++) e[i] = elements[i];
							elements = e;
						}

						elements[size++] = o;
					}

				}

			}catch(Exception e) {
				return false;
			}
			return true;
		}

		@Override
		public void clear() {
			for(int i = 0; i<size; i++) elements[i] = null;
		}

		@Override
		public boolean contains(Object arg0) {
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) return true;
			}

			return false;
		}

		@Override
		public boolean containsAll(Collection arg0) {
			Iterator itr = arg0.iterator();
			while(itr.hasNext()) {
				Object o = itr.next();
				boolean b = false;
				for(int i = 0; i<size; i++) {
					if(elements[i].equals(o)) {
						b = true;
						break;
					}
				}
				if(!b) return false;
			}

			return true;
		}

		@Override
		public boolean isEmpty() {
			return (size == 0);
		}

		@Override
		public Iterator iterator() {
			Object []fitSize = new Object[size];
			for (int i = 0 ; i < size ; i ++) fitSize[i] = elements[i];
			return Arrays.stream(fitSize).iterator();

		}

		@Override
		public boolean remove(Object arg0) {
			int where = -1;
			for(int i = 0; i<size; i++) {
				if(elements[i].equals(arg0)) {
					where = i;
					break;
				}
			}
			if(where != -1) {
				while(where++ <size);
				elements[where] = elements[where + 1];
			}

			elements[--size] = null;
			return (where != -1);
		}

		@Override
		public boolean removeAll(Collection arg0) {
			Iterator itr = arg0.iterator();
			while(itr.hasNext()) {
				Object o = itr.next();

				int where = -1;
				for(int i = 0; i<size; i++) {
					if(elements[i].equals(arg0)) {
						where = i;
						break;
					}
				}
				if(where != -1) {
					while(where++ <size);
					elements[where] = elements[where + 1];
				}

				elements[--size] = null;

			}
			return true;
		}

		@Override
		public boolean retainAll(Collection arg0) {
			try{
				Iterator itr = arg0.iterator();
				int n = 0;
				boolean [] mark = new boolean[size];
				while(itr.hasNext()) {
					Object o = itr.next();
					for(int i = 0; i<size; i++) {
						if(elements[i].equals(o)) {
							mark[i] = true;
							n++;
						}
					}
				}

				Object [] e = new Object[n];
				int where = 0;
				for(int i = 0; i<size; i++) {
					if(mark[i]) {
						e[where++] = elements[i];
					}
				}

				elements = e;

				size = n;
			}
			catch(Exception e){
				return false;
			}

			return true;

		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public Object[] toArray() {
			Object [] a = new Object[size];
			for(int i = 0; i<size; i++) a[i] = elements[i];
			return a;
		}

		@Override
		public Object[] toArray(Object[] arg0) {
			if(size < arg0.length) {
				Object [] a = new Object[size];
				for(int i = 0; i<size; i++) a[i] = elements[i];
				return a;
			}
			else {
				for(int i = 0; i<size; i++) {
					arg0[i] = elements[i];
				}
				for(int i = size; i<arg0.length; i++) {
					arg0[i] = null;
				}
			}


			return arg0;
		}
		
		public myList toList() {
			myList list = new myList();
			for(int i = 0; i<size; i++) {
				list.add(elements[i]);
			}
			return list;
		}
		

	}
	
	public static class myMap<K  ,V > implements Map<K,V> {


		element[] elements = new element[2];//tedad ino chetori dorost konm?
		int size = 0;

		@Override
		public void clear() {
			for (int i = 0; i < size; i++) {
				elements[i] = null;
			}
		}

		@Override
		public boolean containsKey(Object arg0) {
			for (int i = 0; i < size; i++) {
				if (elements[i].key.equals(arg0)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean containsValue(Object arg0) {
			for (int i = 0; i < size; i++) {
				if (elements[i].value.equals(arg0)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Set entrySet() {
			mySet a = new mySet(); 
			for (int i = 0; i < size; i++) {
				a.add(elements[i]);
			}
			return a;
		}

		@Override
		public V get(Object arg0) {
			for (int i = 0; i < size; i++) {
				if (elements[i].key.equals(arg0)) {
					return (V)elements[i].value;
				}
			}
			return null;
		}

		@Override
		public boolean isEmpty() {
			return (size == 0);
		}

		@Override
		public Set keySet() {
			mySet a = new mySet();
			for (int i = 0; i < size; i++) {
				a.add(elements[i].key);
			}
			return a;
		}

		@Override
		public V put(K arg0, V arg1) {
			boolean b = false;
			V previousValue = null;
			for (int i = 0; i < size; i++) {
				if (elements[i].key.equals(arg0)) {
					b = true;
					previousValue = (V)elements[i].value;
					elements[i].value = arg1;
					break;
				}
			}
			if (!b) {
				element a = new element(arg0, arg1);
				if (size == elements.length) {
					element[] e = new element[size * 2];
					for (int i = 0; i < size; i++) {
						e[i] = elements[i];
					}
					elements = e;
				}
				elements[size++] = a;
			}
			return previousValue;
		}

		@Override
		public void putAll(Map arg0) {
			Iterator<Map.Entry> itr = arg0.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry pair = itr.next();

				boolean b = false;
				for (int i = 0; i < size; i++) {
					if (elements[i].key.equals(pair.getKey())) {
						b = true;
						elements[i].value = pair.getValue();
						break;
					}
				}
				if (!b) {
					element a = new element(pair.getKey(), pair.getValue());
					if (size == elements.length) {
						element[] e = new element[size * 2];
						for (int i = 0; i < size; i++) {
							e[i] = elements[i];
						}
						elements = e;
					}
					elements[size++] = a;
				}
			}
		}

		@Override
		public V remove(Object arg0) {
			V previousValue = null;
			int where = -1;
			for (int i = 0; i < size; i++) {
				if (elements[i].equals(arg0)) {
					previousValue = (V)elements[i].value;
					where = i;
					break;
				}
			}
			if (where != -1) {
				for (int i = where + 1; i < size; i++) {
					elements[i - 1] = elements[i];
				}
				elements[size - 1] = null;
				size--;
			}
			return previousValue;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public Collection values() {
			myList a = new myList();

			for (int i = 0; i < size; i++) {
				a.add(elements[i].value);
			}

			return a;
		}

	}

	static class element<K extends Comparable<K>,V extends Comparable<V>> implements Map.Entry<K,V>, Comparable<element<K,V>>{

		Object key;
		Object value;

		public element(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return (K)key;
		}

		@Override
		public V getValue() {
			return (V)value;
		}

		@Override
		public V setValue(V value) {
			V ret = (V)this.value;
			this.value = (V)value;
			return ret;
		}


		@Override
		public int compareTo(element<K, V> o) {
			if (this.getKey().compareTo(o.getKey()) != 0) return this.getKey().compareTo(o.getKey());
			return this.getValue().compareTo(o.getValue());
		}


		@Override
		public boolean equals(Object o)
		{
			try{
				element<K,V> convert = (element<K,V>)o;
				if (convert.key.equals(this.key) && convert.value.equals(this.value)) return true;
			}
			catch(Exception e) {return false;}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 3;
			hash = 97 * hash + Objects.hashCode(this.key);
			hash = 97 * hash + Objects.hashCode(this.value);
			return hash;
		}

		@Override
		public String toString()
		{
			return getKey() + "->" + getValue();
		}

	}
	
	public static void sepObject(myTree father) {
		String str = father.value;
		if(!str.equals("{}"))
		{
			int w = 1;
			while(w<str.length())
			{
				myTree son = new myTree();
				
				int startOfKey = ++w;
				while(!(str.charAt(w) == '"' && isEnd(w,str))) w++;
				int endOfKey = w++;
				int startOfValue = ++w;
				if(str.charAt(w) == '{')
				{
					w++;
					int t = 1;
					while(t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}
						if(str.charAt(w) == '{') t++;
						else if(str.charAt(w) == '}') t--;
						w++;
					}
					if(w == startOfValue + 3)
					{
						son.typeOfRoot = "primitive";
						son.isBarg = true;
					}
					else 
					{
						son.typeOfRoot = "object";
						son.isBarg = false;
					}
				}
				else if(str.charAt(w) == '[')
				{
					w++;
					int t = 1;
					while(t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}

						if(str.charAt(w) == '[') t++;
						else if(str.charAt(w) == ']') t--;
						w++;
					}
					if(w == startOfValue + 2)
					{
						son.typeOfRoot = "primitive";
						son.isBarg = true;
					}
					else 
					{
						son.typeOfRoot = "array";
						son.isBarg = false;
					}
				}
				else if(str.charAt(w) == '"')
				{
					w++;
					while(!(str.charAt(w) == '"' && isEnd(w, str))) w++;
					w++;
					son.typeOfRoot = "primitive";
					son.isBarg = true;
				}
				else
				{
					while(!(str.charAt(w) == ',' || str.charAt(w) == '}')) w++;
					son.typeOfRoot = "primitive";
					son.isBarg = true;
				}
				
				int endOfValue = w++;
				
				String key = str.substring(startOfKey, endOfKey);
				String value = str.substring(startOfValue, endOfValue);
				son.name = '_' + key;
				son.value = value;
				
				for(int i = 0; i<father.cntLeaf; i++)
				{
					myTree child = father.graph.get(i);
					if(child.name.equals(son.name))
					{
						father.erase(son.name);
						father.cntLeaf --;
						break;
					}
				
				}
				father.add(son);
				father.cntLeaf ++;
				
				
				
			}
			for(int i = 0; i<father.cntLeaf; i++)
			{
				
				myTree child = father.graph.get(i);
				if(child.typeOfRoot.equals("object"))
				{
					sepObject(child);
				}
				else if(child.typeOfRoot.equals("array"))
				{
					sepArray(child);
				}
			}
		}
		
		
	}
	
	public static void sepArray(myTree father) {
		String str = father.value;
		if(!str.equals("[]"))
		{
			int w = 1;
			while(w<str.length())
			{
				myTree son = new myTree();
	  
				int startOfValue = w;
				if(str.charAt(w) == '{')
				{
					
					w++;
					int t = 1;
					while(t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}
						if(str.charAt(w) == '{') t++;
						else if(str.charAt(w) == '}') t--;
						w++;
						
					}
					if(w == startOfValue + 3)
					{
						son.typeOfRoot = "primitive";
						son.isBarg = true;
					}
					else 
					{
						son.typeOfRoot = "object";
						son.isBarg = false;
					}
				}
				else if(str.charAt(w) == '[')
				{
					w++;
					int t = 1;
					while(t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}
						if(str.charAt(w) == '[') t++;
						else if(str.charAt(w) == ']') t--;
						w++;
					}
					if(w == startOfValue + 3)
					{
						son.typeOfRoot = "primitive";
						son.isBarg = true;
					}
					else 
					{
						son.typeOfRoot = "array";
						son.isBarg = false;
					}
				}
				else if(str.charAt(w) == '"')
				{
					w++;
					while(!(str.charAt(w) == '"' && isEnd(w, str))) w++;
					w++;
					son.typeOfRoot = "primitive";
					son.isBarg = true;
				}
				else
				{
					while(!(str.charAt(w) == ',' || str.charAt(w) == ']')) w++;
					son.typeOfRoot = "primitive";
					son.isBarg = true;
				}
				
				int endOfValue = w++;
				
				String key = ""+father.cntLeaf;
				String value = str.substring(startOfValue, endOfValue);
				son.name =  key;
				son.value = value;
				

				father.add(son);
				father.cntLeaf ++;
				
				
			}
			for(int i = 0; i<father.cntLeaf; i++)
			{
				myTree child = father.graph.get(i);
				if(child.typeOfRoot.equals("object"))
				{
					sepObject(child);
				}
				else if(child.typeOfRoot.equals("array"))
				{
					sepArray(child);
				}
			}
		}
	}

	public static void isValidObject(String str) {
		if(str.length() <= 1) finish();
		if(!str.equals("{}")) {
			int w = 1;
			while(w<str.length()) {
				if(str.charAt(w) == '"') {

					w++;
					while(w<str.length() && !(str.charAt(w)=='"' && isEnd(w, str))) {
						try {

							if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) {
								finish();
							}
							if(str.charAt(w) == '\\') w+=2;
							else w++;

						}catch(Exception e) {
							finish();}
					}
					w++;
					if(w > str.length() - 1) {
						
						finish();
					}
				}else {
					finish();
				}

				if(str.charAt(w++)!=':') {
					finish();
				}
				if(str.charAt(w) == '"') 
				{
					w++;
					while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str))) {
						try {

							if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) {
								finish();
							}
							if(str.charAt(w) == '\\') w+=2;
							else w++;

						}catch(Exception e) {
							finish();}
					}
					w++;
					if(w > str.length() - 1) {
						finish();
					}
				}
				else if(w<str.length() - 4 && str.substring(w, w+4).equals("true")) w+=4;
				else if(w<str.length() - 4 && str.substring(w, w+4).equals("null")) w+=4;
				else if(w<str.length() - 5 && str.substring(w, w+5).equals("false")) w+=5;
				else if(str.charAt(w) == '.')
				{
					int g = ++w;
					while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
					if(g == w) {
						finish();
					}
				}
				else if(str.charAt(w) >= '0' && str.charAt(w) <= '9') 
				{	
					int dot = 0;
					while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
					{
						if(str.charAt(w) == '.') dot++;
						w++;
					}
					if(str.charAt(w-1) == '.') {
						finish();
					}
					if(dot>1) {
						finish();
					}

				}
				else if(str.charAt(w) == '-')
				{
					w++;
					if(str.charAt(w) == '.')
					{
						int g = ++w;
						while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
						if(g == w) {
							finish();
						}
					}
					else if(str.charAt(w) >= '0' && str.charAt(w) <= '9')
					{
						if(str.charAt(w) == '0' && !(str.charAt(w+1) == '.' || str.charAt(w+1)== '}' || str.charAt(w+1) == ',')) {
							finish();
						}
						int dot = 0;
						while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
						{
							if(str.charAt(w) == '.') dot++;
							w++;
						}
						if(str.charAt(w-1) == '.') {
							finish();
						}
						if(dot > 1) {
							finish();
						}
					}
					else {
						finish();
					}
				}
				else if(str.charAt(w) == '+')
				{
					w++;
					if(str.charAt(w) == '.')
					{
						int g = ++w;
						while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
						if(g == w) 
						{
							finish();
						}
					}
					else if(str.charAt(w) >= '0' && str.charAt(w) <= '9')
					{
						if(str.charAt(w) == '0' && !(str.charAt(w+1) == '.' || str.charAt(w+1) == '}' || str.charAt(w+1) == ',')) 
						{
							finish();
						}
						int dot = 0;
						while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
						{
							if(str.charAt(w) == '.') dot++;
							w++;
						}
						if(str.charAt(w-1) == '.') 
						{
							finish();
						}
						if(dot > 1) 
						{
							finish();
						}
					}
					else 
					{
						finish();
					}
				}
				else if(str.charAt(w) == '{')
				{
					int startOfObject = w++;
					int t = 1;
					while(w<str.length() && t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}
						if(str.charAt(w) == '{') t++;
						else if(str.charAt(w) == '}') t--;
						w++;
					}
					if(w > str.length() - 1) {
						finish();
					}
					int endOfObject = w;
					String object = str.substring(startOfObject, endOfObject);
					isValidObject(object);
				}
				else if(str.charAt(w) == '[')
				{
					int startOfArray = w++;
					int t = 1;
					while(w < str.length() && t != 0)
					{
						if(str.charAt(w) == '"') 
						{
							w++;
							while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
							{
								try {

									if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
									if(str.charAt(w) == '\\') w+=2;
									else w++;

								}catch(Exception e) {
									finish();}
							}
							w++;
							if(w > str.length() - 1)
							{
								finish();
							}
						}
						if(str.charAt(w) == '[') t++;
						else if(str.charAt(w) == ']') t--;
						w++;
					}
					if(w> str.length() - 1) {
						finish();
					}
					int endOfArray = w;
					String array = str.substring(startOfArray, endOfArray);
					isValidArray(array);
				}
				else {
					finish();
				}
				if(w > str.length() - 1) {
					finish();
				}
				if(str.charAt(w) == ',') w++;
				else if(str.charAt(w) == '}')
				{
					if(w != str.length() - 1) {
						finish();
					}
					else w++;
				}else {
					finish();
				}

			}
		}
	}

	public static void isValidArray(String str) {
		if(str.length() <= 1) finish();
		if(!str.equals("[]")) {
			int w = 1;
			if(str.charAt(w) == '"') 
			{
				w++;
				while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
				{
					try {

						if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
						if(str.charAt(w) == '\\') w+=2;
						else w++;

					}catch(Exception e) {
						finish();}
				}
				w++;
				if(w > str.length() - 1)
				{
					finish();
				}
			}
			else if(w<str.length() - 4 && str.substring(w, w+4).equals("true")) w+=4;
			else if(w<str.length() - 4 && str.substring(w, w+4).equals("null")) w+=4;
			else if(w<str.length() - 5 && str.substring(w, w+5).equals("false")) w+=5;
			else if(str.charAt(w) == '.')
			{
				int g = ++w;
				while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
				if(g == w)
				{
					finish();
				}
			}
			else if(str.charAt(w) >= 0 && str.charAt(w) <= '9') 
			{	
				int dot = 0;
				while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
				{
					if(str.charAt(w) == '.') dot++;
					w++;
				}
				if(str.charAt(w-1) == '.') {
					finish();
				}
				if(dot>1) 
				{
					finish();
				}

			}
			else if(str.charAt(w) == '-')
			{
				w++;
				if(str.charAt(w) == '.')
				{
					int g = ++w;
					while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
					if(g == w) 
					{
						finish();
					}
				}
				else if(str.charAt(w) >= '0' && str.charAt(w) <= '9')
				{
					if(str.charAt(w) == '0' && !(str.charAt(w+1) == '.' || str.charAt(w+1) == '}' || str.charAt(w+1) == ',')) 
					{
						finish();
					}
					int dot = 0;
					while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
					{
						if(str.charAt(w) == '.') dot++;
						w++;
					}
					if(str.charAt(w-1) == '.') 
					{
						finish();
					}
					if(dot > 1) 
					{
						finish();
					}
				}
				else 
				{
					finish();
				}
			}
			else if(str.charAt(w) == '+')
			{
				w++;
				if(str.charAt(w) == '.')
				{
					int g = ++w;
					while(str.charAt(w) >= '0' && str.charAt(w) <= '9') w++;
					if(g == w) 
					{
						finish();
					}
				}
				else if(str.charAt(w) >= '0' && str.charAt(w) <= '9')
				{
					if(str.charAt(w) == '0' && !(str.charAt(w+1) == '.' || str.charAt(w+1) == '}' || str.charAt(w+1) == ',')) 
					{
						finish();
					}
					int dot = 0;
					while((str.charAt(w) >= '0' && str.charAt(w) <= '9') || str.charAt(w) == '.')
					{
						if(str.charAt(w) == '.') dot++;
						w++;
					}
					if(str.charAt(w-1) == '.') 
					{
						finish();
					}
					if(dot > 1) 
					{
						finish();
					}
				}
				else 
				{
					finish();
				}
			}
			else if(str.charAt(w) == '{')
			{
				int startOfObject = w++;
				int t = 1;
				while(w<str.length() && t != 0)
				{
					if(str.charAt(w) == '"') 
					{
						w++;
						while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
						{
							try {

								if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
								if(str.charAt(w) == '\\') w+=2;
								else w++;

							}catch(Exception e) {
								finish();}
						}
						w++;
						if(w > str.length() - 1)
						{
							finish();
						}
					}
					
					if(str.charAt(w) == '{') t++;
					else if(str.charAt(w) == '}') t--;
					w++;
				}
				if(w > str.length() - 1) 
				{
					finish();
				}
				int endOfObject = w;
				String object = str.substring(startOfObject, endOfObject);
				isValidObject(object);
			}
			else if(str.charAt(w) == '[')
			{
				int startOfArray = w++;
				int t = 1;
				while(w < str.length() && t != 0)
				{
					if(str.charAt(w) == '"') 
					{
						w++;
						while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str)))
						{
							try {

								if(str.charAt(w)=='\\' && !(str.charAt(w+1) == '"' || str.charAt(w+1) == '\\')) finish();
								if(str.charAt(w) == '\\') w+=2;
								else w++;

							}catch(Exception e) {
								finish();}
						}
						w++;
						if(w > str.length() - 1)
						{
							finish();
						}
					}
					if(str.charAt(w) == '[') t++;
					else if(str.charAt(w) == ']') t--;
					w++;
				}
				if(w> str.length() - 1) 
				{
					finish();
				}
				int endOfArray = w;
				String array = str.substring(startOfArray, endOfArray);
				isValidArray(array);
			}
			else 

			{
				finish();
			}
			if(w > str.length() - 1) {
				finish();
			}
			if(str.charAt(w) == ',') w++;
			else if(str.charAt(w) == ']')
			{
				if(w != str.length() - 1) {
					finish();
				}
				else w++;
			}else {
				finish();
			}
		}
	}

	public static String removeAll(String str) {
		try {
			int w = 0;
			while(w < str.length()) {
				if(str.charAt(w) == '"') {
					w++;
					while(w<str.length() && !(str.charAt(w) == '"' && isEnd(w, str))) w++;
					w++;
					if(w > str.length()-1)

					{
						finish();
					}
				}


				boolean b = (str.charAt(w) == '{') || (str.charAt(w) == '}') || (str.charAt(w) == '[')|| (str.charAt(w) == ']') || (str.charAt(w) == ',') || (str.charAt(w) == ':') || (str.charAt(w) == '-') || (str.charAt(w) == '+');

				if(b) 
				{
					int r = w-1;
					while(r >= 0 && str.charAt(r) == ' ')
					{
						str = remove(r, str);
						r--;
						w--;
					}
					r+=2;
					while(r<str.length() && str.charAt(r) == ' ')
					{
						str = remove(r, str);
					}
				}
				w++;
			}
		}catch(Exception e) {
			finish();
		}

		return str;
	}

	public static boolean isEnd(int w, String str) {
		int s = 0, r = w-1;

		while (r >= 0 && str.charAt(r) == '\\')
		{
			s++;
			r--;
		}

		return (s % 2 == 0);
	}

	public static String remove(int i, String str) {
		str = str.substring(0,  i) + str.substring(i+1, str.length());
		return str;
	}

	public static void finish() {
		System.out.println(0);
		System.exit(0);
	}

	public static String setValue(myTree g) {
		if(!g.isBarg) {
			myList<myTree> son = g.graph;
			son.sort();
			g.value = "";
			if(g.typeOfRoot.equals("array"))
			{
				g.value += '[';
				for(int i = 0; i<son.size(); i++)
				{
					g.value += setValue(son.get(i));
					if(i != son.size() - 1)
					{
						g.value += ',';
					}
				}
				g.value += ']';
			}
			else if(g.typeOfRoot.equals("object"))
			{
				g.value += '{';
				for(int i = 0; i<son.size(); i++)
				{
					String s = son.get(i).name;
					g.value = g.value + "\""+remove(0, s) +"\""+ ':' + setValue(son.get(i));
					if(i != son.size() - 1)
					{
						g.value += ',';
					}
				}
				g.value += '}';
			}
		} 

		return g.value;
	}
	
	
	public static int cntNode(myTree tree) {
		try{
			tree.totalNodes += tree.cntLeaf;
			for(int i = 0; i<tree.graph.size(); i++)
			{
				myTree child = tree.graph.get(i);
				tree.totalNodes += cntNode(child);
			}
		}catch(Exception e){finish();}
		return tree.totalNodes;
	
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		str = removeAll(str);
		isValidObject(str);
		myTree father = new myTree();
		father.typeOfRoot = "object";
		father.value = str;
		sepObject(father);
		setValue(father);
		System.out.println(father.value);
		
		int cntValue = cntNode(father) + 1;
		System.out.println(cntValue);

	}
}


