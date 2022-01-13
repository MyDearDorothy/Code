package Algorithms.数据结构;

import sun.reflect.generics.tree.Tree;

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

    public static void main(String[] args) {

    }
}
