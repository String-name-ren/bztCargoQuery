package com.leader.ren.common.utils;


import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
  /**
   * 两层循环实现建树
   * 
   * @param treeNodes 传入的树节点列表
   * @return
   */
  public static <T extends Tree> List<T> bulid(List<T> treeNodes, Object root)  {

    List<T> trees = new ArrayList<T>();

    for (T treeNode : treeNodes) {

      if (root.equals(treeNode.getParentId())) {
        treeNode.setLevel(1);
        trees.add(treeNode);
      }

      for (T it : treeNodes) {
        if (treeNode.getId().equals(it.getParentId())) {
          if (treeNode.getChildren() == null) {
            treeNode.setChildren(new ArrayList<Tree>());
          }
          it.setLevel(treeNode.getLevel()+1);
          treeNode.add(it);
        }
      }
    }
    return trees;
  }

  /**
   * 使用递归方法建树
   * 
   * @param treeNodes
   * @return
   */
  public static <T extends Tree> List<T> buildByRecursive(List<T> treeNodes, Object root) {
    List<T> trees = new ArrayList<T>();
    for (T treeNode : treeNodes) {
      if (root.equals(treeNode.getParentId())) {
        trees.add(findChildren(treeNode, treeNodes));
      }
    }
    return trees;
  }

  /**
   * 递归查找子节点
   * 
   * @param treeNodes
   * @return
   */
  public static <T extends Tree> T findChildren(T treeNode, List<T> treeNodes) {
    for (T it : treeNodes) {
      if (treeNode.getId().equals(it.getParentId())) {
        if (treeNode.getChildren() == null) {
          treeNode.setChildren(new ArrayList<Tree>());
        }
        treeNode.add(findChildren(it, treeNodes));
      }
    }
    return treeNode;
  }

}
