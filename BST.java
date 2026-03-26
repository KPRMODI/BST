public class BST<E extends Comparable<E>> implements Tree<E> {

    // ── Inner node class ──────────────────────────────────────────────────
    protected static class TreeNode<E> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E e) {
            element = e;
            left    = null;
            right   = null;
        }
    }

    // ── Fields ────────────────────────────────────────────────────────────
    protected TreeNode<E> root;
    protected int size;

    // ── Constructor ───────────────────────────────────────────────────────
    public BST() {
        root = null;
        size = 0;
    }

    // ── Search ────────────────────────────────────────────────────────────
    @Override
    public boolean search(E e) {
        // TODO: return true if e is in the tree, false otherwise
        // Follow the invariant from root.
        // Return false when current becomes null (fell off the tree).
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0){
                current = current.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    // ── Insert ────────────────────────────────────────────────────────────
    @Override
    public boolean insert(E e) {
        // TODO: insert e into the correct position
        // Return false if e is already in the tree (duplicate).
        // Return true if inserted successfully.
        // Remember to increment size on a successful insert.

        if (root == null) {
            root = new TreeNode<>(e);
            size++;
            return true;
        }
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
        // move down one level, remembering the parent
            if      (cmp < 0) { parent = current; current = current.left; }
            else if (cmp > 0) { parent = current; current = current.right; }
            else return false;  // duplicate
        }
        // current is null -- attach new node to parent
        if (e.compareTo(parent.element) < 0)
            parent.left  = new TreeNode<>(e);
        else
            parent.right = new TreeNode<>(e);
        size++;
        return true;
        }

    // ── Delete ────────────────────────────────────────────────────────────
    @Override
    public boolean delete(E e) {
        // Step 1: find the node -- same path as search, tracking parent
        TreeNode<E> parent  = null;
        TreeNode<E> current = root;

        while (current != null) {
            int cmp = e.compareTo(current.element);
            if      (cmp < 0) { parent = current; current = current.left; }
            else if (cmp > 0) { parent = current; current = current.right; }
            else break; // found
        }

        if (current == null) {
            return false;
         } // not found

        // Step 2: determine which case applies and handle it
        // TODO Case 1: current has no children
        //   -- set parent's left or right to null
        //   -- handle the special case where current is the root

        // TODO Case 2: current has one child
        //   -- set parent's pointer to current's only child
        //   -- handle the special case where current is the root

        // TODO Case 3: current has two children
        //   -- find the in-order successor: go right once, then left as far as possible
        //   -- copy successor's value into current
        //   -- delete the successor (it has at most one child, so Case 1 or 2)

        // TODO: decrement size and return true
        return false; // replace this
    }

    // ── Inorder traversal ─────────────────────────────────────────────────
    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode<E> root) {
        // TODO: implement inorder traversal (left -> visit -> right)
        // Base case: if node is null, return.

        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    // ── Preorder traversal ────────────────────────────────────────────────
    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> root) {
        // TODO: implement preorder traversal (visit -> left -> right)
        // Base case: if node is null, return.
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // ── Postorder traversal ───────────────────────────────────────────────
    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> root) {
        // TODO: implement postorder traversal (left -> right -> visit)
        // Base case: if node is null, return.
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");

    }

    // ── Size and empty ────────────────────────────────────────────────────
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // ── Test driver ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        // Insert
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(90);

        // Traversals -- predict the output before running
        System.out.print("Inorder:   "); tree.inorder();   System.out.println();
        System.out.print("Preorder:  "); tree.preorder();  System.out.println();
        System.out.print("Postorder: "); tree.postorder(); System.out.println();

        // Search
        System.out.println("Search 30: " + tree.search(30));  // true
        System.out.println("Search 40: " + tree.search(40));  // false

        // Delete leaf
        tree.delete(30);
        System.out.print("After delete 30: "); tree.inorder(); System.out.println();

        // Delete node with one child
        tree.delete(25);
        System.out.print("After delete 25: "); tree.inorder(); System.out.println();

        // Delete node with two children
        tree.delete(75);
        System.out.print("After delete 75: "); tree.inorder(); System.out.println();

        // Size
        System.out.println("Size: " + tree.getSize());  // 4
    }
}
