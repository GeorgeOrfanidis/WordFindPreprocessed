interface BinNode { // Binary tree node ADT //something like header file in c++, .h 
  // Get and set the element value
  public Information value(); //it was object 
  public void setValue(Information v);

  // return the children
  public BinNode left();
  public BinNode right();

  // return TRUE if a leaf node, FALSE otherwise
  public boolean isLeaf();
}
// Binary tree node implementation: supports comparable objects
class BSTNode  { //implements the header file, .cpp implements BinNode was there 
  private Comparable element; // Element for this node //try to put information instead comparable 
  private BSTNode left;          // Pointer to left child
  private BSTNode right;         // Pointer to right child

  // Constructors
  BSTNode() {left = right = null; }
  BSTNode(Comparable val) { left = right = null; element = val; }
  BSTNode(Comparable val, BSTNode l, BSTNode r)
    { left = l; right = r; element = val; }

  // Get and set the element value
  public Comparable value() { return element; }
  public void setValue(Comparable v) { element = v; }
  public void setValue(Object v) { // We need this one to satisfy BinNode interface
    if (!(v instanceof Comparable))
      throw new ClassCastException("A Comparable object is required.");
    element = (Comparable)v;
  }

  // Get and set the left child
  public BSTNode left() { return left; }
  public void setLeft(BSTNode p) { left = p; }

  // Get and set the right child
  public BSTNode right() { return right; }
  public void setRight(BSTNode p) { right = p; }

  // return TRUE if a leaf node, FALSE otherwise
  public boolean isLeaf() { return (left == null) && (right == null); }
}

// Binary Search Tree implementation
class BST {
  private BSTNode root; // Root of the BST
  private int nodecount; // Number of nodes in the BST

  // constructor
  BST() { root = null; nodecount = 0; }

  // Reinitialize tree
  public void clear() { root = null; nodecount = 0; }
  private Comparable findhelp(BSTNode rt, Comparable key){
      if (rt==null)return null;
      if (rt.value().compareTo(key)>0)
      return findhelp(rt.left(),key);
      else if (rt.value().compareTo(key)==0)
      return rt.value(); 
      else return findhelp (rt.right(),key);
    }
  
  private BSTNode inserthelp(BSTNode rt, Comparable e){
      if (rt==null)return new BSTNode(e);
      if (rt.value().compareTo(e)>0)
        rt.setLeft(inserthelp(rt.left(),e));
      else 
      rt.setRight(inserthelp(rt.right(),e));
      return rt;
  }
    
  private BSTNode removehelp(BSTNode rt, Comparable key){
      if (rt==null)return null;
      if (rt.value().compareTo(key)>0)
      rt.setLeft(removehelp(rt.left(),key));
      else if (rt.value().compareTo(key)<0)
      rt.setRight(removehelp(rt.right(),key));
      else 
      {
          if(rt.left()==null)return rt.right();
          else if(rt.right()==null) return rt.left();
          else 
          {
              BSTNode temp=getmax(rt.left());
              rt.setValue(temp.value());
              rt.setLeft(deletemax(rt.left()));
            }
        }
        return rt;
    }
  // Get the maximum valued element in a subtree
  private BSTNode getmax(BSTNode rt) {
    if (rt.right() == null) return rt;
    return getmax(rt.right());
  }
  private BSTNode deletemax(BSTNode rt){
      if (rt.right()==null)return rt.left();
      rt.setRight(deletemax(rt.right()));
      return rt;
    }
    // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(Comparable e) {
    root = inserthelp(root, e);
    nodecount++;
  }

  // Remove a record from the tree
  // key: The key value of record to remove
  // Returns the record removed, null if there is none.
  public Comparable remove(Comparable key) {
    Comparable temp = findhelp(root, key); // First find it
    if (temp != null) {
      root = removehelp(root, key); // Now remove it
      nodecount--;
    }
    return temp;
  }

  // Return the record with key value k, null if none exists
  // key: The key value to find
  public Comparable find(Comparable key) { return findhelp(root, key); }

  // Return the number of records in the dictionary
  public int size() { return nodecount;}
}