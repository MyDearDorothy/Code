package 数据结构;

import java.util.*;

/**
 * @author ZhangCheng
 * @version 1.0
 * @date 2022/1/8 11:20
 * 二叉树
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class TestBinaryTree {
    //    翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        /*递归
        if(root==null)
            return null;
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;*/
        if(root==null)
            return null;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode p;
        while(!queue.isEmpty()) {
            p = queue.poll();
            swap(p);
            if(p.left!=null)
                queue.add(p.left);
            if(p.right!=null)
                queue.add(p.right);
        }
        return root;
    }
    void swap(TreeNode p){
        TreeNode temp = p.left;
        p.left = p.right;
        p.right = temp;
    }
    //    对称二叉树：给你一个二叉树的根节点 root ， 检查它是否轴对称。
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }
    public boolean check(TreeNode p, TreeNode q) {
        /*递归
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);*/
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while(!queue.isEmpty()){
            p=queue.poll();
            q=queue.poll();
            if (p == null && q == null)
                continue;
            if ((p == null || q == null)||p.val!=q.val)
                return false;
            //子树为null照旧加入队列，出队时进行判断。
            queue.add(p.left);
            queue.add(q.right);

            queue.add(p.right);
            queue.add(q.left);
        }
        return true;

    }
    //    二叉树先序遍历
    public List<Integer> preorderTraversal(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        TreeNode p=root;
        while(p!=null||!stack.isEmpty()){
            if (p!=null){
                list.add(p.val);
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                p=p.right;
            }
        }
        return list;
    }
    //    二叉树中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        TreeNode p=root;
        while(p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                list.add(p.val);
                p=p.right;
            }
        }
        return list;
    }
    //    二叉树后序遍历
    public List<Integer> postorderTraversal(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        TreeNode p=root,r=null;
        while(p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.peek();
                if(p.right==null||p.right==r){
                    list.add(p.val);
                    p=stack.pop();
                    r=p;
                    p=null;
                }
                else
                    p=p.right;
            }
        }
        return list;
    }
    //    二叉树层次遍历
    public List<Integer> levelorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Queue<TreeNode> quene=new LinkedList<>();
        TreeNode p;
        quene.add(root);
        while(!quene.isEmpty()){
            p=quene.poll();
            list.add(p.val);
            if(p.left!=null)
                quene.add(p.left);
            if(p.right!=null)
                quene.add(p.right);
        }
        return list;
    }
    //    从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root!=null)
            queue.add(root);
        TreeNode p;
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> row=new ArrayList<>();
            for(int i=0;i<size;i++){
                p=queue.poll();
                if (p.left!=null)
                    queue.add(p.left);
                if (p.right!=null)
                    queue.add(p.right);
                row.add(p.val);
            }
            list.add(row);
        }
        return list;
    }


    //    验证二叉搜索树(BST)
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        double temp = -Double.MAX_VALUE;
        TreeNode p=root;
        while(p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                if(p.val<=temp)
                    return false;
                temp=p.val;
                p=p.right;
            }
        }
        return true;
    }
    //    二叉搜索树(BST)中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        /* 递归
        if (root == null || root.val == val) return root;
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);*/
        // 迭代
        while (root != null && root.val != val) {
            root = root.val > val ? root.left : root.right;
        }
        return root;
    }
    //    二叉搜索树(BST)中的插入
    public TreeNode insertIntoBST(TreeNode root, int val) {
        /*递归
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val < val){
            root.right = insertIntoBST(root.right,val);
        } else {
            root.left = insertIntoBST(root.left,val);
        }
        return root;*/
        // 迭代
        if(root==null)
            return new TreeNode(val);
        TreeNode p=root;
        while (p!=null){
            if (p.val>val){
                if(p.left==null) {
                    p.left = new TreeNode(val);
                    break;
                }
                else
                    p=p.left;
            }
            else{
                if (p.right==null) {
                    p.right = new TreeNode(val);
                    break;
                }
                p=p.right;
            }
        }
        return root;

    }
    //    二叉搜索树(BST)的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
    //    给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
    public boolean findTarget(TreeNode root, int k) {
        //中序遍历
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        TreeNode p=root;
        while(p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                list.add(p.val);
                p=p.right;
            }
        }

        int low=0,high=list.size()-1,temp;
        while(low<high){
            temp=list.get(low)+list.get(high);
            if (temp==k)
                return true;
            else if (temp<k)
                low++;
            else
                high--;
        }
        return false;
    }
}
