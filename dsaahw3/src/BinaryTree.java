import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    public TreeNode root;
    public boolean AddLeft(TreeNode parent, TreeNode left){
        if(parent == null||left == null||parent.left != null){return false;}
        //left.left = parent.left.left;
        //left.right = parent.left.right;
        parent.left = left;
        return true;
    }
    public boolean AddRight(TreeNode parent, TreeNode right){
        if(parent == null||right == null||parent.right != null){return false;}
        //right.left = parent.right.left;
        //right.right = parent.right.right;
        parent.right = right;
        return true;
    }
    public String TraversalInOrder() {
        return inorderTraversal(root).toString().replaceAll("(,)|(\\[)|(\\])","").concat(" ");
    }
    public String TraversalPreOrder() {
        return preorderTraversal(root).toString().replaceAll("(,)|(\\[)|(\\])","").concat(" ");
    }
    public String TraversalPostOrder() {
        return postorderTraversal(root).toString().replaceAll("(,)|(\\[)|(\\])","").concat(" ");
    }
    public String TraversalLevelOrder() {
        if (root == null) {
            return "";
        }
        String ans="";
        Queue<TreeNode> tmp = new LinkedList<TreeNode>();
        String tmpans = "";
        tmp.offer(root);
        while( !tmp.isEmpty() ){
            int level = tmp.size();
            tmpans = "[";
            for (int i=0;i<level;++i){
                TreeNode node = tmp.poll();
                tmpans += (node.val+" ");
                if( node.left != null )
                    tmp.offer( node.left );
                if( node.right != null )
                    tmp.offer( node.right );
            }
            tmpans += ("]");
            ans += (tmpans);
        }
        ans += ("");
        return ans;
    }
    public String PreIn2Post(String preTravesal, String inTravesal){
        String[] pre = preTravesal.split(" ");
        String[] in = inTravesal.split(" ");
        BinaryTree ans = new BinaryTree();
        ans.root = PreIn2Post(pre,in);
        return ans.TraversalPostOrder();
    }
    public String InPost2Pre(String inTravesal, String postTravesal){
        String[] in = inTravesal.split(" ");
        String[] post = postTravesal.split(" ");
        BinaryTree ans = new BinaryTree();
        ans.root = InPost2Pre(in,post);
        return ans.TraversalPreOrder();
    }

    private List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List ans = new LinkedList();
        ans.add(root.val);
        if (root.left != null) {
            ans.addAll(preorderTraversal(root.left));
        }
        if (root.right != null) {
            ans.addAll(preorderTraversal(root.right));
        }
        return ans;
    }
    private List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List ans = new LinkedList();
        if (root.left != null) {
            ans.addAll(inorderTraversal(root.left));
        }
        ans.add(root.val);
        if (root.right != null) {
            ans.addAll(inorderTraversal(root.right));
        }
        return ans;
    }
    private List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List ans = new LinkedList();
        if (root.left != null) {
            ans.addAll(postorderTraversal(root.left));
        }
        if (root.right != null) {
            ans.addAll(postorderTraversal(root.right));
        }
        ans.add(root.val);
        return ans;
    }
    private TreeNode PreIn2Post(String[] pre, String[] in) {
        if(pre.length==0 || in.length==0) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(pre[0]));
        for(int i=0;i<pre.length;++i) {
            if(pre[0].equals(in[i])) {
                String[] pre_left = Arrays.copyOfRange(pre,1,i+1);
                String[] pre_right = Arrays.copyOfRange(pre,i+1,pre.length);
                String[] in_left = Arrays.copyOfRange(in,0,i);
                String[] in_right = Arrays.copyOfRange(in,i+1,in.length);
                root.left = PreIn2Post(pre_left,in_left);
                root.right = PreIn2Post(pre_right,in_right);
                break;
            }
        }
        return root;
    }
    private TreeNode InPost2Pre(String[] in, String[] post){
        if(in.length==0 || post.length==0) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(post[post.length-1]));
        for(int i=0;i<post.length;++i) {
            if(post[post.length-1].equals(in[i])) {
                String[] post_left = Arrays.copyOfRange(post,0,i);
                String[] post_right = Arrays.copyOfRange(post,i,post.length-1);
                String[] in_left = Arrays.copyOfRange(in,0,i);
                String[] in_right = Arrays.copyOfRange(in,i+1,in.length);
                root.left = InPost2Pre(in_left,post_left);
                root.right = InPost2Pre(in_right,post_right);
                break;
            }
        }
        return root;
    }
}
